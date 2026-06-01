package com.itdestek.itdestek.repository;

import com.itdestek.itdestek.entity.SupportTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface IssueRepository extends JpaRepository<SupportTicket, Long> {

    List<SupportTicket> findByResolved(boolean resolved);

    List<SupportTicket> findByTitleContainingIgnoreCase(String title);

    List<SupportTicket> findByTitleContainingIgnoreCaseAndResolved(String title, boolean resolved);

    long countByResolved(boolean resolved);

    boolean existsByTitleIgnoreCase(String title);

    List<SupportTicket> findTop5ByOrderByIdDesc();
}