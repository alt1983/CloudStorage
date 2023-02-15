package ru.netology.cloudstorage.repository;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tokens", schema = "netology")
public class Tokens {
    @Id
    @GeneratedValue
    private int id;
    @Column(nullable = false)
    private String token;
    @Column(nullable = false)
    private boolean active;
}
