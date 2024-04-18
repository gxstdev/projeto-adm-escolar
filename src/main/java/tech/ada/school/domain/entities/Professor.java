package tech.ada.school.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "professores")
public class Professor {
    @Column(name = "nomeCompleto")
    private String nome;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String cpf;

    private String email;

    @CreationTimestamp
    private Instant createdAt;
}
