package cn.itrip.biz.service;

import cn.itrip.beans.dto.Dto;
import cn.itrip.beans.vo.order.ItripAddHotelOrderVO;

public interface ItripAddHotelOrderService {
      Dto add(ItripAddHotelOrderVO orderVO);
      public Dto getItripHotelOrderRoomInfoById( Long id);

}
