package cn.itrip.auth.service;

import cn.itrip.beans.dto.Dto;


public interface Loginservice {
      Dto Login(String name, String password,String userAgent);

}
