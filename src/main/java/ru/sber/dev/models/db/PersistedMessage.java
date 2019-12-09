package ru.sber.dev.models.db;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "messages")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersistedMessage {
    @Id
    private Long id;
    private String payload;
    @Column(nullable = false, columnDefinition = "timestamp DEFAULT now()")
    private Date createDate;
}
