package com.pessoaapi.repository;

import com.pessoaapi.entity.PessoaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PessoaRepository extends JpaRepository<PessoaEntity, Integer> {

    @Query("SELECT pessoa " +
            "FROM PESSOA pessoa " +
            "WHERE ( :nomePessoa is null or UPPER(pessoa.nome) LIKE UPPER(concat('%',:nomePessoa, '%'))) " +
            "AND ( pessoa.idPessoa = :idPessoa or :idPessoa is null)")
    List<PessoaEntity> findByFiltroConsulta(Integer idPessoa, String nomePessoa);

}
