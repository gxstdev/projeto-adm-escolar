package tech.ada.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.ada.school.domain.entities.Aluno;

import java.util.List;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Integer> {
    List<Aluno> findAllByTurma(Integer turma);
    List<Aluno> findAllByNomeAndTurma(String nome, Integer turma);
}
