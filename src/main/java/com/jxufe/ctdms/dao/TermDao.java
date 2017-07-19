package com.jxufe.ctdms.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jxufe.ctdms.bean.Term;

public interface TermDao extends JpaRepository<Term, Serializable> {

 
}
