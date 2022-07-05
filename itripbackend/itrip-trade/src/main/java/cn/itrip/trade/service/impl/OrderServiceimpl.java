package cn.itrip.trade.service.impl;

import cn.itrip.beans.pojo.ItripHotelOrder;
import cn.itrip.dao.hotelorder.ItripHotelOrderMapper;
import cn.itrip.trade.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OrderServiceimpl implements OrderService {
    @Resource
    private ItripHotelOrderMapper itripHotelOrderMapper;
    @Override
    public ItripHotelOrder queryBy(String orderNo) {
        return itripHotelOrderMapper.queryByOrderNo(orderNo);
    }

    @Override
    public Boolean isOrderisOrderNo(String orderNo) {
        ItripHotelOrder order = itripHotelOrderMapper.queryByOrderNo(orderNo);
        return order==null||order.getOrderStatus()!=0;

    }

    @Override
    public boolean update(ItripHotelOrder porder) {
        Integer integer =0;
        try {
            integer= itripHotelOrderMapper.updateItripHotelOrder(porder);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return integer>0;
    }
}
