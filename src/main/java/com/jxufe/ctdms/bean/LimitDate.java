package com.jxufe.ctdms.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
@Entity
public class LimitDate {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;
	
	@Column(nullable = false,length=25)
	private String start; 
	@Column(nullable = false,length=25)
	private String end ;
	@Column(nullable = false,length=15)
	private String tab; 
	
	@Column(nullable = false)
	private int isWork = 1; 
	@Column(nullable = false,length=15)
	private long term ;
	 
	public LimitDate(String start, String end, String tab) {
		super();
		this.start = start;
		this.end = end;
		this.tab = tab;
	}
	public LimitDate() { 
		
	}
	@Override
	public String toString() { 
		return "[]"+start+" to "+end;
	}
	 
	public long getTerm() {
		return term;
	}

	public void setTerm(long term) {
		this.term = term;
	} 
	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = format(start);
	}

	public String getEnd() {
		return end;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTab() {
		return tab;
	}

	public void setTab(String tab) {
		this.tab = tab;
	}

	public int getIsWork() {
		return isWork;
	}

	public void setIsWork(int isWork) {
		this.isWork = isWork;
	}

	public void setEnd(String end) {
		this.end = format(end);
	}
	
	private String format(String date){
		String fdate = date.replace("年", "-").replace("月", "-").replace("日", "");
		return fdate;
	}
}
