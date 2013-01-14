package com.tangpian.sna.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tangpian.sna.model.Relation;

public interface RelationDao extends JpaRepository<Relation, String> {

}
