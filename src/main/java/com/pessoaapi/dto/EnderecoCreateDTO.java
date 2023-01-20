package com.pessoaapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class EnderecoCreateDTO {

    @Schema(description = "Logradouro")
    @NotBlank(message = "Insira um logradouro")
    private String logradouro;

    @Schema(description = "CEP válido", example = "27000-000")
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "Insira um cep válido")
    @NotNull(message = "Insira um nome")
    private Integer cep;

    @Schema(description = "Número", example = "20")
    @NotNull(message = "Insira um número")
    private Integer numero;

    @Schema(description = "Cidade")
    @NotBlank(message = "Insira uma cidade")
    private String cidade;


}
