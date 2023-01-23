package com.pessoaapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;

import java.time.LocalDate;

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
