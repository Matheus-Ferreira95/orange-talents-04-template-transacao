package br.com.zupacademy.matheus.transacao.cartao;

import javax.persistence.*;

@Entity
public class Cartao {

    @Id
    private String numero;

    private String email;

    @Deprecated
    public Cartao(){}

    public Cartao(String numero, String email) {
        this.numero = numero;
        this.email = email;
    }
}
