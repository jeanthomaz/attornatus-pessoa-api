package com.pessoaapi.controller;

import com.pessoaapi.controller.documentation.EnderecoControllerInterface;
import com.pessoaapi.dto.EnderecoCreateDTO;
import com.pessoaapi.dto.EnderecoDTO;
import com.pessoaapi.exception.RegraDeNegocioException;
import com.pessoaapi.service.EnderecoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/endereco")
@Validated
@Slf4j
@RequiredArgsConstructor
public class EnderecoController implements EnderecoControllerInterface {

    private final EnderecoService enderecoService;
    @PostMapping("/{idPessoa}")
    public ResponseEntity<EnderecoDTO> create(@PathVariable("idPessoa") Integer id,
                                              @RequestBody @Valid EnderecoCreateDTO enderecoCreateDTO) throws RegraDeNegocioException {
        return ResponseEntity.ok(enderecoService.create(id, enderecoCreateDTO));
    }

    @GetMapping("/listar/{idPessoa}")
    public ResponseEntity<List<EnderecoDTO>> list(@RequestParam(required = false) Integer idPessoa) throws RegraDeNegocioException {

        return ResponseEntity.ok(enderecoService.listarEnderecos(idPessoa));

    }

    @PutMapping("selecionar-endereco-principal/{idEndereço}")
    public ResponseEntity<EnderecoDTO> selecionarEnderecoPrincipal(@RequestParam Integer idEndereco) throws RegraDeNegocioException {
        return ResponseEntity.ok(enderecoService.selecionarEnderecoPrincipal(idEndereco));
    }

}
