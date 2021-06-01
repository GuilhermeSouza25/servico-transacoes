package br.com.zupacademy.transacoes.transacao;

import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransacaoResponse {

    private String codigo;
    private BigDecimal valor;
    private String efetivadaEm;
    private EstabelecimentoResponse estabelecimento;

    public TransacaoResponse(Transacao transacao) {
        this.codigo = transacao.getId();
        this.valor = transacao.getValor();
        this.efetivadaEm = transacao.formataData("dd/MM/yyyy HH:mm");
        this.estabelecimento = new EstabelecimentoResponse(transacao.getEstabelecimento());
    }

    public String getCodigo() {
        return codigo;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public String getEfetivadaEm() {
        return efetivadaEm;
    }

    public EstabelecimentoResponse getEstabelecimento() {
        return estabelecimento;
    }

    public static Page<TransacaoResponse> converter(Page<Transacao> transacoes) {
        return transacoes.map(TransacaoResponse::new);
    }
}
