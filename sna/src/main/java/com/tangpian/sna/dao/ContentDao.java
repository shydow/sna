package com.tangpian.sna.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tangpian.sna.model.Content;

public interface ContentDao extends JpaRepository<Content, String> {

}
