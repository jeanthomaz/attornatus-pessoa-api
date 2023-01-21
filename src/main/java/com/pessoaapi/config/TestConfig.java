package com.pessoaapi.config;

import com.pessoaapi.entity.EnderecoEntity;
import com.pessoaapi.entity.PessoaEntity;
import com.pessoaapi.enums.TipoEndereco;
import com.pessoaapi.repository.EnderecoRepository;
import com.pessoaapi.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class TestConfig implements CommandLineRunner {

    private final PessoaRepository pessoaRepository;
    private final EnderecoRepository enderecoRepository;


    @Override
    public void run(String... args) throws Exception {

        Set<EnderecoEntity> enderecosVazio = new HashSet<>();

        PessoaEntity p = new PessoaEntity(null, "Jean Jardim", LocalDate.of(2002, 5, 10), enderecosVazio);
        PessoaEntity p1 = new PessoaEntity(null, "Tássio Fernandes", LocalDate.of(1990, 5, 10), enderecosVazio);
        PessoaEntity p2 = new PessoaEntity(null, "Gustavo Acacio", LocalDate.of(1991, 5, 10), enderecosVazio);
        PessoaEntity p3 = new PessoaEntity(null, "Kauã Cassiano", LocalDate.of(1992, 5, 10), enderecosVazio);
        PessoaEntity p4 = new PessoaEntity(null, "Wendell Lucas", LocalDate.of(1993, 5, 10), enderecosVazio);
        PessoaEntity p5 = new PessoaEntity(null, "Kauã Mendes", LocalDate.of(1994, 5, 10), enderecosVazio);
        PessoaEntity p6 = new PessoaEntity(null, "Giunei Philippi", LocalDate.of(1995, 5, 10), enderecosVazio);
        PessoaEntity p7 = new PessoaEntity(null, "Kauã Mendes", LocalDate.of(1996, 5, 10), enderecosVazio);
        PessoaEntity p8 = new PessoaEntity(null, "Isadora de Medeiros", LocalDate.of(1997, 5, 10), enderecosVazio);
        PessoaEntity p9 = new PessoaEntity(null, "Albano Borba", LocalDate.of(1998, 5, 10), enderecosVazio);

        pessoaRepository.saveAll(Arrays.asList(p,p1,p2,p3,p4,p5,p6,p7,p8,p9));

//        EnderecoEntity e = new EnderecoEntity(null,p.getIdPessoa(), "Rua Anita Garibaldi", "27777777", 300, "Resende", TipoEndereco.PRINCIPAL, p);
//        EnderecoEntity e1 = new EnderecoEntity(null,p.getIdPessoa(), "Avenida Nova Resende", "28888888", 100, "Resende", TipoEndereco.PRINCIPAL, p);
//
//        enderecoRepository.save(e);
//        enderecoRepository.save(e);
//
//        Set<EnderecoEntity> enderecosP = new HashSet<>();
//        enderecosP.add(e);
//        enderecosP.add(e1);
//        p.setEnderecos(enderecosP);
//
//        pessoaRepository.save(p);


    }
}
