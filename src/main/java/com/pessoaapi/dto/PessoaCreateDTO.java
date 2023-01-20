package com.pessoaapi.dto;

import com.pessoaapi.entity.EnderecoEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class PessoaCreateDTO {

    @Schema(description = "Nome da pessoa")
    @NotBlank(message = "Insira um nome")
    private String nome;

    @Schema(description = "AAAA-MM-DD")
    @Past(message = "Insira uma data passada")
    @NotNull(message = "Insira uma data válida")
    private LocalDate dataNascimento;


}
