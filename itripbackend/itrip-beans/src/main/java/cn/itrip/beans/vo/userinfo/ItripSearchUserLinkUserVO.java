package cn.itrip.beans.vo.userinfo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by donghai on 2017-06-08.
 */
@ApiModel(value = "ItripSearchUserLinkUserVO",description = "查询常用联系人")
public class ItripSearchUserLinkUserVO {
    @ApiModelProperty("[必填] 常用刚联系人姓名")
    private String linkUserName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLinkIdCard() {
        return linkIdCard;
    }

    public void setLinkIdCard(String linkIdCard) {
        this.linkIdCard = linkIdCard;
    }

    public String getLinkIdCardType() {
        return linkIdCardType;
    }

    public void setLinkIdCardType(String linkIdCardType) {
        this.linkIdCardType = linkIdCardType;
    }

    public String getLinkPhone() {
        return linkPhone;
    }

    public void setLinkPhone(String linkPhone) {
        this.linkPhone = linkPhone;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    private Long id;
    private String linkIdCard;
    private String linkIdCardType;
    private String linkPhone;
    private String modifiedBy;
    private String  modifyDate;
    private  String userId;



    public String getLinkUserName() {
        return linkUserName;
    }

    public void setLinkUserName(String linkUserName) {
        this.linkUserName = linkUserName;
    }
}
