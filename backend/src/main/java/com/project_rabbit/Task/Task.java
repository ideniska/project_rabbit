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

    @Column(name = "start_date", nullable = true)
    private String startDate;

    @Column(name = "due_date", nullable = true)
    private String dueDate;

    @Column(name = "created_at", updatable = false, nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "project", nullable = false)
    private Integer projectId;

    @Column(name = "epic", nullable = true)
    private Integer epicId;

    @Column(name = "assigned_to", nullable = true)
    private Integer assignedTo;

    @Column(name = "status", nullable = false)
    private Integer status;
}
