package cn.itrip.auth.service.service;

import cn.itrip.beans.dto.Dto;
import cn.itrip.beans.pojo.ItripUser;
import cn.itrip.beans.vo.hotelroom.SearchHotelRoomVO;

public interface ItripHotelTempStoreService {
    Dto getpreorderinfo(SearchHotelRoomVO searchHotelRoomVO, ItripUser user);

    public Dto updateorderstatusandpaytype(Long id, Long payType);
}
