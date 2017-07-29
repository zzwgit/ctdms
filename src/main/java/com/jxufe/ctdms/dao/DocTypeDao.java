package com.jxufe.ctdms.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jxufe.ctdms.bean.DocType;

public interface DocTypeDao extends JpaRepository<DocType, Serializable> {

	DocType findByTabName(String tabName);
}
