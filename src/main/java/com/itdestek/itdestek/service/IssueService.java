package com.itdestek.itdestek.service;

import com.itdestek.itdestek.dto.IssueRequest;
import com.itdestek.itdestek.entity.SupportTicket;
import com.itdestek.itdestek.repository.IssueRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class IssueService {

    private final IssueRepository issueRepository;

    public IssueService(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    public List<SupportTicket> getAllIssues() {
        return issueRepository.findAll();
    }

    public Optional<SupportTicket> getIssueById(Long id) {
        return issueRepository.findById(id);
    }

    @Transactional
    public SupportTicket createIssue(IssueRequest issueRequest) {
        SupportTicket ticket = new SupportTicket();

        ticket.setTitle(issueRequest.getTitle());
        ticket.setDescription(issueRequest.getDescription());
        ticket.setLocation(issueRequest.getLocation());
        ticket.setPriority(issueRequest.getPriority());

        if (issueRequest.getResolved() != null) {
            ticket.setResolved(issueRequest.getResolved());
        } else {
            ticket.setResolved(false);
        }

        return issueRepository.save(ticket);
    }

    @Transactional
    public SupportTicket updateIssue(Long id, IssueRequest issueRequest) {
        Optional<SupportTicket> optionalTicket = issueRepository.findById(id);

        if (optionalTicket.isPresent()) {
            SupportTicket existingTicket = optionalTicket.get();

            existingTicket.setTitle(issueRequest.getTitle());
            existingTicket.setDescription(issueRequest.getDescription());
            existingTicket.setLocation(issueRequest.getLocation());
            existingTicket.setPriority(issueRequest.getPriority());

            if (issueRequest.getResolved() != null) {
                existingTicket.setResolved(issueRequest.getResolved());
            }

            return issueRepository.save(existingTicket);
        }

        return null;
    }

    @Transactional
    public boolean deleteIssue(Long id) {
        Optional<SupportTicket> optionalTicket = issueRepository.findById(id);

        if (optionalTicket.isPresent()) {
            issueRepository.deleteById(id);
            return true;
        }

        return false;
    }

    public List<SupportTicket> getIssuesByResolvedStatus(boolean resolved) {
        return issueRepository.findByResolved(resolved);
    }

    public List<SupportTicket> searchIssuesByTitle(String title) {
        return issueRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<SupportTicket> filterIssuesByTitleAndResolved(String title, boolean resolved) {
        return issueRepository.findByTitleContainingIgnoreCaseAndResolved(title, resolved);
    }

    public long countIssuesByResolvedStatus(boolean resolved) {
        return issueRepository.countByResolved(resolved);
    }

    public boolean existsIssueByTitle(String title) {
        return issueRepository.existsByTitleIgnoreCase(title);
    }

    public List<SupportTicket> getLatestFiveIssues() {
        return issueRepository.findTop5ByOrderByIdDesc();
    }
}