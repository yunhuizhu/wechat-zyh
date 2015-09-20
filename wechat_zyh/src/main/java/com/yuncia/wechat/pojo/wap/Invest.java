package com.yuncia.wechat.pojo.wap;

/**
 * Created with IntelliJ IDEA.
 * User: zhu
 * Date: 15-5-4
 * Time: 上午9:46
 * To change this template use File | Settings | File Templates.
 */
public class Invest {
    private String investName;
    private String identityCode;
    private String customerId;
    private String typeId;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getInvestName() {
        return investName;
    }

    public void setInvestName(String investName) {
        this.investName = investName;
    }

    public String getIdentityCode() {
        return identityCode;
    }

    public void setIdentityCode(String identityCode) {
        this.identityCode = identityCode;
    }
}
