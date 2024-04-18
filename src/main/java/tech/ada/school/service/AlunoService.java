package tech.ada.school.service;

import org.springframework.stereotype.Service;
import tech.ada.school.domain.dto.AlunoDto;
import tech.ada.school.domain.dto.exception.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlunoService implements IAlunoService{
    List<AlunoDto> alunos = new ArrayList<>();
    private int geradorId = 0;
    @Override
    public AlunoDto criarAluno(AlunoDto aluno) {
        AlunoDto result = new AlunoDto(aluno.getNome(), geradorId, aluno.getCpf(),
                aluno.getEmail(), aluno.getTurma(), null);
        alunos.add(result);
        geradorId++;
        return result;
    }

    @Override
    public List<AlunoDto> lerAlunos() {
        return alunos;
    }

    @Override
    public AlunoDto buscarAlunoPorId(int id) throws NotFoundException {
        for (int i = 0; i < alunos.size(); i++) {
            if (alunos.get(i).getId() == id){
                return alunos.get(i);
            }
        }
        throw new NotFoundException(AlunoDto.class, String.valueOf(id));
    }

    @Override
    public AlunoDto atualizarAluno(int id, AlunoDto alunoDto) {
        for (AlunoDto aluno : alunos){
            if (aluno.getId() == id){
                aluno.setNome(alunoDto.getNome());
                aluno.setCpf(alunoDto.getCpf());
                aluno.setEmail(alunoDto.getEmail());
                aluno.setTurma(alunoDto.getTurma());
                return aluno;
            }
        }
        return null;
    }

    @Override
    public void deletarAluno(int id) throws NotFoundException {
        AlunoDto aluno = buscarAlunoPorId(id);
        alunos.remove(aluno);

    }

    @Override
    public List<AlunoDto> buscarAlunoPorTurma(int turma) throws NotFoundException {
        List<AlunoDto> alunosPorTuma = new ArrayList<>();
        for (AlunoDto aluno : alunos){
            if (aluno.getTurma() == turma){
                alunosPorTuma.add(aluno);
            }
        }
        if (alunosPorTuma.isEmpty()){
            throw new NotFoundException(AlunoDto.class, String.valueOf(turma));
        }
        return alunosPorTuma;
    }

    @Override
    public List<AlunoDto> buscarPorNomeETurma(String nome, int turma) {
        return null;
    }
}
