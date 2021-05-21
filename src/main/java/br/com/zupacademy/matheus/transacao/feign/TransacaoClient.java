package br.com.zupacademy.matheus.transacao.feign;

import br.com.zupacademy.matheus.transacao.transacao.TransacaoRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "transacao", url = "${transacao.client.url}")
public interface TransacaoClient {

    @PostMapping
    void ativaTransacao(@RequestBody TransacaoRequest request);

    @DeleteMapping("/{id}")
    void paraTransacao(@PathVariable String id);
}
