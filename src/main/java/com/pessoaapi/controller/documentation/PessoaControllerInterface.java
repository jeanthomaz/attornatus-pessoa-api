package com.pessoaapi.controller.documentation;

import com.pessoaapi.dto.PessoaCreateDTO;
import com.pessoaapi.dto.PessoaDTO;
import com.pessoaapi.exception.RegraDeNegocioException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface PessoaControllerInterface {

    @Operation(summary = "Listar pessoas", description = "Lista todas as pessoas do banco de dados")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Pessoas listadas com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/listar")
    public ResponseEntity<List<PessoaDTO>> list();

    @Operation(summary = "Consultar pessoa", description = "Consulta uma pessoa por id, por nome ou sobrenome")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Pessoa consultada com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/listar-id-nome")
    public ResponseEntity<List<PessoaDTO>> consultar(@RequestParam(required = false) Integer idPessoa,
                                                     @RequestParam(required = false) String nomePessoa) throws RegraDeNegocioException;

    @Operation(summary = "Criar pessoa", description = "Cria uma pessoa no banco de dados")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Pessoa criada com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping
    public ResponseEntity<PessoaDTO> create(@Valid @RequestBody PessoaCreateDTO pessoaCreateDTO) throws RegraDeNegocioException;

    @Operation(summary = "Editar pessoa", description = "Edita uma pessoa do banco de dados")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Pessoa editada com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PutMapping("/{idPessoa}")
    public ResponseEntity<PessoaDTO> update(@PathVariable("idPessoa") Integer id,
                                            @RequestBody @Valid PessoaCreateDTO pessoaAtualizada) throws RegraDeNegocioException;

}
