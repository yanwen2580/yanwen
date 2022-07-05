package cn.itrip.auth.controller;

import cn.itrip.auth.service.ItripHotelService;
import cn.itrip.auth.service.Loginservice;
import cn.itrip.beans.dto.Dto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
public class LoginController {
    @Resource
    private Loginservice loginservice;
    @Resource
    private ItripHotelService itripHotelService;
    @PostMapping("/api/dologin")
    public Dto doLogin(String name, String password, HttpServletRequest request){
        String userAgent = request.getHeader("User-Agent");
         return loginservice.Login(name,password,userAgent);
    }
    @GetMapping("/api/ckusr")
    public  Dto ckusr( String name){
        return   itripHotelService.queryByUsercode(name);
    }
}
