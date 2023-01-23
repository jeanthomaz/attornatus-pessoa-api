package com.pessoaapi.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.pessoaapi.dto.PessoaCreateDTO;
import com.pessoaapi.dto.PessoaDTO;
import com.pessoaapi.entity.PessoaEntity;
import com.pessoaapi.exception.RegraDeNegocioException;
import com.pessoaapi.factory.PessoaFactory;
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
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PessoaServiceTest {

    @InjectMocks
    private PessoaService pessoaService;
    @Mock
    private PessoaRepository pessoaRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void init() {
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        ReflectionTestUtils.setField(pessoaService, "objectMapper", objectMapper);
    }

    @Test
    public void deveTestarListComSucesso() throws RegraDeNegocioException {
        //SETUP
        List<PessoaEntity> pessoaEntities = new ArrayList<>();
        pessoaEntities.add(PessoaFactory.getPessoaEntity());
        List<PessoaDTO> pessoaDTOS = new ArrayList<>();

        //ACT
        when(pessoaRepository.findAll()).thenReturn(pessoaEntities);
        pessoaDTOS = pessoaService.list();
        //ASSERT

        assertNotNull(pessoaDTOS);
    }

    @Test
    public void deveConsultarPorIdOuNomeComSucesso() throws RegraDeNegocioException {
        //SETUP
        List<PessoaEntity> pessoaEntities = new ArrayList<>();
        pessoaEntities.add(PessoaFactory.getPessoaEntity());
        List<PessoaDTO> pessoaDTOS = new ArrayList<>();

        //ACT
        when(pessoaRepository.findByFiltroConsulta(anyInt(),anyString())).thenReturn(pessoaEntities);
        pessoaDTOS = pessoaService.consultarPorIdOuNome(1,"AAA");
        //ASSERT

        assertNotNull(pessoaDTOS);
    }

    @Test
    public void deveTestarCreatePessoaComSucesso() throws RegraDeNegocioException{
        PessoaEntity pessoa = PessoaFactory.getPessoaEntity();
        PessoaCreateDTO pessoaCreateDTO = PessoaFactory.getPessoaCreateDTO();

        when(pessoaRepository.save(any())).thenReturn(pessoa);

        PessoaDTO pessoaDTO = pessoaService.create(pessoaCreateDTO);

        assertEquals(pessoa.getNome(), pessoaDTO.getNome());
        assertNotNull(pessoaDTO);

    }

    @Test
    public void deveTestarUpdatePessoaComSucesso() throws RegraDeNegocioException{
        PessoaEntity pessoa = PessoaFactory.getPessoaEntity();
        PessoaCreateDTO pessoaCreateDTO = PessoaFactory.getPessoaCreateDTO();

        when(pessoaRepository.findById(any())).thenReturn(Optional.of(pessoa));
        when(pessoaRepository.save(any())).thenReturn(pessoa);

        PessoaDTO pessoaDTO = pessoaService.update(1,pessoaCreateDTO);

        assertEquals(pessoa.getNome(), pessoaDTO.getNome());
        assertNotNull(pessoaDTO);

    }


}
