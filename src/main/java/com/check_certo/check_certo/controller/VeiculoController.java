package com.check_certo.check_certo.controller;

import com.check_certo.check_certo.domain.Veiculo;
import com.check_certo.check_certo.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    @Autowired
    VeiculoService veiculoService;

    @PostMapping
    public Veiculo cadastrarVeiculo(@RequestBody Veiculo veiculo) {

        ResponseEntity<Veiculo> veiculoRetorno;
        try {
            veiculoRetorno = veiculoService.salvarVeiculo(veiculo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return veiculoRetorno.getBody();
    }
}
