package com.check_certo.check_certo.service;

import com.check_certo.check_certo.domain.Veiculo;
import com.check_certo.check_certo.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class VeiculoService {

    @Autowired
    VeiculoRepository veiculoRepository;


    public ResponseEntity<Veiculo> salvarVeiculo(Veiculo veiculoParametro) throws Exception {

        //verifica na base de dados se o CPF informado já existe na base
        boolean cpf = veiculoRepository.existsByCpf(veiculoParametro.getCpf());
        boolean placa = veiculoRepository.existsByPlaca(veiculoParametro.getPlaca());


        System.out.println("O resultado da placa é: "+placa);
        System.out.println("O resultado do CPF é: "+cpf);
        System.out.println("Essa é a placa do veiculo: "+veiculoParametro.getPlaca());

        if (cpf) {
            throw new Exception("Esse CPF já existe nessa base");
        }
        if (placa) {
            throw new Exception("Essa placa já existe nessa base");
        }

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Veiculo> veiculoRetorno =
                restTemplate
                        .getForEntity("https://my.api.mockaroo.com/veiculos?key=55ad1cd0&placa="+veiculoParametro.getPlaca(),Veiculo.class);


        veiculoRetorno.getBody().setProprietario(veiculoParametro.getProprietario());
        veiculoRetorno.getBody().setCpf(veiculoParametro.getCpf());
        veiculoRetorno.getBody().setPlaca(veiculoParametro.getPlaca());
        return veiculoRetorno;

    }


}
