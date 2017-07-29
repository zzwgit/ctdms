package com.jxufe.ctdms.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

/**
 * 上传文档记录
 * 
 * @author Moe
 * 
 */
@Entity
@Table
public class UploadRecord {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user; // 上传用户

	@ManyToOne
	@JoinColumn(name="type_id")
	private DocType docType;
	
	@Column(nullable = false, length = 20)
	private String date; // 上传时间
	
	@Transient
	private MultipartFile file;
	@Transient
	private long cid;
	@Transient
	private String tab;
	@Column(nullable = false, length = 80)
	private String name;			//文档名
	@Column(nullable = false, length = 255)
	private String path ;			//保存路径
	@Column(nullable = false)
	private long size;				//大小 
	
	@ManyToOne
	@JoinColumn(name = "term_id")
	private Term term; // 学期

	public UploadRecord() {
		// TODO Auto-generated constructor stub
	}
	public UploadRecord(MultipartFile file, String tab, String path ,long cid ) {
		super();
		this.file = file;
		this.tab = tab;
		this.path = path; 
		this.cid = cid;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCid() {
		return cid;
	}
	public void setCid(long cid) {
		this.cid = cid;
	}
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	} 

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getTab() {
		return tab;
	}

	public void setTab(String tab) {
		this.tab = tab;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public DocType getDocType() {
		return docType;
	}

	public void setDocType(DocType docType) {
		this.docType = docType;
	}

	public Term getTerm() {
		return term;
	}

	public void setTerm(Term term) {
		this.term = term;
	}

}
