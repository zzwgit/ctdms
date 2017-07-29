package com.jxufe.ctdms.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jxufe.ctdms.bean.UploadRecord;

public interface UploadRecordDao extends JpaRepository<UploadRecord, Serializable> {

}
