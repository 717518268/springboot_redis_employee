package com.wang.pojo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class User   implements Serializable{
	
	private String id;

	private Integer number;

    private String username;

    private String sex;
   // @JsonFormat(pattern="MM-dd HH:mm",timezone="GMT+8")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date registertime;

    private Integer did;
    private Department department;

    private String yearanmethod;//工龄存储

    private Integer count;//休假天数

    public String getYearanmethod() {
        return yearanmethod;
    }

    public Integer getCount() {
        return count;
    }

    public void setYearanmethod(String yearanmethod) {
        this.yearanmethod = yearanmethod;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public User(String id) {
        this.id = id;
    }

   

    public User(String id, Integer number, String username, String sex, Date registertime, Integer did,
			Department department, String yearanmethod, Integer count) {
		super();
		this.id = id;
		this.number = number;
		this.username = username;
		this.sex = sex;
		this.registertime = registertime;
		this.did = did;
		this.department = department;
		this.yearanmethod = yearanmethod;
		this.count = count;
	}

	public User(String id, Integer number, String username, String sex, Date registertime, Integer did,
			Department department) {
		super();
		this.id = id;
		this.number = number;
		this.username = username;
		this.sex = sex;
		this.registertime = registertime;
		this.did = did;
		this.department = department;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

	public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", number=" + number +
                ", username='" + username + '\'' +
                ", sex='" + sex + '\'' +
                ", registertime=" + registertime +
                ", did=" + did +
                ", department=" + department +
                ", yearanmethod='" + yearanmethod + '\'' +
                ", count=" + count +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Department getDepartment() {
        return department;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public Date getRegistertime() {
        return registertime;
    }

    public void setRegistertime(Date registertime) {
        this.registertime = registertime;
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }
    
     

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}