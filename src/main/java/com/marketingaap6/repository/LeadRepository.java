package com.marketingaap6.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marketingaap6.entity.Lead;

public interface LeadRepository extends JpaRepository<Lead, Long> {

}
