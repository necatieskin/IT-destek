package com.itdestek.itdestek.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class SupportTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String location;    // Arıza yeri
    private String priority;    // Öncelik durumu
    private boolean resolved;   // Çözüldü mü? (Hocanın 'completed' alanı gibi)

    // 1. Boş Constructor (Hibernate veritabanından veri çekerken bu boş kalıba ihtiyaç duyar)
    public SupportTicket() {
    }

    // 2. Dolu Constructor (Kod içinde yeni bir nesne oluştururken tüm bilgileri doldurmanı sağlar)
    public SupportTicket(Long id, String title, String description, String location, String priority, boolean resolved) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.priority = priority;
        this.resolved = resolved;
    }

    // 3. Getter ve Setter Metotları (Private değişkenlere güvenli erişim sağlar)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public boolean isResolved() {
        return resolved;
    }

    public void setResolved(boolean resolved) {
        this.resolved = resolved;
    }
}
