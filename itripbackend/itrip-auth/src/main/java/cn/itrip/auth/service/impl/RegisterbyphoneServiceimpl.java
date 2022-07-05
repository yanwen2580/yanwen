package cn.itrip.auth.service.impl;

import cn.itrip.beans.dto.Dto;
import cn.itrip.beans.pojo.ItripUser;
import cn.itrip.common.DtoUtil;
import cn.itrip.common.MD5;
import cn.itrip.common.Msg;
import cn.itrip.common.RedisAPI;
import cn.itrip.dao.user.ItripUserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Random;

@Service
public class RegisterbyphoneServiceimpl implements RegisterService {
    @Resource
    private ItripUserMapper itripUserMapper;
    @Resource
    private RedisAPI redisAPI;
    @Override
    public Dto registerbyphone(Map <String,String>pram) {
        ItripUser itripUser=new ItripUser();
        try {
            String userCode = pram.get("userCode");
            String userName=pram.get("userName");
            String password=pram.get("userPassword");
            itripUser.setUserName(userName);
            itripUser.setUserCode(userCode);
            itripUser.setActivated(0);
            itripUser.setUserPassword(MD5.MD5(password));
            itripUserMapper.insertItripUser(itripUser);
            Random random=new Random();
            int i = random.nextInt(9999);
            redisAPI.set(userCode,300,String.valueOf(i));
            Msg.msg(userCode,String.valueOf(i));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DtoUtil.returnDataSuccess(itripUser);
    }
}
