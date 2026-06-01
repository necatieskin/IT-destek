package com.itdestek.itdestek.controller;

import jakarta.validation.Valid;
import com.itdestek.itdestek.dto.IssueRequest;
import com.itdestek.itdestek.entity.SupportTicket;
import com.itdestek.itdestek.service.IssueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/issues")
public class IssueController {

    private final IssueService issueService;

    public IssueController(IssueService issueService) {
        this.issueService = issueService;
    }

    @GetMapping
    public List<SupportTicket> getAllIssues() {
        return issueService.getAllIssues();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupportTicket> getIssueById(@PathVariable Long id) {
        return issueService.getIssueById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<SupportTicket> createIssue(@Valid @RequestBody IssueRequest issueRequest) {
        SupportTicket createdTicket = issueService.createIssue(issueRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTicket);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupportTicket> updateIssue(
            @PathVariable Long id,
            @Valid @RequestBody IssueRequest issueRequest
    ) {
        SupportTicket updatedTicket = issueService.updateIssue(id, issueRequest);

        if (updatedTicket != null) {
            return ResponseEntity.ok(updatedTicket);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteIssue(@PathVariable Long id) {
        boolean deleted = issueService.deleteIssue(id);

        if (deleted) {
            return ResponseEntity.ok("Arıza kaydı silindi.");
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/resolved/{resolved}")
    public List<SupportTicket> getIssuesByResolvedStatus(@PathVariable boolean resolved) {
        return issueService.getIssuesByResolvedStatus(resolved);
    }

    @GetMapping("/search")
    public List<SupportTicket> searchIssuesByTitle(@RequestParam String title) {
        return issueService.searchIssuesByTitle(title);
    }

    @GetMapping("/filter")
    public List<SupportTicket> filterIssues(
            @RequestParam String title,
            @RequestParam boolean resolved
    ) {
        return issueService.filterIssuesByTitleAndResolved(title, resolved);
    }

    @GetMapping("/count")
    public long countIssuesByResolvedStatus(@RequestParam boolean resolved) {
        return issueService.countIssuesByResolvedStatus(resolved);
    }

    @GetMapping("/exists")
    public boolean existsIssueByTitle(@RequestParam String title) {
        return issueService.existsIssueByTitle(title);
    }

    @GetMapping("/latest")
    public List<SupportTicket> getLatestFiveIssues() {
        return issueService.getLatestFiveIssues();
    }
}