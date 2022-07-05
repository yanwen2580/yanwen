package cn.itrip.auth.service.impl;

import cn.itrip.beans.pojo.ItripUser;
import cn.itrip.common.MD5;
import cn.itrip.common.RedisAPI;
import cn.itrip.common.Token;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

@Service
public class Tokenservice {
    @Resource
    private RedisAPI redisAPI;
    public Token createToke(String userAgent, ItripUser user) {
            StringBuffer sb=new StringBuffer("token:");
                Token token=new Token();
        if (isPc(userAgent)){
                sb.append("PC-");
            }else {
                sb.append("MOBILE");
            }
            sb.append(MD5.MD5(user.getUserPassword()));
            sb.append("-").append(user.getUserName());
            sb.append("-").append(MD5.MD5(userAgent));
            token.setToken(sb.toString());
            token.setCreaDate(System.currentTimeMillis());
            int util=10*6000;
            token.setGqDate(System.currentTimeMillis()*util);
            redisAPI.set(sb.toString(),util, JSON.toJSONString(user));
            return token;
    }
    private static boolean isPc(String userAgent) {
        if (StringUtils.hasText(userAgent)){
            if (userAgent.indexOf("Android")!=-1){
                return false;
            }
            if (userAgent.indexOf("Mac")!=-1){
                return false;
            }
        }
        return true;
    }
}
