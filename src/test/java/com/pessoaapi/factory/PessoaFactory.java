package com.pessoaapi.factory;

import com.pessoaapi.dto.PessoaCreateDTO;
import com.pessoaapi.dto.PessoaDTO;
import com.pessoaapi.entity.EnderecoEntity;
import com.pessoaapi.entity.PessoaEntity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class PessoaFactory {

    public static PessoaEntity getPessoaEntity() {
        Set<EnderecoEntity> enderecosVazio = new HashSet<>();

        PessoaEntity pessoaEntity = new PessoaEntity(
                1,
                "Joao",
                LocalDate.of(2002, 10, 02),
                enderecosVazio
        );

        return pessoaEntity;
    }

    public static PessoaDTO getPessoaDTO() {
        Set<EnderecoEntity> enderecosVazio = new HashSet<>();

        PessoaDTO pessoaDTO = new PessoaDTO();
        pessoaDTO.setIdPessoa(1);
        pessoaDTO.setNome("Joao");
        pessoaDTO.setDataNascimento(LocalDate.of(2002, 10, 02));

        return pessoaDTO;
    }

    public static PessoaCreateDTO getPessoaCreateDTO(){

        PessoaCreateDTO pessoaCreateDTO = new PessoaCreateDTO();

        pessoaCreateDTO.setNome("Luiz");
        pessoaCreateDTO.setDataNascimento(LocalDate.of(2002, 10, 02));

        return pessoaCreateDTO;
    }

}
