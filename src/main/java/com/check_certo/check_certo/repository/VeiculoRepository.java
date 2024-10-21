package com.check_certo.check_certo.repository;

import com.check_certo.check_certo.domain.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

    boolean existsByCpf(String cpf);
    boolean existsByPlaca(String placa);

}
