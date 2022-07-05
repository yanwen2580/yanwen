package cn.itrip.trade.controller;

import cn.itrip.beans.dto.Dto;
import cn.itrip.beans.pojo.ItripHotelOrder;
import cn.itrip.common.DtoUtil;
import cn.itrip.common.RedisAPI;
import cn.itrip.trade.sdk.wx.WXPay;
import cn.itrip.trade.sdk.wx.WXPayUtil;
import cn.itrip.trade.service.OrderService;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class Wxapy {
    @Resource
    private WXPay wxPay;
    @Resource
    private OrderService service;
    @Resource
    private RedisAPI  redisAPI;
    @GetMapping("api/wxpay/createqccode/{order}")
    public Object wxPay(@PathVariable String order) {
        ItripHotelOrder order1 = service.queryBy(order);
        redisAPI.set("orderNo", order1.getOrderNo());
        Map<String, String> data = new HashMap<String, String>();
        Map resultMap = new HashMap();
        data.put("body", order1.getHotelName());
        String uuid = UUID.randomUUID().toString();
        data.put("out_trade_no", order1.getOrderNo());
        data.put("fee_type", "CNY");
        data.put("total_fee", "1");
        data.put("spbill_create_ip", "12.234.22.11");
         data.put("trade_type", "NATIVE");  // 此处指定为扫码支付
        data.put("product_id", "12");
        try {
            Map<String, String> resp = wxPay.unifiedOrder(data);
            if ("SUCCESS".equals(resp.get("return_code"))) {
                if ("SUCCESS".equals(resp.get("result_code"))) {
                    String codeUrl = resp.get("code_url");
                    resultMap.put("codeUrl", codeUrl);
                    resultMap.put("payAmount", order1.getPayAmount());
                    resultMap.put("count", order1.getCount());
                    resultMap.put("roomId", order1.getRoomId());
                    resultMap.put("hotelName", order1.getHotelName());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DtoUtil.returnDataSuccess(resultMap);
    }

    @RequestMapping("/api/wxpay/notifUrl")
    public void queryorderstatus(  HttpServletRequest request, HttpServletResponse response) throws Exception {
        String respxml = IOUtils.toString(request.getInputStream(), "utf-8");
            Map<String, String> result = WXPayUtil.xmlToMap(respxml);
        String out_trade_no = result.get("out_trade_no");
          if ("SUCCESS".equals(result.get("return_code"))) {
            if (!service.isOrderisOrderNo(out_trade_no)) {
                ItripHotelOrder itripHotelOrder = service.queryBy(out_trade_no);
                itripHotelOrder.setTradeNo(UUID.randomUUID().toString());
                itripHotelOrder.setOrderStatus(2);
                service.update(itripHotelOrder);
            }
            Map <String,String> results=new HashMap<>();
            results.put("code_code","SUCCESS");
            results.put("return_msg","OK");
            response.getWriter().write(WXPayUtil.mapToXml(results));
            response.getWriter().flush();
        }else {
            throw  new RuntimeException("支付失败");
        }
 }
 @RequestMapping("api/wxpay/queryorderstatus/{order}")
    public  Dto queryorderstatus(@PathVariable String order){
     ItripHotelOrder orderisOrderNo = service.queryBy(order);
     return DtoUtil.returnDataSuccess(orderisOrderNo);
 }
}


