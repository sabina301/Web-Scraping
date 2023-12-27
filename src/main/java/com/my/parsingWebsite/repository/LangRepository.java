package com.my.parsingWebsite.repository;

import com.my.parsingWebsite.entity.LangEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LangRepository extends JpaRepository<LangEntity, Long> {
}
