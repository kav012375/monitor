package com.wulin.dal.Prosession.entity;

import java.io.Serializable;

/**
 * Created by zeusw on 2016/11/11.
 * 职业设定表
 */
public class ProsessionDO implements Serializable {
    private static final long serialVersionUID = 458045724375300044L;
    /**
     * 职业代码
     */
    private int P_ID;
    /**
     * 职业名称
     */
    private String P_NAME;
    /**
     * 职业图像地址
     */
    private String P_IMG;
    /**
     * 扩展字段
     */
    private String FEATURE;
    /**
     * 详情处的角色图像
     */
    private String P_DETAIL_IMG;
    /**
     * 角色描述
     */
    private String P_DETAIL_DES;
    /**
     * 角色专精描述
     */
    private String P_DETAIL_EMT;
    /**
     * 角色技能描述
     */
    private String P_DETAIL_SKILL;



    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getP_ID() {
        return P_ID;
    }

    public void setP_ID(int p_ID) {
        P_ID = p_ID;
    }

    public String getP_NAME() {
        return P_NAME;
    }

    public void setP_NAME(String p_NAME) {
        P_NAME = p_NAME;
    }

    public String getP_IMG() {
        return P_IMG;
    }

    public void setP_IMG(String p_IMG) {
        P_IMG = p_IMG;
    }

    public String getFEATURE() {
        return FEATURE;
    }

    public void setFEATURE(String FEATURE) {
        this.FEATURE = FEATURE;
    }

    public String getP_DETAIL_IMG() {
        return P_DETAIL_IMG;
    }

    public void setP_DETAIL_IMG(String p_DETAIL_IMG) {
        P_DETAIL_IMG = p_DETAIL_IMG;
    }

    public String getP_DETAIL_DES() {
        return P_DETAIL_DES;
    }

    public void setP_DETAIL_DES(String p_DETAIL_DES) {
        P_DETAIL_DES = p_DETAIL_DES;
    }

    public String getP_DETAIL_EMT() {
        return P_DETAIL_EMT;
    }

    public void setP_DETAIL_EMT(String p_DETAIL_EMT) {
        P_DETAIL_EMT = p_DETAIL_EMT;
    }

    public String getP_DETAIL_SKILL() {
        return P_DETAIL_SKILL;
    }

    public void setP_DETAIL_SKILL(String p_DETAIL_SKILL) {
        P_DETAIL_SKILL = p_DETAIL_SKILL;
    }
}
