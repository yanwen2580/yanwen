package cn.itrip.common;

import com.cloopen.rest.sdk.BodyType;
import com.cloopen.rest.sdk.CCPRestSmsSDK;

import java.util.HashMap;
import java.util.Set;

public class Msg {
  public static void msg(String code,String phone){
    //生产环境请求地址：app.cloopen.com
    String serverIp = "app.cloopen.com";
    //请求端口
    String serverPort = "8883";
    //主账号,登陆云通讯网站后,可在控制台首页看到开发者主账号ACCOUNT SID和主账号令牌AUTH TOKEN
    String accountSId = "8aaf07087f77bf96017fc10a4562242a";
    String accountToken = "78828859e4984ffc9290b67589a1199c";
    //请使用管理控制台中已创建应用的APPID
    String appId = "8aaf07087f77bf96017fc10a464a2431";
    CCPRestSmsSDK sdk = new CCPRestSmsSDK();
    sdk.init(serverIp, serverPort);
    sdk.setAccount(accountSId, accountToken);
    sdk.setAppId(appId);
    sdk.setBodyType(BodyType.Type_JSON);
    String to = phone;
    String templateId= "1";
    String[] datas = {code,"5",};
    //HashMap<String, Object> result = sdk.sendTemplateSMS(to,templateId,datas);
    HashMap<String, Object> result = sdk.sendTemplateSMS(to,templateId,datas);
    if("000000".equals(result.get("statusCode"))){
      //正常返回输出data包体信息（map）
      HashMap<String,Object> data = (HashMap<String, Object>) result.get("data");
      Set<String> keySet = data.keySet();
      for(String key:keySet){
        Object object = data.get(key);
        System.out.println(key +" = "+object);
      }
    }else{
      //异常返回输出错误码和错误信息
      System.out.println("错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg"));
    }
  }

  public static void main(String[] args) {

  }
}
