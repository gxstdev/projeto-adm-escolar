package tech.ada.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.ada.school.domain.entities.Professor;

import java.util.Optional;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Integer> {
     Optional<Professor> findByCpf(String cpf);
     boolean existsByCpf(String cpf);
}
