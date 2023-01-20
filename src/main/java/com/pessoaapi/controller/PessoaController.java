package com.pessoaapi.controller;

import com.pessoaapi.controller.documentation.PessoaControllerInterface;
import com.pessoaapi.dto.PessoaCreateDTO;
import com.pessoaapi.dto.PessoaDTO;
import com.pessoaapi.exception.RegraDeNegocioException;
import com.pessoaapi.service.PessoaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoa")
@Validated
@Slf4j
@RequiredArgsConstructor
public class PessoaController implements PessoaControllerInterface {

    private final PessoaService pessoaService;

    @GetMapping ("/listar")
    public ResponseEntity<List<PessoaDTO>> list() {
        return ResponseEntity.ok(pessoaService.list());
    }

    @GetMapping("/consultar-id-nome")
    public ResponseEntity<List<PessoaDTO>> consultar(@RequestParam(required = false) Integer idPessoa,
                                                     @RequestParam(required = false) String nomePessoa) throws RegraDeNegocioException {
        return ResponseEntity.ok(pessoaService.consultarPorIdOuNome(idPessoa,nomePessoa));
    }

    @PostMapping
    public ResponseEntity<PessoaDTO> create(@Valid @RequestBody PessoaCreateDTO pessoaCreateDTO) throws RegraDeNegocioException {
        return new ResponseEntity<>(pessoaService.create(pessoaCreateDTO), HttpStatus.OK);
    }

    @PutMapping("/{idPessoa}")
    public ResponseEntity<PessoaDTO> update(@PathVariable("idPessoa") Integer id,
                                            @RequestBody @Valid PessoaCreateDTO pessoaAtualizada) throws RegraDeNegocioException {
        return ResponseEntity.ok(pessoaService.update(id, pessoaAtualizada));
    }
}
