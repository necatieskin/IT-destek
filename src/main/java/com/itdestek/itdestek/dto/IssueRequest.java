package com.itdestek.itdestek.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class IssueRequest {

    @NotBlank(message = "Arıza başlığı boş bırakılamaz.")
    @Size(min = 3, max = 100, message = "Başlık 3 ile 100 karakter arasında olmalıdır.")
    private String title;

    @NotBlank(message = "Lütfen arızanın olduğu birimi/konumu belirtin.")
    private String location;

    @Size(max = 500, message = "Açıklama en fazla 500 karakter olabilir.")
    private String description;

    private String priority;
    private Boolean resolved;

    public IssueRequest() {
    }

    public IssueRequest(String title, String location, String description, String priority, Boolean resolved) {
        this.title = title;
        this.location = location;
        this.description = description;
        this.priority = priority;
        this.resolved = resolved;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Boolean getResolved() {
        return resolved;
    }

    public void setResolved(Boolean resolved) {
        this.resolved = resolved;
    }
}