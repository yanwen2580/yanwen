package cn.itrip.trade.controller;

import cn.itrip.beans.pojo.ItripHotelOrder;
import cn.itrip.common.RedisAPI;
import cn.itrip.trade.sdk.alipay.AlipayConfig;
import cn.itrip.trade.service.OrderService;
import com.alibaba.fastjson.JSON;
import com.alipay.api.internal.util.AlipaySignature;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Controller
public class OrderController {
    @Resource
    private OrderService orderService;
    @Resource
    private RedisAPI redisAPI;
    @GetMapping("/api/prepay/{order}")
    public String prepa(@PathVariable String order,HttpServletRequest request){
        ItripHotelOrder porder = orderService.queryBy(order);
        redisAPI.set("orderNo",300, JSON.toJSONString(porder.getOrderNo()));
        request.setAttribute("orderVo",porder);
        return "wappay/pay";
    }
    @PostMapping("api/prepay/notify_url")
    public String notigy(HttpServletResponse response, HttpServletRequest request) throws Exception {
        request.setCharacterEncoding("utf-8");
        String orderNO = redisAPI.get("orderNo");
        //获取支付宝POST过来反馈信息
        PrintWriter out = response.getWriter();
        Map<String,String> params = new HashMap<String,String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
            params.put(name, valueStr);
        }
        //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
        //商户订单号

        String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
        //支付宝交易号

        String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

        //交易状态
        String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");

        //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
        //计算得出通知验证结果
        //boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey, String charset, String sign_type)
        boolean verify_result = AlipaySignature.rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET, "RSA2");

        if(verify_result){//验证成功
            //////////////////////////////////////////////////////////////////////////////////////////
            //请在这里加上商户的业务逻辑程序代码
            //——请根据您的业务逻辑来编写程序（以下代码仅作参考）——

            if(trade_status.equals("TRADE_FINISHED")){
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
                //如果有做过处理，不执行商户的业务程序

                if (orderService.isOrderisOrderNo(orderNO)) {
                  return "true";
                }else {
                    ItripHotelOrder porder = orderService.queryBy(orderNO);
                    porder.setOrderStatus(2);
                    porder.setTradeNo(orderNO);
                    orderService.update(porder);
                     return "true";
                }
                //注意：
                //如果签约的是可退款协议，退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
                //如果没有签约可退款协议，那么付款完成后，支付宝系统发送该交易状态通知。
            } else if (trade_status.equals("TRADE_SUCCESS")){
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
                //如果有做过处理，不执行商户的业务程序
                if (orderService.isOrderisOrderNo(orderNO)) {
                    out.print("<br>订单已支付！");
                    out.print("location.href='true.jsp'");
                }else {
                    ItripHotelOrder porder = orderService.queryBy(orderNO);
                    porder.setOrderStatus(2);
                    porder.setTradeNo(orderNO);
                    orderService.update(porder);
                    return "true";
                }
                //注意：
                //如果签约的是可退款协议，那么付款完成后，支付宝系统发送该交易状态通知。
            }

            //——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
            out.println("success");	//请不要修改或删除
            return "true";
            //////////////////////////////////////////////////////////////////////////////////////////
        }else{//验证失败
            out.println("fail");
        }
        return "true";
    }
}
