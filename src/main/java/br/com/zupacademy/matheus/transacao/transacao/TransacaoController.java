package br.com.zupacademy.matheus.transacao.transacao;

import br.com.zupacademy.matheus.transacao.feign.TransacaoClient;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    private final Logger log = LoggerFactory.getLogger(TransacaoController.class);
    private final TransacaoClient transacaoClient;

    public TransacaoController(TransacaoClient transacaoClient) {
        this.transacaoClient = transacaoClient;
    }

    @PostMapping
    public void ativaTransacao(@Valid @RequestBody TransacaoRequest request) {
        try {
            transacaoClient.ativaTransacao(request);
            log.info("Transação iniciada para o cartão {}", request.getId());
        } catch (FeignException ex) {
            log.warn("Não foi possível iniciar uma transação...");
            ex.printStackTrace();
        }
    }

    @DeleteMapping("/{id}")
    public void paraTransacao(@PathVariable String id) {
        try {
            transacaoClient.paraTransacao(id);
            log.info("Transação interrompida para o cartão {}", id);
        } catch (FeignException ex) {
            log.warn("Não foi possível parar esta transação...");
            ex.printStackTrace();
        }
    }
}
