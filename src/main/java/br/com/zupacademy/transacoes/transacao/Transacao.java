package br.com.zupacademy.transacoes.transacao;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Transacao {

    @Id
    private String id;
    private BigDecimal valor;
    @ManyToOne(cascade = CascadeType.PERSIST)
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


}
