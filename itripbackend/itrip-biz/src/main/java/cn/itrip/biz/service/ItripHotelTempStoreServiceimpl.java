package cn.itrip.biz.service;

import cn.itrip.beans.dto.Dto;
import cn.itrip.beans.pojo.ItripHotel;
import cn.itrip.beans.pojo.ItripHotelRoom;
import cn.itrip.beans.pojo.ItripUser;
import cn.itrip.beans.vo.hotelroom.SearchHotelRoomVO;
import cn.itrip.beans.vo.order.PreAddOrderVO;
import cn.itrip.beans.vo.store.StoreVO;
import cn.itrip.common.DtoUtil;
import cn.itrip.dao.hotel.ItripHotelMapper;
import cn.itrip.dao.hotelorder.ItripHotelOrderMapper;
import cn.itrip.dao.hotelroom.ItripHotelRoomMapper;
import cn.itrip.dao.hoteltempstore.ItripHotelTempStoreMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
@Service
public class ItripHotelTempStoreServiceimpl implements ItripHotelTempStoreService {
    @Resource
    private ItripHotelTempStoreMapper itripHotelTempStoreMapper;
    @Resource
    private ItripHotelMapper itripHotelMapper;
    @Resource
    private ItripHotelRoomMapper itripHotelRoomMapper;
    @Resource
    private ItripHotelOrderMapper itripHotelOrderMapper;
    @Override
    public Dto getpreorderinfo(SearchHotelRoomVO searchHotelRoomVO, ItripUser user) {
        PreAddOrderVO order= null;
        try {
            order = new PreAddOrderVO();
            itripHotelTempStoreMapper.flushStore(searchHotelRoomVO);
            List<StoreVO> list=itripHotelTempStoreMapper.queryRoomStore(searchHotelRoomVO);
            int min=0;
            if (!list.isEmpty()){
                      min=list.get(0).getStore();
            }else{
                throw    new RuntimeException("库存不足");
            }
            ItripHotel hoteId = itripHotelMapper.queryById(searchHotelRoomVO.getHotelId());
            ItripHotelRoom roomId = itripHotelRoomMapper.getItripHotelRoomById(Long.parseLong(String.valueOf(searchHotelRoomVO.getRoomId())));
            order.setHotelName(hoteId.getHotelName());
            order.setCheckInDate(searchHotelRoomVO.getCheckInDate());
            order.setCheckOutDate(searchHotelRoomVO.getCheckOutDate());
            order.setStore(min);
            order.setPrice(BigDecimal.valueOf(roomId.getRoomPrice()));
            order.setCount(searchHotelRoomVO.getCount());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DtoUtil.returnDataSuccess(order);
    }



    @Override
    public Dto updateorderstatusandpaytype(Long id, Long payType) {
        Integer count=0;
        try {
              count = itripHotelOrderMapper.updateHotelOrderStatus(id, payType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DtoUtil.returnSuccess("支付成功",count);
    }


}
