package br.com.zupacademy.matheus.transacao.transacao;

import br.com.zupacademy.matheus.transacao.estabelecimento.EstabelecimentoResponse;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransacaoResponse {

    private BigDecimal valor;
    private EstabelecimentoResponse estabeleciomento;
    private LocalDateTime efetivadaEm;

    public TransacaoResponse(Transacao transacao) {
        this.valor = transacao.getValor();
        this.estabeleciomento = new EstabelecimentoResponse(transacao.getEstabelecimento());
        this.efetivadaEm = transacao.getEfetivadaEm();
    }

    public BigDecimal getValor() {
        return valor;
    }

    public EstabelecimentoResponse getEstabeleciomento() {
        return estabeleciomento;
    }

    public LocalDateTime getEfetivadaEm() {
        return efetivadaEm;
    }
}
