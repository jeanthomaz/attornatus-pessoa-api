package com.pessoaapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Entity(name = "PESSOA")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PessoaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_PESSOA")
    private Integer idPessoa;

    private String nome;

    private LocalDate dataNascimento;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pessoa")
    private Set<EnderecoEntity> enderecos;
}
