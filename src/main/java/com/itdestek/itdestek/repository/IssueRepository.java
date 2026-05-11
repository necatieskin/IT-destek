package com.itdestek.itdestek.repository;

import com.itdestek.itdestek.entity.SupportTicket;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IssueRepository extends JpaRepository<SupportTicket, Long> {

}