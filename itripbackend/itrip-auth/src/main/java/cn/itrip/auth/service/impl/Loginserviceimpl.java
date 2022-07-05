package cn.itrip.auth.service.impl;

import cn.itrip.auth.service.Loginservice;
import cn.itrip.beans.dto.Dto;
import cn.itrip.beans.pojo.ItripUser;
import cn.itrip.common.DtoUtil;
import cn.itrip.common.MD5;
import cn.itrip.dao.user.ItripUserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class Loginserviceimpl implements Loginservice {
    @Resource
    private ItripUserMapper itripUserMapper;
    @Resource
    private Tokenservice tokenservice;

    @Override
    public Dto Login(String name, String password, String userAgent) {
        ItripUser login = itripUserMapper.login(name);
        System.out.println("userAgent:"+userAgent);
        if (login == null) {
            return DtoUtil.returnFail( "账号不存在","10001");
        } else if (!login.getUserPassword().equals(MD5.MD5(password))) {
            return DtoUtil.returnFail("密码不存在","10002");
        } else {
            return DtoUtil.returnSuccess("登录成功！", tokenservice.createToke(userAgent, login));
        }
    }

    public static void main(String[] args) {

    }
}
