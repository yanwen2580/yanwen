package cn.itrip.biz.service;

import cn.itrip.beans.dto.Dto;
import cn.itrip.beans.vo.userinfo.ItripSearchUserLinkUserVO;
import cn.itrip.common.DtoUtil;
import cn.itrip.dao.userlinkuser.ItripUserLinkUserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class ItripUserLinkUserServiceimpl implements  ItripUserLinkUserService{
    @Resource
    private ItripUserLinkUserMapper itripUserLinkUserMapper;
    @Override
    public Dto queryuserlinkuser(Long id) {
        List <ItripSearchUserLinkUserVO>itripUserLinkUserById = null;
        ItripSearchUserLinkUserVO   userLinkUserVO=new ItripSearchUserLinkUserVO();
        try {
              itripUserLinkUserById = itripUserLinkUserMapper.getItripUserLinkUserById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DtoUtil.returnDataSuccess(itripUserLinkUserById);
    }
}
