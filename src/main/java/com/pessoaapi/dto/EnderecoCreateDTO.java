package com.pessoaapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EnderecoCreateDTO {

    @Schema(description = "Logradouro", example = "Rua camarão")
    @NotBlank(message = "Insira um logradouro")
    private String logradouro;

    @Schema(description = "CEP válido", example = "27000000")
    @Size(min=8, max=8, message = "Insira um CEP válido")
    @NotNull(message = "Insira um nome")
    private String cep;

    @Schema(description = "Número", example = "20")
    @NotNull(message = "Insira um número")
    private Integer numero;

    @Schema(description = "Cidade", example = "Tubarão")
    @NotBlank(message = "Insira uma cidade")
    private String cidade;


}
