package cn.itrip.biz.controller;

import cn.itrip.beans.dto.Dto;
import cn.itrip.biz.service.ItripHotelService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
public class HotelController {
    @Resource
    private ItripHotelService itripHotelService;

    @GetMapping("api/hotel/queryhotcity/{isChina}")
    public Dto queryhotcity(@PathVariable int isChina) {
        return itripHotelService.getItripHotelById(isChina);
    }
    @PostMapping("api/hotelroom/queryhotelroombyhotel")
    public Dto queryhotelroombyhotel(@RequestBody Map pram){
        return itripHotelService.queryhotelroombyhotel(Long.parseLong(String.valueOf(pram.get("hotelId"))));
    }

}
