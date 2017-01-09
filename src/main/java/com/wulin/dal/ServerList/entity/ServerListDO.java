package com.wulin.dal.ServerList.entity;

import java.io.Serializable;

/**
 * Created by zeusw on 2016/8/31.
 */
public class ServerListDO implements Serializable {
    private static final long serialVersionUID = 458045724375300043L;
    private Integer id;
    private String host_address;
    private String host_name;
    private String host_status;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHost_address() {
        return host_address;
    }

    public void setHost_address(String host_address) {
        this.host_address = host_address;
    }

    public String getHost_name() {
        return host_name;
    }

    public void setHost_name(String host_name) {
        this.host_name = host_name;
    }

    public String getHost_status() {
        return host_status;
    }

    public void setHost_status(String host_status) {
        this.host_status = host_status;
    }
}
