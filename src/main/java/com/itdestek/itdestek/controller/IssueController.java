package com.itdestek.itdestek.controller;

import jakarta.validation.Valid; //
import com.itdestek.itdestek.dto.IssueRequest;
import com.itdestek.itdestek.entity.SupportTicket;
import com.itdestek.itdestek.service.IssueService;
import org.springframework.http.HttpStatus; //
import org.springframework.http.ResponseEntity; //
import org.springframework.web.bind.annotation.*;

import java.util.List; //

@RestController //
@RequestMapping("/issues") //
public class IssueController {

    private final IssueService issueService; // Servis katmanı bağlantısı

    // Constructor Injection
    public IssueController(IssueService issueService) {
        this.issueService = issueService;
    }

    // 1. Tüm biletleri getir
    @GetMapping
    public List<SupportTicket> getAllIssues() {
        return issueService.getAllIssues();
    }

    // 2. ID'ye göre tek bir bilet getir
    @GetMapping("/{id}")
    public ResponseEntity<SupportTicket> getIssueById(@PathVariable Long id) {
        return issueService.getIssueById(id)
                .map(ticket -> ResponseEntity.ok(ticket))
                .orElse(ResponseEntity.notFound().build());
    }

    // 3. Yeni bilet oluştur
    @PostMapping
    public ResponseEntity<SupportTicket> createIssue(@Valid @RequestBody IssueRequest issueRequest) {
        SupportTicket createdTicket = issueService.createIssue(issueRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTicket);
    }

    // 4. Var olan bileti güncelle
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

    // 5. Bileti sil
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteIssue(@PathVariable Long id) {
        boolean deleted = issueService.deleteIssue(id);

        if (deleted) {
            return ResponseEntity.ok("Arıza kaydı silindi."); //
        }

        return ResponseEntity.notFound().build();
    }
}
