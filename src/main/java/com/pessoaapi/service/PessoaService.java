package com.pessoaapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pessoaapi.dto.PessoaCreateDTO;
import com.pessoaapi.dto.PessoaDTO;
import com.pessoaapi.entity.PessoaEntity;
import com.pessoaapi.exception.RegraDeNegocioException;
import com.pessoaapi.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PessoaService {

    private final PessoaRepository pessoaRepository;
    private final ObjectMapper objectMapper;

    public List<PessoaDTO> list(){

        List<PessoaEntity> pessoaEntities = pessoaRepository.findAll();

        List<PessoaDTO> pessoaDTOS = pessoaEntities.stream().map(
                pessoaEntity -> {
                    PessoaDTO pessoaDTO = objectMapper.convertValue(pessoaEntity, PessoaDTO.class);
                    return pessoaDTO;
                }
        ).toList();
        return pessoaDTOS;
    }

    public List<PessoaDTO> consultarPorIdOuNome(Integer idPessoa, String nomePessoa) throws RegraDeNegocioException {


            List<PessoaEntity> pessoaEntities = pessoaRepository.findByFiltroConsulta(idPessoa, nomePessoa);

            List<PessoaDTO> pessoaDTOS = pessoaEntities.stream().map(
                    pessoaEntity -> {
                        PessoaDTO pessoaDTO = objectMapper.convertValue(pessoaEntity, PessoaDTO.class);
                        return pessoaDTO;
                    }
            ).toList();

            return pessoaDTOS;
        }

    public PessoaDTO create(PessoaCreateDTO pessoaCreateDTO) throws RegraDeNegocioException {
        PessoaEntity pessoaEntity = objectMapper.convertValue(pessoaCreateDTO, PessoaEntity.class);

        pessoaEntity.setNome(pessoaCreateDTO.getNome());
        pessoaEntity.setDataNascimento(pessoaCreateDTO.getDataNascimento());
        PessoaEntity pessoaSaved = pessoaRepository.save(pessoaEntity);
        return objectMapper.convertValue(pessoaSaved, PessoaDTO.class);
    }

    public PessoaDTO update(Integer id, PessoaCreateDTO pessoaAtualizada) throws RegraDeNegocioException {
        PessoaEntity pessoaEntityRecuperada = findById(id);

        pessoaEntityRecuperada.setNome(pessoaAtualizada.getNome());
        pessoaEntityRecuperada.setDataNascimento(pessoaAtualizada.getDataNascimento());
        pessoaRepository.save(pessoaEntityRecuperada);

        PessoaDTO pessoaDTO = objectMapper.convertValue(pessoaEntityRecuperada, PessoaDTO.class);

        return pessoaDTO;
    }

    private PessoaEntity findById(Integer idPessoa) throws RegraDeNegocioException {
        return pessoaRepository.findById(idPessoa)
                .orElseThrow(() -> new RegraDeNegocioException("Pessoa não encontrada!"));
    }

}
