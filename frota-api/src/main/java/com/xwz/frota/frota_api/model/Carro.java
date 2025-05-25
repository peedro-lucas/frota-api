package com.xwz.frota.frota_api.model;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("CARRO")
@PrimaryKeyJoinColumn(name = "veiculo_id")  // <== ADICIONE ESTA LINHA AQUI
public class Carro extends Veiculo {

    private int quantidadePortas;
    private String tipoCombustivel;

    public Carro() {
    }

    public Carro(String modelo, String fabricante, int ano, double preco, String cor,
                 int quantidadePortas, String tipoCombustivel, String tipo) {
        super(modelo, fabricante, ano, preco, cor, tipo);
        this.quantidadePortas = quantidadePortas;
        this.tipoCombustivel = tipoCombustivel;
    }

    public int getQuantidadePortas() {
        return quantidadePortas;
    }

    public void setQuantidadePortas(int quantidadePortas) {
        this.quantidadePortas = quantidadePortas;
    }

    public String getTipoCombustivel() {
        return tipoCombustivel;
    }

    public void setTipoCombustivel(String tipoCombustivel) {
        this.tipoCombustivel = tipoCombustivel;
    }
}
