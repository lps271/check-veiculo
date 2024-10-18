package com.check_certo.check_certo.service;

import com.check_certo.check_certo.domain.Veiculo;
import com.check_certo.check_certo.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VeiculoService {

    @Autowired
    VeiculoRepository veiculoRepository;

    @Autowired
    Veiculo veiculo;


    public void salvarVeiculo(Veiculo veiculoParametro) throws Exception {

        //verifica na base de dados se o CPF informado já existe na base
        boolean cpf = veiculoRepository.existsByCpfProprietario(veiculoParametro.getCpfProprietario());
        boolean placa = veiculoRepository.existsByPlaca(veiculoParametro.getPlaca());

        if (cpf) {
            throw new Exception("Esse CPF já existe nessa base");
        }
        if (placa) {
            throw new Exception("Esse CPF já existe nessa base");
        }

    }
}
