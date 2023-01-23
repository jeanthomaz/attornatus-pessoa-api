package com.pessoaapi.controller.documentation;

import com.pessoaapi.dto.EnderecoCreateDTO;
import com.pessoaapi.dto.EnderecoDTO;
import com.pessoaapi.dto.PessoaDTO;
import com.pessoaapi.exception.RegraDeNegocioException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface EnderecoControllerInterface {

    @Operation(summary = "Criar endereço", description = "Cria um endereço para uma pessoa específica")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Endereço criado com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping("/{idPessoa}")
    public ResponseEntity<EnderecoDTO> create(@PathVariable("idPessoa") Integer id,
                                              @RequestBody @Valid EnderecoCreateDTO enderecoCreateDTO) throws RegraDeNegocioException;

    @Operation(summary = "Listar endereços de uma pessoa", description = "Lista endereços de uma pessoa específica, deixe em branco para listar todos os endereços.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Endereços listados com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )@GetMapping("/listar/{idPessoa}")
    public ResponseEntity<List<EnderecoDTO>> list(@RequestParam(required = false) Integer idPessoa) throws RegraDeNegocioException;


    @Operation(summary = "Selecionar endereço principal de uma pessoa", description = "Seleciona o endereco principal de uma pessoa")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Endereço selecionado com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )@PutMapping("selecionar-endereco-principal/{idEndereço}")
    public ResponseEntity<EnderecoDTO> selecionarEnderecoPrincipal(@RequestParam Integer idEndereco) throws RegraDeNegocioException;

}
