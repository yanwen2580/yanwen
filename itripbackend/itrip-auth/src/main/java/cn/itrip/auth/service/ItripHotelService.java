package cn.itrip.auth.service;

import cn.itrip.beans.dto.Dto;

public interface ItripHotelService {
    Dto getItripHotelById(long id);

    Dto queryhotelroombyhotel(Long hotel);

    Dto queryByUsercode(String name);
}
