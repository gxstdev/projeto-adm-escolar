package tech.ada.school.view;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.ada.school.domain.dto.AlunoDto;
import tech.ada.school.domain.dto.exception.NotFoundException;
import tech.ada.school.service.IAlunoService;

import java.util.List;

@RestController
@RequestMapping("/aluno")
public class AlunoController {
    @Autowired
    private IAlunoService service;
    @PostMapping
    public ResponseEntity<AlunoDto> criarAluno(@RequestBody @Valid AlunoDto aluno) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criarAluno(aluno));
    }
    @GetMapping
    public ResponseEntity<List<AlunoDto>> lerAlunos(
            @RequestParam(required = false) Integer turma,
            @RequestParam(required = false) String nome)
            throws NotFoundException {
        if (turma == null){
            return ResponseEntity.ok(service.lerAlunos());
        }
        if (nome == null){
            return ResponseEntity.ok(service.buscarAlunoPorTurma(turma));
        }
        return ResponseEntity.ok(service.buscarPorNomeETurma(nome, turma));
    }
    @GetMapping("/{id}")
    public ResponseEntity<AlunoDto> buscarAlunoPorId(@PathVariable("id") int idAluno)
            throws NotFoundException {
        AlunoDto result = service.buscarAlunoPorId(idAluno);
        return ResponseEntity.ok(result);
    }
    @PutMapping("{id}")
    public ResponseEntity<AlunoDto> atualizarAluno(@PathVariable int id,
     @RequestBody @Valid AlunoDto aluno) throws NotFoundException {
        AlunoDto result = service.atualizarAluno(id, aluno);
        return  ResponseEntity.ok(result);

    }
    @DeleteMapping("{id}")
    public ResponseEntity<AlunoDto> deletarAluno(@PathVariable int id)
            throws NotFoundException {
        service.deletarAluno(id);
        return ResponseEntity.noContent().build();
    }
}
