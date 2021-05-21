package br.com.zupacademy.matheus.transacao.transacao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ListenerDeTransacao {

    private final Logger log = LoggerFactory.getLogger(ListenerDeTransacao.class);
    private final TransacaoRepository transacaoRepository;

    public ListenerDeTransacao(TransacaoRepository transacaoRepository) {
        this.transacaoRepository = transacaoRepository;
    }

    @KafkaListener(topics = "${spring.kafka.topic.transactions}")
    public void ouvir(EventoDeTransacao eventoDeTransacao) {
        transacaoRepository.save(eventoDeTransacao.toModel());
        log.info("Nova transação salva {}", eventoDeTransacao.toString());
    }
}