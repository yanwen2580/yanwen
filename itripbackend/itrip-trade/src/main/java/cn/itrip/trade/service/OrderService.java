package cn.itrip.trade.service;


import cn.itrip.beans.pojo.ItripHotelOrder;

public interface OrderService {
   ItripHotelOrder queryBy(String orderNo);
   Boolean isOrderisOrderNo(String orderNo);

   boolean update(ItripHotelOrder porder);
}
