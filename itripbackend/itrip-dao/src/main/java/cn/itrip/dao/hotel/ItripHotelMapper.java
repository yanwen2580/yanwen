package cn.itrip.dao.hotel;
import cn.itrip.beans.pojo.ItripAreaDic;
import cn.itrip.beans.pojo.ItripHotel;
import cn.itrip.beans.pojo.ItripLabelDic;
import cn.itrip.beans.pojo.ItripUser;
import cn.itrip.beans.vo.hotel.ItripSearchFacilitiesHotelVO;
import cn.itrip.beans.vo.hotel.ItripSearchPolicyHotelVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ItripHotelMapper {

	public List getItripHotelById(@Param(value = "id") Long id)throws Exception;

	public ItripSearchFacilitiesHotelVO getItripHotelFacilitiesById(@Param(value = "id") Long id) throws Exception;

	public ItripSearchPolicyHotelVO queryHotelPolicy(@Param(value = "id") Long id) throws Exception;

	/*public List<ItripSearchDetailsHotelVO> queryHotelDetails(@Param(value="id") Long id) throws Exception;*/

	public List<ItripHotel>	getItripHotelListByMap(Map<String, Object> param)throws Exception;

	public Integer getItripHotelCountByMap(Map<String, Object> param)throws Exception;

	public Integer insertItripHotel(ItripHotel itripHotel)throws Exception;

	public Integer updateItripHotel(ItripHotel itripHotel)throws Exception;

	public Integer deleteItripHotelById(@Param(value = "id") Long id)throws Exception;

	/**
	 *  根据酒店ID获取商圈
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<ItripAreaDic> getHotelAreaByHotelId(@Param(value = "id") Long id)throws Exception;

	/**
	 *  根据酒店ID获取特色
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<ItripLabelDic> getHotelFeatureByHotelId(@Param(value = "id") Long id)throws Exception;


    List queryhotelroombyhotel(Long hotel);

    ItripHotel queryById(long hoteId);

    ItripUser queryByUsercode(String name);
}
