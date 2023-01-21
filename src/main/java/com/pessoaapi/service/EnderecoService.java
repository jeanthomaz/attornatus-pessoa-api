package com.pessoaapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pessoaapi.dto.EnderecoCreateDTO;
import com.pessoaapi.dto.EnderecoDTO;
import com.pessoaapi.dto.PessoaDTO;
import com.pessoaapi.entity.EnderecoEntity;
import com.pessoaapi.entity.PessoaEntity;
import com.pessoaapi.enums.TipoEndereco;
import com.pessoaapi.exception.RegraDeNegocioException;
import com.pessoaapi.repository.EnderecoRepository;
import com.pessoaapi.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final PessoaRepository pessoaRepository;
    private final EnderecoRepository enderecoRepository;
    private final ObjectMapper objectMapper;

    public EnderecoDTO create(Integer idPessoa, EnderecoCreateDTO enderecoCreateDTO) throws RegraDeNegocioException {

        PessoaEntity pessoaEntity = pessoaRepository.findById(idPessoa).orElseThrow(() -> new RegraDeNegocioException("Pessoa não encontrada!"));

        EnderecoEntity enderecoEntity = objectMapper.convertValue(enderecoCreateDTO, EnderecoEntity.class);
        enderecoEntity.setIdPessoa(idPessoa);
        enderecoEntity.setPessoa(pessoaEntity);
        enderecoEntity.setTipoEndereco(TipoEndereco.COMUM);

        enderecoRepository.save(enderecoEntity);

        return objectMapper.convertValue(enderecoEntity, EnderecoDTO.class);
    }

    public List<EnderecoDTO> listarEnderecos(Integer idPessoa) throws RegraDeNegocioException {

        List<EnderecoEntity> enderecoEntities = new ArrayList<>();

        if(idPessoa != null){
            enderecoEntities = enderecoRepository.findAllByIdPessoa(idPessoa);
        }else{
            enderecoEntities = enderecoRepository.findAll();
        }

        List<EnderecoDTO> enderecoDTOS = enderecoEntities.stream().map(
                enderecoEntity -> {
                    EnderecoDTO enderecoDTO = objectMapper.convertValue(enderecoEntity, EnderecoDTO.class);
                    return enderecoDTO;
                }
        ).toList();

        return enderecoDTOS;
    }

    public EnderecoDTO selecionarEnderecoPrincipal(Integer idEndereco) throws RegraDeNegocioException {

        EnderecoEntity enderecoSelecionado = enderecoRepository.findById(idEndereco).orElseThrow(() -> new RegraDeNegocioException("Endereço não encontrado!"));
        Integer idPessoa = enderecoSelecionado.getIdPessoa();
        EnderecoEntity enderecoPrincipal = enderecoRepository.findEnderecoEntityByIdPessoaAndTipoEndereco(idPessoa, TipoEndereco.PRINCIPAL);

        if(enderecoPrincipal != null){
            enderecoPrincipal.setTipoEndereco(TipoEndereco.COMUM);
            enderecoRepository.save(enderecoPrincipal);
        }

        if(enderecoSelecionado.getTipoEndereco() != TipoEndereco.PRINCIPAL){

            enderecoSelecionado.setTipoEndereco(TipoEndereco.PRINCIPAL);
            enderecoRepository.save(enderecoSelecionado);

            return objectMapper.convertValue(enderecoSelecionado, EnderecoDTO.class);

        }

        return objectMapper.convertValue(enderecoSelecionado, EnderecoDTO.class);

    }
}
