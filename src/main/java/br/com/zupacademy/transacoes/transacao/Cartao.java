package br.com.zupacademy.transacoes.transacao;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Embeddable
public class Cartao {

    @Column(name = "cartao_id")
    private String id;
    private String email;

    public Cartao() {}

    public Cartao(String id, String email) {
        this.id = id;
        this.email = email;
    }
}
