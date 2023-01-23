package com.pessoaapi.factory;

import com.pessoaapi.dto.EnderecoCreateDTO;
import com.pessoaapi.dto.EnderecoDTO;
import com.pessoaapi.entity.EnderecoEntity;
import com.pessoaapi.entity.PessoaEntity;
import com.pessoaapi.enums.TipoEndereco;

public class EnderecoFactory {

    public static EnderecoEntity getEnderecoEntity() {
        PessoaEntity pessoaEntity1 = PessoaFactory.getPessoaEntity();

        EnderecoEntity enderecoEntity = new EnderecoEntity(
                1,
                1,
                "Rua Plinio de Castro",
                "28999999",
                22,
                "Joao de meriti",
                TipoEndereco.COMUM,
                pessoaEntity1
        );

        return enderecoEntity;
    }

    public static EnderecoDTO getEnderecoDTO(){
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setIdEndereco(1);
        enderecoDTO.setTipoEndereco(TipoEndereco.COMUM);
        enderecoDTO.setCep("20000000");
        enderecoDTO.setCidade("resende");
        enderecoDTO.setNumero(23);
        enderecoDTO.setIdPessoa(1);
        enderecoDTO.setLogradouro("Rua 2");

        return enderecoDTO;
    }

    public static EnderecoCreateDTO getCreateEnderecoDTO() {
        EnderecoCreateDTO enderecoCreateDTO = new EnderecoCreateDTO();
        enderecoCreateDTO.setCidade("Resende");
        enderecoCreateDTO.setCep("20009999");
        enderecoCreateDTO.setLogradouro("Avenida 2");
        enderecoCreateDTO.setNumero(23);

        return enderecoCreateDTO;
    }

}
