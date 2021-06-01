package br.com.zupacademy.transacoes.listener;

import br.com.zupacademy.transacoes.transacao.Cartao;

public class CartaoResponse {

    private String id;
    private String email;

    @Deprecated
    public CartaoResponse() {}

    public CartaoResponse(String id, String email) {
        this.id = id;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "CartaoResponse{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public Cartao converter() {
        return new Cartao(id, email);
    }
}
