package com.pessoaapi.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.pessoaapi.dto.EnderecoCreateDTO;
import com.pessoaapi.dto.EnderecoDTO;
import com.pessoaapi.entity.EnderecoEntity;
import com.pessoaapi.entity.PessoaEntity;
import com.pessoaapi.enums.TipoEndereco;
import com.pessoaapi.exception.RegraDeNegocioException;
import com.pessoaapi.factory.EnderecoFactory;
import com.pessoaapi.factory.PessoaFactory;
import com.pessoaapi.repository.EnderecoRepository;
import com.pessoaapi.repository.PessoaRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EnderecoEntityTest {

    @InjectMocks
    private EnderecoService enderecoService;
    @Mock
    private EnderecoRepository enderecoRepository;
    @Mock
    private PessoaRepository pessoaRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void init() {
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        ReflectionTestUtils.setField(enderecoService, "objectMapper", objectMapper);
    }
    @Test
    public void deveTestarCriarEnderecoComSucesso() throws RegraDeNegocioException{
        PessoaEntity pessoa = PessoaFactory.getPessoaEntity();
        EnderecoCreateDTO enderecoCreateDTO = EnderecoFactory.getCreateEnderecoDTO();
        EnderecoEntity enderecoEntity = EnderecoFactory.getEnderecoEntity();


        when(pessoaRepository.findById(anyInt())).thenReturn(Optional.of(pessoa));
        when(enderecoRepository.save(any())).thenReturn(enderecoEntity);
        EnderecoDTO enderecoDTO = enderecoService.create(1, enderecoCreateDTO);

        assertNotNull(enderecoCreateDTO);

    }

    @Test
    public void deveListarEnderecosComSucesso() throws RegraDeNegocioException{
        List<EnderecoEntity> enderecoEntities = new ArrayList<>();
        enderecoEntities.add(EnderecoFactory.getEnderecoEntity());
        PessoaEntity pessoa = PessoaFactory.getPessoaEntity();
        EnderecoCreateDTO enderecoCreateDTO = EnderecoFactory.getCreateEnderecoDTO();

        when(enderecoRepository.findAllByIdPessoa(anyInt())).thenReturn(enderecoEntities);
        List<EnderecoDTO> enderecoDTOS = enderecoService.listarEnderecos(1);

        assertNotNull(enderecoDTOS);

    }

    @Test
    public void deveListarTodosEnderecosComSucesso() throws RegraDeNegocioException{
        List<EnderecoEntity> enderecoEntities = new ArrayList<>();
        enderecoEntities.add(EnderecoFactory.getEnderecoEntity());
        PessoaEntity pessoa = PessoaFactory.getPessoaEntity();
        EnderecoCreateDTO enderecoCreateDTO = EnderecoFactory.getCreateEnderecoDTO();

        when(enderecoRepository.findAll()).thenReturn(enderecoEntities);
        List<EnderecoDTO> enderecoDTOS = enderecoService.listarEnderecos(null);

        assertNotNull(enderecoDTOS);

    }

    @Test
    public void deveSelecionarEnderecoPrincipal() throws RegraDeNegocioException{
        EnderecoEntity enderecoEntity = EnderecoFactory.getEnderecoEntity();
        enderecoEntity.setTipoEndereco(TipoEndereco.COMUM);

        when(enderecoRepository.findById(anyInt())).thenReturn(Optional.of(enderecoEntity));
        when(enderecoRepository.findEnderecoEntityByIdPessoaAndTipoEndereco(anyInt(), any())).thenReturn(enderecoEntity);
        when(enderecoRepository.save(any())).thenReturn(enderecoEntity);
        EnderecoDTO enderecoDTO = enderecoService.selecionarEnderecoPrincipal(1);

        assertNotNull(enderecoDTO);
        assertEquals(enderecoEntity.getIdEndereco(), enderecoDTO.getIdEndereco());

    }

    @Test
    public void deveSelecionarEnderecoPrincipalParaEnderecoJaPrincipal() throws RegraDeNegocioException{
        EnderecoEntity enderecoEntity = EnderecoFactory.getEnderecoEntity();
        enderecoEntity.setTipoEndereco(TipoEndereco.PRINCIPAL);

        when(enderecoRepository.findById(anyInt())).thenReturn(Optional.of(enderecoEntity));
        when(enderecoRepository.findEnderecoEntityByIdPessoaAndTipoEndereco(anyInt(), any())).thenReturn(null);
        EnderecoDTO enderecoDTO = enderecoService.selecionarEnderecoPrincipal(1);

        assertNotNull(enderecoDTO);
        assertEquals(enderecoEntity.getIdEndereco(), enderecoDTO.getIdEndereco());

    }
}
