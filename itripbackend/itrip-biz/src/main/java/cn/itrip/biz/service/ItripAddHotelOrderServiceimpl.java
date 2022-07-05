package cn.itrip.biz.service;

import cn.itrip.beans.dto.Dto;
import cn.itrip.beans.pojo.ItripHotelOrder;
import cn.itrip.beans.pojo.ItripHotelRoom;
import cn.itrip.beans.vo.order.ItripAddHotelOrderVO;
import cn.itrip.beans.vo.order.ItripModifyHotelOrderVO;
import cn.itrip.common.DateUtil;
import cn.itrip.common.DtoUtil;
import cn.itrip.dao.hotelorder.ItripHotelOrderMapper;
import cn.itrip.dao.hotelroom.ItripHotelRoomMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;
@Service
public class ItripAddHotelOrderServiceimpl implements ItripAddHotelOrderService {
    @Resource
    private ItripHotelOrderMapper itripHotelOrderMapper;
    @Resource
    private ItripHotelRoomMapper itripHotelRoomMapper;

    @Override
    public Dto add(ItripAddHotelOrderVO orderVO) {
        Integer mun=0;
        Integer count =0;
        ItripModifyHotelOrderVO orderVO1=new ItripModifyHotelOrderVO();
        try {
            ItripHotelRoom itripHotelRoomById = itripHotelRoomMapper.getItripHotelRoomById(orderVO.getRoomId());
            orderVO1.setBookingDays(DateUtil.getBetweenDates((orderVO.getCheckInDate()),orderVO.getCheckOutDate()).size());
            orderVO1.setPayAmount(itripHotelRoomById.getRoomPrice());
            orderVO1.setUserId(orderVO.getUserId());
            orderVO1.setLinkUser(orderVO.getLinkUser().stream().map(item->item.getLinkUserName()).collect(Collectors.joining(",")));
            orderVO1.setOrderNo(UUID.randomUUID().toString().replace("-",""));
            orderVO1.setOrderStatus(0);
            orderVO1.setPayAmount(itripHotelRoomById.getRoomPrice()*orderVO.getCount());
            orderVO1.setHotelName(itripHotelRoomById.getRoomTitle());
            orderVO1.setCount(orderVO.getCount());
            orderVO1.setCreationDate(new Date());
            orderVO1.setRoomId(itripHotelRoomById.getId());
            orderVO1.setCheckInDate(orderVO.getCheckInDate());
            orderVO1.setCheckOutDate(orderVO.getCheckOutDate());
               mun = itripHotelOrderMapper.insertItripHotelOrder(orderVO1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (mun!=0){
            return DtoUtil.returnSuccess("添加成功",orderVO1);
        }else {
            return DtoUtil.returnFail("添加失败","10002" );
        }
    }

    @Override
    public Dto getItripHotelOrderRoomInfoById(Long id) {
        ItripHotelOrder itripHotelOrderById=null;
        try {
              itripHotelOrderById = itripHotelOrderMapper.getItripHotelOrderById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DtoUtil.returnDataSuccess(itripHotelOrderById);
    }
}
