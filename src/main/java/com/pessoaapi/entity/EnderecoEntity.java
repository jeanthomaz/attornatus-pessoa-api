package com.pessoaapi.entity;

import com.pessoaapi.enums.TipoEndereco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "ENDERECO")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idEndereco;

    @Column(name = "ID_PESSOA", insertable = false, updatable = false)
    private Integer idPessoa;
    private String logradouro;

    private String cep;

    private Integer numero;

    private String cidade;

    private TipoEndereco tipoEndereco;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pessoa", referencedColumnName = "id_pessoa")
    private PessoaEntity pessoa;

}
