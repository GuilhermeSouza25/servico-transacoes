package br.com.zupacademy.transacoes.listener;

import br.com.zupacademy.transacoes.transacao.Estabelecimento;

public class EstabelecimentoResponse {

    private String nome;
    private String cidade;
    private String endereco;

    @Deprecated
    public EstabelecimentoResponse() {}

    public EstabelecimentoResponse(String nome, String cidade, String endereco) {
        this.nome = nome;
        this.cidade = cidade;
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEndereco() {
        return endereco;
    }

    @Override
    public String toString() {
        return "EstabelecimentoResponse{" +
                "nome='" + nome + '\'' +
                ", cidade='" + cidade + '\'' +
                ", endereco='" + endereco + '\'' +
                '}';
    }

    public Estabelecimento converter() {
        return new Estabelecimento(nome, cidade, endereco);
    }
}
