package model;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Column;

import java.sql.Timestamp;


@MappedSuperclass
public abstract class AbstractClass {

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_at")
    private Timestamp createdAt;

    public String getCreatedBy() { return createdBy; }
    public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
}

