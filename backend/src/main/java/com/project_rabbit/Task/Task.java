package com.project_rabbit.Task;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

@Data
@Entity
public class Task {

    @Id
    @GeneratedValue
    private Integer taskId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "due_date", nullable = true)
    private String dueDate;

    @Column(name = "created_at", updatable = false, nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "project", nullable = false)
    private int projectId;

    @Column(name = "assigned_to", nullable = true)
    private int assignedTo;

    @Column(name = "status", nullable = false)
    private int status;
}
