package com.jxufe.ctdms.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 消息通知 DTO
 * 
 * @author Moe
 * 
 */
@Entity
@Table
public class Notices {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
/*
	@ManyToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name = "type_id",nullable=false)
	private NoticesType noticesType = new NoticesType();*/

	@Column(nullable = true)
	private long userId; // 针对某个人的消息 noticestype.rank == 1 生效

	@Column(nullable = false)
	private int level; // 消息紧急程度
	@Column(length = 25, nullable = false)
	private String title;
	@Column(length = 255, nullable = true)
	private String message;
	@Column(length = 25, nullable = false)
	private String time;
	@Column(length = 25, nullable = true)
	private String author;

	@Override
	public String toString() {
		return title + "-" + message + "-" + time + "-" + level + "-" + author
				+ "-" ;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
 
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

}
