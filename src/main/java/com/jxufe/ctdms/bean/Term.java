package com.jxufe.ctdms.bean;

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



/**
 * 学期
 * @author Moe
 *
 */
@Entity
@Table
public class Term {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="term_id")
	private long termId;
	
	@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL,mappedBy="term")
	List<Course> courses ;
	
	@Column(length=25,nullable=false)
	private String termName; 
	
	public long getTermId() {
		return termId;
	}
	public void setTermId(long termId) {
		this.termId = termId;
	}
	public String getTermName() {
		return termName;
	}
	public void setTermName(String termName) {
		this.termName = termName;
	}
	
	
	
}
