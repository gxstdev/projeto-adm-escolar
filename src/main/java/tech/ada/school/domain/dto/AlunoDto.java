package tech.ada.school.domain.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.br.CPF;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
//@JsonIgnoreProperties
public class AlunoDto {
    @NotBlank
    @Size(min = 3)
    private String nome;
    @PositiveOrZero
    private int id;
    @CPF
    @NotBlank
    private String cpf;
    @Email
    private String email;
    @Range(min = 1000, max = 9999)
    @NotNull
    private int turma;
    @Null
    private String activity;
}
