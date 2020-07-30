package com.wang.pojo;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class LeaveRecords {
    private String uid;

    private String lid;

    private Integer lcount;

    private String leave_reason;
    @JSONField(format ="yyyy-MM-dd")
    private Date leaveYearstart;
    @JSONField(format ="yyyy-MM-dd")
    private Date leaveYearend;
    @JSONField(format ="yyyy-MM-dd")
    private Date inputTime;
    
    private String stauts;
    
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public String getLid() {
        return lid;
    }

    public void setLid(String lid) {
        this.lid = lid == null ? null : lid.trim();
    }

    public Integer getLcount() {
        return lcount;
    }

    public void setLcount(Integer lcount) {
        this.lcount = lcount;
    }

    public Date getLeaveYearstart() {
        return leaveYearstart;
    }

    public void setLeaveYearstart(Date leaveYearstart) {
        this.leaveYearstart = leaveYearstart;
    }

    public Date getLeaveYearend() {
        return leaveYearend;
    }

    public void setLeaveYearend(Date leaveYearend) {
        this.leaveYearend = leaveYearend;
    }

    public Date getInputTime() {
        return inputTime;
    }

    public void setInputTime(Date inputTime) {
        this.inputTime = inputTime;
    }

	public LeaveRecords(String uid, String lid, Integer lcount, Date leaveYearstart, Date leaveYearend,
			Date inputTime,String stauts) {
		super();
		this.uid = uid;
		this.lid = lid;
		this.lcount = lcount;
		this.leaveYearstart = leaveYearstart;
		this.leaveYearend = leaveYearend;
		this.inputTime = inputTime;
		this.stauts=stauts;
	}

	public LeaveRecords() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "LeaveRecords [uid=" + uid + ", lid=" + lid + ", lcount=" + lcount + ", leave_reason=" + leave_reason
				+ ", leaveYearstart=" + leaveYearstart + ", leaveYearend=" + leaveYearend + ", inputTime=" + inputTime
				+ "]stauts="+stauts;
	}

	public LeaveRecords(String uid, String lid, Integer lcount, String leave_reason, Date leaveYearstart,
			Date leaveYearend, Date inputTime) {
		super();
		this.uid = uid;
		this.lid = lid;
		this.lcount = lcount;
		this.leave_reason = leave_reason;
		this.leaveYearstart = leaveYearstart;
		this.leaveYearend = leaveYearend;
		this.inputTime = inputTime;
	}

	public String getLeave_reason() {
		return leave_reason;
	}

	public void setLeave_reason(String leave_reason) {
		this.leave_reason = leave_reason;
	}

	public String getStauts() {
		return stauts;
	}

	public void setStauts(String stauts) {
		this.stauts = stauts;
	}
    
    
}