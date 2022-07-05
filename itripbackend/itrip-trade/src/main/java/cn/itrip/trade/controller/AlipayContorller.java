package cn.itrip.trade.controller;

import cn.itrip.trade.sdk.alipay.AlipayConfig;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
@Controller
public class AlipayContorller {
     @PostMapping("api/pay")
    public  void  alipay(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        if(request.getParameter("WIDout_trade_no")!=null){
            // 商户订单号，商户网站订单系统中唯一订单号，必填
            String out_trade_no = new String(request.getParameter("WIDout_trade_no").getBytes("ISO-8859-1"),"UTF-8");
            // 订单名称，必填
            String subject = new String(request.getParameter("WIDsubject").getBytes("ISO-8859-1"),"UTF-8");
            System.out.println(subject);
            // 付款金额，必填
            String total_amount=new String(request.getParameter("WIDtotal_amount").getBytes("ISO-8859-1"),"UTF-8");
            // 商品描述，可空
            String body = new String(request.getParameter("WIDbody").getBytes("ISO-8859-1"),"UTF-8");
            // 超时时间 可空
            String timeout_express="2m";
            // 销售产品码 必填
            String product_code="QUICK_WAP_WAY";
            /**********************/
            // SDK 公共请求类，包含公共请求参数，以及封装了签名与验签，开发者无需关注签名与验签
            //调用RSA签名方式
            AlipayClient client = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID, AlipayConfig.RSA_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY,AlipayConfig.SIGNTYPE);
            AlipayTradeWapPayRequest alipay_request=new AlipayTradeWapPayRequest();
             // 封装请求支付信息
            AlipayTradeWapPayModel model=new AlipayTradeWapPayModel();
            model.setOutTradeNo(out_trade_no);
            model.setSubject(subject);
            model.setTotalAmount(total_amount);
            model.setBody(body);
            model.setTimeoutExpress(timeout_express);
            model.setProductCode(product_code);
            alipay_request.setBizModel(model);
            // 设置异步通知地址
            alipay_request.setNotifyUrl(AlipayConfig.notify_url);
            // 设置同步地址
            alipay_request.setReturnUrl(AlipayConfig.return_url);

            // form表单生产
            String form = "";
            try {
                // 调用SDK生成表单
                form = client.pageExecute(alipay_request).getBody();
                response.setContentType("text/html;charset=" + AlipayConfig.CHARSET);
                response.getWriter().write(form);//直接将完整的表单html输出到页面
                response.getWriter().flush();
                response.getWriter().close();
            } catch (AlipayApiException | IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
