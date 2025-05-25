package com.xwz.frota.frota_api.repository;

import com.xwz.frota.frota_api.model.Veiculo;
import java.util.List;

public interface VeiculoRepositoryCustom {
    List<Veiculo> buscarPorFiltros(String tipo, String modelo, String cor, Integer ano);
}
