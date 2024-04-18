package tech.ada.school.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

//@Data - cria gets, sets, toString etc...
//@Builder - responsável pela criação da instância da classe
//@JsonIgnoreProperties - ignora atributos não inicializados ou valores
//que não sejam atributos
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
public class ProfessorDto {
    @NotBlank
    private String nome;
    @PositiveOrZero
    private int id;
    @CPF
    private String cpf;
    @Email
    private String email;
}
