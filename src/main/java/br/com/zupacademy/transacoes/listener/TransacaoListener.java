package br.com.zupacademy.transacoes.listener;

import br.com.zupacademy.transacoes.configs.ExecutorTransacao;
import br.com.zupacademy.transacoes.transacao.Transacao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class TransacaoListener {

    @Autowired ExecutorTransacao executorTransacao;

    private final Logger logger = LoggerFactory.getLogger(TransacaoListener.class);

    @KafkaListener(topics = "${spring.kafka.topic.transactions}")
    public void ouvir(EventoDeTransacao eventoDeTransacao) {

        Transacao transacao = eventoDeTransacao.converter();

        try {
            executorTransacao.salvaEComita(transacao);
            logger.info("Nova transacao sava com id={}", eventoDeTransacao.getId());
        } catch (Exception e) {
            logger.error("Não foi possivel salvar a transação");
        }
    }
}



