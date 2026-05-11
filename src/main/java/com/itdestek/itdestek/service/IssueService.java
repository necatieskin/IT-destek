package com.itdestek.itdestek.service;

import com.itdestek.itdestek.dto.IssueRequest;
import com.itdestek.itdestek.entity.SupportTicket;
import com.itdestek.itdestek.repository.IssueRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IssueService {

    private final IssueRepository issueRepository;

    // Constructor Injection: Repository'yi constructor üzerinden bağlıyoruz
    public IssueService(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    // Tüm biletleri listeleme
    public List<SupportTicket> getAllIssues() {
        return issueRepository.findAll();
    }

    // ID'ye göre tek bir bilet bulma
    public Optional<SupportTicket> getIssueById(Long id) {
        return issueRepository.findById(id);
    }

    // Yeni bilet oluşturma (Postman'den gelen IssueRequest'i veritabanı nesnesi olan SupportTicket'a çeviriyoruz)
    public SupportTicket createIssue(IssueRequest issueRequest) {
        SupportTicket ticket = new SupportTicket();

        ticket.setTitle(issueRequest.getTitle());
        ticket.setLocation(issueRequest.getLocation());
        ticket.setDescription(issueRequest.getDescription());
        ticket.setPriority(issueRequest.getPriority());

        // Eğer çözüldü bilgisi gelmemişse varsayılan olarak 'false' yapıyoruz
        if (issueRequest.getResolved() != null) {
            ticket.setResolved(issueRequest.getResolved());
        } else {
            ticket.setResolved(false);
        }

        return issueRepository.save(ticket);
    }

    // Var olan bileti güncelleme
    public SupportTicket updateIssue(Long id, IssueRequest issueRequest) {
        Optional<SupportTicket> optionalTicket = issueRepository.findById(id);

        if (optionalTicket.isPresent()) {
            SupportTicket existingTicket = optionalTicket.get();

            existingTicket.setTitle(issueRequest.getTitle());
            existingTicket.setLocation(issueRequest.getLocation());
            existingTicket.setDescription(issueRequest.getDescription());
            existingTicket.setPriority(issueRequest.getPriority());

            if (issueRequest.getResolved() != null) {
                existingTicket.setResolved(issueRequest.getResolved());
            }

            return issueRepository.save(existingTicket);
        }

        return null; // Bilet bulunamazsa null dönüyoruz
    }

    // Bilet silme
    public boolean deleteIssue(Long id) {
        Optional<SupportTicket> optionalTicket = issueRepository.findById(id);

        if (optionalTicket.isPresent()) {
            issueRepository.deleteById(id);
            return true;
        }

        return false; // Silinecek bilet bulunamadıysa false dönüyoruz
    }
}
