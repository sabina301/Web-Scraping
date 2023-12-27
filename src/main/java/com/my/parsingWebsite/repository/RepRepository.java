package com.my.parsingWebsite.repository;

import com.my.parsingWebsite.entity.RepEntity;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


@Repository
public interface RepRepository extends JpaRepository<RepEntity, Long>{
    Optional<RepEntity> findByRepName(String repName);
}
