package com.wang.pojo;

import java.io.Serializable;

public class Department  implements Serializable{
    private Integer nid;
    private Integer id;

    private String dname;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname == null ? null : dname.trim();
    }
    public Integer getNid() {
        return nid;
    }

    public void setNid(Integer nid) {
        this.nid = nid;
    }
}