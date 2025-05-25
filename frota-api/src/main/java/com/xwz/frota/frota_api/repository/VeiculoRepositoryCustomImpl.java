package com.xwz.frota.frota_api.repository;

import com.xwz.frota.frota_api.model.Carro;
import com.xwz.frota.frota_api.model.Moto;
import com.xwz.frota.frota_api.model.Veiculo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
public class VeiculoRepositoryCustomImpl implements VeiculoRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Veiculo> buscarPorFiltros(String tipo, String modelo, String cor, Integer ano) {
        StringBuilder sql = new StringBuilder("SELECT * FROM veiculo v ");

        if ("carro".equalsIgnoreCase(tipo)) {
            sql.append("JOIN carro c ON v.id = c.veiculo_id WHERE 1=1 ");
        } else if ("moto".equalsIgnoreCase(tipo)) {
            sql.append("JOIN moto c ON v.id = c.veiculo_id WHERE 1=1 ");
        }


        Map<String, Object> params = new HashMap<>();

        if (tipo != null && !tipo.isEmpty()) {
            sql.append(" AND LOWER(tipo) = :tipo");
            params.put("tipo", tipo.toLowerCase());
        }

        if (modelo != null && !modelo.isEmpty()) {
            sql.append(" AND LOWER(modelo) LIKE :modelo");
            params.put("modelo", "%" + modelo.toLowerCase() + "%");
        }

        if (cor != null && !cor.isEmpty()) {
            sql.append(" AND LOWER(cor) = :cor");
            params.put("cor", cor.toLowerCase());
        }

        if (ano != null) {
            sql.append(" AND ano = :ano");
            params.put("ano", ano);
        }

        Query query;

        if ("carro".equalsIgnoreCase(tipo)) {
            query = entityManager.createNativeQuery(sql.toString(), Carro.class);
        } else if ("moto".equalsIgnoreCase(tipo)) {
            query = entityManager.createNativeQuery(sql.toString(), Moto.class);
        } else {
            query = entityManager.createNativeQuery(sql.toString(), Carro.class);
        }

        for (Map.Entry<String, Object> entry : params.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }

        return query.getResultList();
    }
}
