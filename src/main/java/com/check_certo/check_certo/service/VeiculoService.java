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

        //verifica se o CPF e a placa informada já existem na base
        verificaPlacaECpf(veiculoParametro);

        RestTemplate restTemplate = new RestTemplate();
        //realiza consulta a API externa usando a placa do veiculo que foi recebido como parametro neste metodo
        ResponseEntity<Veiculo> veiculoRetorno =
                restTemplate
                        .getForEntity("https://my.api.mockaroo.com/veiculos?key=55ad1cd0&placa="+veiculoParametro.getPlaca(),Veiculo.class);

        //Este trecho completa o objeto recebido da API externa
        veiculoRetorno.getBody().setProprietario(veiculoParametro.getProprietario());
        veiculoRetorno.getBody().setCpf(veiculoParametro.getCpf());
        veiculoRetorno.getBody().setPlaca(veiculoParametro.getPlaca());

        //aqui estamos salvando o veiculo na base de dados
        veiculoRepository.save(veiculoRetorno.getBody());
        return veiculoRetorno;

    }

    public void verificaPlacaECpf(Veiculo veiculoParametro) throws Exception {

        if (veiculoRepository.existsByCpf(veiculoParametro.getCpf())) {
            throw new Exception("Esse CPF já existe nessa base");
        }
        if (veiculoRepository.existsByPlaca(veiculoParametro.getPlaca())) {
            throw new Exception("Essa placa já existe nessa base");
        }

    }


}
