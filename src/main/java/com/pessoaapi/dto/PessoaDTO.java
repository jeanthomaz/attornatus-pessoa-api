package com.pessoaapi.dto;

import jakarta.persistence.Id;
import lombok.Data;

@Data
public class PessoaDTO extends PessoaCreateDTO{

    @Id
    private Integer idPessoa;


}
