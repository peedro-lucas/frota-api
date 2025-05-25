package com.xwz.frota.frota_api.repository;

import com.xwz.frota.frota_api.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Integer> , VeiculoRepositoryCustom{

    // Buscar veículos cujo modelo contenha o texto, ignorando maiúsculas/minúsculas
    List<Veiculo> findByModeloContainingIgnoreCase(String modelo);

    // Buscar veículos por cor exata
    List<Veiculo> findByCor(String cor);

    // Buscar veículos pelo ano exato
    List<Veiculo> findByAno(int ano);

    // Buscar veículos pelo tipo (Carro ou Moto)
    List<Veiculo> findByTipo(String tipo);

    // Combinar filtros - exemplo para modelo e cor (você pode criar o que precisar)
    List<Veiculo> findByModeloContainingIgnoreCaseAndCor(String modelo, String cor);
}




