package com.jxufe.ctdms.bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table
public class NoticesType{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int typeId;
	
	@OneToMany(fetch = FetchType.EAGER,cascade=CascadeType.ALL) 
	@JsonManagedReference 
	private List<Notices> notices = new ArrayList<>();
 
	@Column(nullable=false)
	private int rank;		//通知 查看等级 0-for all    1-for person
	
	@Column(length=25,nullable=false)
	private String typeName;


	public int getTypeId() {
		return typeId;
	}


	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
 

	public List<Notices> getNotices() {
		return notices;
	}


	public void setNotices(List<Notices> notices) {
		this.notices = notices;
	}


	public String getTypeName() {
		return typeName;
	}


	public int getRank() {
		return rank;
	}


	public void setRank(int rank) {
		this.rank = rank;
	}


	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	
}
