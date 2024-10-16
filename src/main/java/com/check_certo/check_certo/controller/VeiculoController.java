package com.check_certo.check_certo.controller;

import com.check_certo.check_certo.domain.Veiculo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class VeiculoController {

    @PostMapping
    public ResponseEntity<Veiculo> cadastrarVeiculo() {
        // criar logica de manipular esse request
        return null;
    }
}
