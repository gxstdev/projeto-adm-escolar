package tech.ada.school.view;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.ada.school.domain.dto.exception.NotFoundException;
import tech.ada.school.domain.dto.ProfessorDto;
import tech.ada.school.service.IProfessorService;

import java.util.List;

@RestController
@RequestMapping("/professor")
public class ProfessorController {
    @Autowired
    private final IProfessorService service;

    public ProfessorController(IProfessorService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ProfessorDto>> lerProfessores() {
            return ResponseEntity.ok(service.lerProfessores());
    }

    @PostMapping
    public ResponseEntity<ProfessorDto> criarProfessor(@Valid @RequestBody ProfessorDto professor){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.criarProfessor(professor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfessorDto> atualizarProfessor(@PathVariable("id") int id,
         @RequestBody @Valid ProfessorDto professor) throws NotFoundException {
        ProfessorDto result = service.atualizarProfessor(id, professor);
        return ResponseEntity.ok(result);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessorDto> buscarProfessorPorId(@PathVariable int id) throws NotFoundException {
        return ResponseEntity.ok(service.buscarProfessorPorId(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProfessor(@PathVariable("id") int id) throws NotFoundException {
        service.deletarProfessor(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("cpf/{cpf}")
    public ResponseEntity<ProfessorDto> buscarProfessorPorCpf(String cpf)
            throws NotFoundException {
        return ResponseEntity.ok(service.buscarProfessorPorCpf(cpf));
    }
}
