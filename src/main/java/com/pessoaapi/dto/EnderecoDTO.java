package com.pessoaapi.dto;

import com.pessoaapi.enums.TipoEndereco;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EnderecoDTO extends EnderecoCreateDTO {

    @Id
    private Integer idEndereco;

    private Integer idPessoa;

    @NotNull
    private TipoEndereco tipoEndereco;

}
