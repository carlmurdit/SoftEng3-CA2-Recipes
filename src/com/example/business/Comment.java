package com.example.business;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Comment implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int commentid;
	private int recipeid;
	private String comment;
	private Timestamp datetime;
	private int userid;
	private String username;
	
	public Comment(int commentid, int recipeid, String comment, Timestamp timestamp, int userid, String username) {
		this.commentid = commentid;
		this.recipeid = recipeid;
		this.comment = comment;
		this.datetime = timestamp;
		this.userid = userid;
		this.username = username;
	}

	public int getCommentid() {
		return commentid;
	}

	public void setCommentid(int commentid) {
		this.commentid = commentid;
	}

	public int getRecipeid() {
		return recipeid;
	}

	public void setRecipeid(int recipeid) {
		this.recipeid = recipeid;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Timestamp getDatetime() {
		return datetime;
	}
	
	public String getDatetimeString() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(getDatetime());
	}

	public void setDatetime(Timestamp datetime) {
		this.datetime = datetime;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
	
}
