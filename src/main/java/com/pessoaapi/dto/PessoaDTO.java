package com.pessoaapi.dto;

import com.pessoaapi.entity.EnderecoEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Set;

@Data
public class PessoaDTO extends PessoaCreateDTO{

    @Id
    private Integer idPessoa;


}
