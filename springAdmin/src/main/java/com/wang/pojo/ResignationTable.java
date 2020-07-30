package com.wang.pojo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 离职表
 * @author Administrator
 *
 */
public class ResignationTable implements Serializable {
    private String qId;

    private String uId;

    private String userName;

    private String departmentid;

    private String workingYears;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date entryTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date exitTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endupdatetime;

    public String getqId() {
        return qId;
    }

    public void setqId(String qId) {
        this.qId = qId == null ? null : qId.trim();
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId == null ? null : uId.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(String departmentid) {
        this.departmentid = departmentid;
    }

    public String getWorkingYears() {
        return workingYears;
    }

    public void setWorkingYears(String workingYears) {
        this.workingYears = workingYears == null ? null : workingYears.trim();
    }

    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    public Date getExitTime() {
        return exitTime;
    }

    public void setExitTime(Date exitTime) {
        this.exitTime = exitTime;
    }

    public Date getEndupdatetime() {
        return endupdatetime;
    }

    public void setEndupdatetime(Date endupdatetime) {
        this.endupdatetime = endupdatetime;
    }
}