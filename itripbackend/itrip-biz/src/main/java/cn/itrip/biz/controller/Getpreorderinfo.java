package cn.itrip.biz.controller;

import cn.itrip.beans.dto.Dto;
import cn.itrip.beans.pojo.ItripUser;
import cn.itrip.beans.vo.hotelroom.SearchHotelRoomVO;
import cn.itrip.beans.vo.order.ItripAddHotelOrderVO;
import cn.itrip.biz.service.ItripAddHotelOrderService;
import cn.itrip.biz.service.ItripHotelTempStoreService;
import cn.itrip.biz.service.ItripUserLinkUserService;
import cn.itrip.common.DtoUtil;
import cn.itrip.common.RedisAPI;
import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class Getpreorderinfo {
    @Resource
    private ItripHotelTempStoreService itripHotelTempStoreService;
    @Resource
    private  RedisAPI redisAPI;
    @Resource
    private ItripUserLinkUserService itripUserLinkUserService;
    @Resource
     private ItripAddHotelOrderService service;
        @PostMapping("api/hotelorder/getpreorderinfo")
    public Dto getpreorderinfo(@RequestBody SearchHotelRoomVO searchHotelRoomVO, HttpServletRequest request){
            String token = request.getHeader("token");
            if (token==null){
                return DtoUtil.returnFail("10001","请您登录后在试试！");
            }
            String userInfo= redisAPI.get(token);
            ItripUser user = JSON.parseObject(userInfo,ItripUser.class);
            System.out.println(userInfo);
            return itripHotelTempStoreService.getpreorderinfo(searchHotelRoomVO, user);
    }
    @PostMapping("/api/userinfo/queryuserlinkuser")
    public Dto queryuserlinkuser(HttpServletRequest request){
            String token=request.getHeader("token");
            String userInfo=redisAPI.get(token);
            ItripUser user=JSON.parseObject(userInfo,ItripUser.class);
            return itripUserLinkUserService.queryuserlinkuser(user.getId());
    }
    @PostMapping("api/hotelorder/addhotelorder")
    public Dto addhotelorder(@RequestBody ItripAddHotelOrderVO orderVO,HttpServletRequest request){
        String token = request.getHeader("token");
        String s = redisAPI.get(token);
        ItripUser user=JSON.parseObject(s,ItripUser.class);
        orderVO.setUserId(Integer.parseInt(String.valueOf(user.getId())));
        return service.add(orderVO);
    }
    @GetMapping("api/hotelorder/getpersonalorderroominfo/{orderId}")
        public  Dto getpersonalorderinfo(@PathVariable Long orderId){
            return service.getItripHotelOrderRoomInfoById(orderId);
        }
        @PostMapping("api/hotelorder/updateorderstatusandpaytype")
    public  Dto updateorderstatusandpaytype(@RequestBody Map<String,String> map){
            return  itripHotelTempStoreService.updateorderstatusandpaytype(Long.parseLong(map.get("id")),Long.parseLong(map.get("payType")));
        }

}
