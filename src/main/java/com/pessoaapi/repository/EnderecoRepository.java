package com.pessoaapi.repository;

import com.pessoaapi.entity.EnderecoEntity;
import com.pessoaapi.enums.TipoEndereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnderecoRepository extends JpaRepository<EnderecoEntity, Integer> {

    List<EnderecoEntity> findAllByIdPessoa(Integer idPessoa);

    EnderecoEntity findEnderecoEntityByIdPessoaAndTipoEndereco(Integer idPessoa, TipoEndereco tipoEndereco);
}
