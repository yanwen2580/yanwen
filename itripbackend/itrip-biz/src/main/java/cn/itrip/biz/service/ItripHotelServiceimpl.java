package cn.itrip.biz.service;

import cn.itrip.beans.dto.Dto;
import cn.itrip.beans.pojo.ItripHotelRoom;
import cn.itrip.beans.pojo.ItripUser;
import cn.itrip.common.DtoUtil;
import cn.itrip.dao.hotel.ItripHotelMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ItripHotelServiceimpl implements  ItripHotelService{
    @Resource
    private  ItripHotelMapper  itripHotelMapper;

    @Override
    public Dto getItripHotelById(long id) {
        List itripHotelById=null;
                try {
                    itripHotelById = itripHotelMapper.getItripHotelById(id);
                } catch (Exception e) {
                e.printStackTrace();
            }
                return DtoUtil.returnDataSuccess(itripHotelById);
    }

    @Override
    public Dto queryhotelroombyhotel(Long hotel) {
        List <ItripHotelRoom>queryhotelroombyhotel = itripHotelMapper.queryhotelroombyhotel(hotel);
        List lists =new ArrayList();
        for (ItripHotelRoom itripHotelRoom:queryhotelroombyhotel){
            List listd=new ArrayList();
            listd.add(itripHotelRoom);
            lists.add(listd);
        }
        return DtoUtil.returnDataSuccess(lists);
    }

    @Override
    public Dto queryByUsercode(String name) {
        ItripUser user = itripHotelMapper.queryByUsercode(name);
        if (user==null) {
            return DtoUtil.returnSuccess("邮箱可用！");
        }else {
            return DtoUtil.returnFail("邮箱不可用","500001");
        }
    }


}
