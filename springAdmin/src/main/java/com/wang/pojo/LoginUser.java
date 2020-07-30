package com.wang.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 登录表
 * @author Administrator
 *
 */
public class LoginUser implements Serializable{
    private String lid;

    private String userName;

    private String passWord;

    private Integer count;

    private Date creattime;
    
    private String ruler;
    
    public String getLid() {
        return lid;
    }

    public void setLid(String lid) {
        this.lid = lid == null ? null : lid.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord == null ? null : passWord.trim();
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Date getCreattime() {
        return creattime;
    }

    public void setCreattime(Date creattime) {
        this.creattime = creattime;
    }

	public LoginUser(String lid, String userName, String passWord, Integer count, Date creattime,String ruler) {
		super();
		this.lid = lid;
		this.userName = userName;
		this.passWord = passWord;
		this.count = count;
		this.creattime = creattime;
		this.ruler=ruler;
	}

	public LoginUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "LoginUser [lid=" + lid + ", userName=" + userName + ", passWord=" + passWord + ", count=" + count
				+ ", creattime=" + creattime + "]ruler=="+ruler;
	}

	public String getRuler() {
		return ruler;
	}

	public void setRuler(String ruler) {
		this.ruler = ruler;
	}
    
    
    
}