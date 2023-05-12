package com.net.assign5backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.net.assign5backend.entitity.BlogEntity;

@Repository
public interface BlogRepository extends JpaRepository<BlogEntity, Integer>{
    public BlogEntity findByTitle(String title);
    public List<BlogEntity> findByEmail(String email);
    public List<BlogEntity> findByTags(String tags);
}
