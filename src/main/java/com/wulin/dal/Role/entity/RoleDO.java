package com.wulin.dal.Role.entity;

import java.io.Serializable;


/**
 * Created by zeusw on 2016/11/11.
 * 角色表
 */
public class RoleDO implements Serializable {
    private static final long serialVersionUID = 458045724375300045L;
    /**
     * 用户ID
     */
    private int userId;
    /**
     * 用户对应的角色ID，自增长
     */
    private int RoleId;
    /**
     * 角色名
     */
    private String RoleName;
    /**
     * 角色的职业，对应职业代码
     */
    private int RoleProsession;
    /**
     * 力量
     */
    private Long STR;
    /**
     * 敏捷
     */
    private Long AGI;
    /**
     * 智力
     */
    private Long INT;
    /**
     * 耐力
     */
    private Long STA;
    /**
     * 生命值
     */
    private Long HP;
    /**
     * 能量值
     */
    private Long MP;
    /**
     * 命中
     */
    private Long HIT;
    /**
     * 躲闪
     */
    private Long DOD;
    /**
     * 暴击
     */
    private Long CRI;
    /**
     * 破甲
     */
    private Long WRE;
    /**
     * 吸血
     */
    private Long VAM;
    /**
     * 吸蓝
     */
    private Long BLS;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRoleId() {
        return RoleId;
    }

    public void setRoleId(int roleId) {
        RoleId = roleId;
    }

    public String getRoleName() {
        return RoleName;
    }

    public void setRoleName(String roleName) {
        RoleName = roleName;
    }

    public int getRoleProsession() {
        return RoleProsession;
    }

    public void setRoleProsession(int roleProsession) {
        RoleProsession = roleProsession;
    }

    public Long getSTR() {
        return STR;
    }

    public void setSTR(Long STR) {
        this.STR = STR;
    }

    public Long getAGI() {
        return AGI;
    }

    public void setAGI(Long AGI) {
        this.AGI = AGI;
    }

    public Long getSTA() {
        return STA;
    }

    public void setSTA(Long STA) {
        this.STA = STA;
    }

    public Long getHP() {
        return HP;
    }

    public void setHP(Long HP) {
        this.HP = HP;
    }

    public Long getMP() {
        return MP;
    }

    public void setMP(Long MP) {
        this.MP = MP;
    }

    public Long getHIT() {
        return HIT;
    }

    public void setHIT(Long HIT) {
        this.HIT = HIT;
    }

    public Long getDOD() {
        return DOD;
    }

    public void setDOD(Long DOD) {
        this.DOD = DOD;
    }

    public Long getCRI() {
        return CRI;
    }

    public void setCRI(Long CRI) {
        this.CRI = CRI;
    }

    public Long getWRE() {
        return WRE;
    }

    public void setWRE(Long WRE) {
        this.WRE = WRE;
    }

    public Long getVAM() {
        return VAM;
    }

    public void setVAM(Long VAM) {
        this.VAM = VAM;
    }

    public Long getBLS() {
        return BLS;
    }

    public void setBLS(Long BLS) {
        this.BLS = BLS;
    }

    public Long getINT() {
        return INT;
    }

    public void setINT(Long INT) {
        this.INT = INT;
    }
}
