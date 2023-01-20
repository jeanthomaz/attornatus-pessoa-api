package com.pessoaapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pessoaapi.dto.EnderecoCreateDTO;
import com.pessoaapi.dto.EnderecoDTO;
import com.pessoaapi.entity.EnderecoEntity;
import com.pessoaapi.entity.PessoaEntity;
import com.pessoaapi.enums.TipoEndereco;
import com.pessoaapi.exception.RegraDeNegocioException;
import com.pessoaapi.repository.EnderecoRepository;
import com.pessoaapi.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final PessoaRepository pessoaRepository;
    private final EnderecoRepository enderecoRepository;
    private final ObjectMapper objectMapper;

    public EnderecoDTO create(Integer idPessoa, EnderecoCreateDTO enderecoCreateDTO) throws RegraDeNegocioException {

        PessoaEntity pessoaEntity = pessoaRepository.findById(idPessoa).get();

        EnderecoEntity enderecoEntity = objectMapper.convertValue(enderecoCreateDTO, EnderecoEntity.class);
        enderecoEntity.setIdPessoa(idPessoa);
        enderecoEntity.setPessoa(pessoaEntity);
        enderecoEntity.setTipoEndereco(TipoEndereco.COMUM);

        enderecoRepository.save(enderecoEntity);

        return objectMapper.convertValue(enderecoEntity, EnderecoDTO.class);
    }

    private PessoaEntity findById(Integer idPessoa) throws RegraDeNegocioException {
        return pessoaRepository.findById(idPessoa)
                .orElseThrow(() -> new RegraDeNegocioException("Pessoa não encontrada!"));
    }

}
