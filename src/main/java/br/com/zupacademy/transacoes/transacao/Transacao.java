package br.com.zupacademy.transacoes.transacao;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class Transacao {

    @Id
    private String id;
    private BigDecimal valor;
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Estabelecimento estabelecimento;
    @Embedded
    private Cartao cartao;
    private LocalDateTime efetivadaEm;

    public Transacao() {}

    public Transacao(String id, BigDecimal valor, Estabelecimento estabelecimento, Cartao cartao, LocalDateTime efetivadaEm) {
        this.id = id;
        this.valor = valor;
        this.estabelecimento = estabelecimento;
        this.cartao = cartao;
        this.efetivadaEm = efetivadaEm;
    }

    public String getId() {
        return id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Estabelecimento getEstabelecimento() {
        return estabelecimento;
    }

    public String formataData(String pattern) {
        return this.efetivadaEm.format(DateTimeFormatter.ofPattern(pattern));
    }
}
