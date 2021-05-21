package br.com.zupacademy.matheus.transacao.transacao;

import br.com.zupacademy.matheus.transacao.cartao.Cartao;
import br.com.zupacademy.matheus.transacao.cartao.CartaoRepository;
import br.com.zupacademy.matheus.transacao.feign.TransacaoClient;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    private final Logger log = LoggerFactory.getLogger(TransacaoController.class);
    private final TransacaoClient transacaoClient;
    private final TransacaoRepository transacaoRepository;
    private final CartaoRepository cartaoRepository;

    public TransacaoController(TransacaoClient transacaoClient, TransacaoRepository transacaoRepository, CartaoRepository cartaoRepository) {
        this.transacaoClient = transacaoClient;
        this.transacaoRepository = transacaoRepository;
        this.cartaoRepository = cartaoRepository;
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

    @GetMapping("/{id}")
    public ResponseEntity<?>  ultimasDezTransacoes(@PathVariable String id) {
        Optional<Cartao> possivelCartao = cartaoRepository.findByNumero(id);
        if (possivelCartao.isEmpty()) {
            log.warn("Cartão não encontrado");
            return ResponseEntity.notFound().build();
        }
        Page<Transacao> transacoes = transacaoRepository.findByCartaoOrderByEfetivadaEmDesc(possivelCartao.get(), PageRequest.of(0, 10));
        return ResponseEntity.ok(transacoes.map(TransacaoResponse::new).stream().collect(Collectors.toList()));
    }
}
