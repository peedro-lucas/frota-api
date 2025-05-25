package com.xwz.frota.frota_api.model;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("MOTO")
@PrimaryKeyJoinColumn(name = "veiculo_id")  // <== ADICIONE ESTA LINHA AQUI
public class Moto extends Veiculo {
    private int cilindrada;

    public Moto() {
    }

    public Moto(String modelo, String fabricante, int ano, double preco, String cor, int cilindrada, String tipo) {
        super(modelo, fabricante, ano, preco, cor, tipo);
        this.cilindrada = cilindrada;
    }

    public int getCilindrada() {
        return cilindrada;
    }

    public void setCilindrada(int cilindrada) {
        this.cilindrada = cilindrada;
    }
}
