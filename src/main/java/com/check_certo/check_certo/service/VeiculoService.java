package com.check_certo.check_certo.service;

import com.check_certo.check_certo.domain.Veiculo;
import com.check_certo.check_certo.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VeiculoService {

    @Autowired
    VeiculoRepository veiculoRepository;


    public void salvarVeiculo(Veiculo veiculoParametro) throws Exception {

        //verifica na base de dados se o CPF informado já existe na base
        boolean cpf = veiculoRepository.existsByCpf(veiculoParametro.getCpf());
        boolean placa = veiculoRepository.existsByPlaca(veiculoParametro.getPlaca());

        System.out.println("O resultado da placa é: "+placa);
        System.out.println("O resultado do CPF é: "+cpf);

        /*
        if (cpf) {
            throw new Exception("Esse CPF já existe nessa base");
        }
        if (placa) {
            throw new Exception("Esse CPF já existe nessa base");
        }
        */

    }
}
