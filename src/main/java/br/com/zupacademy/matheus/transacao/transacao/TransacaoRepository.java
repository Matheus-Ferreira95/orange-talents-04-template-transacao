package br.com.zupacademy.matheus.transacao.transacao;

import br.com.zupacademy.matheus.transacao.cartao.Cartao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

    Page<Transacao> findByCartaoOrderByEfetivadaEmDesc(Cartao cartao, Pageable pageable);
}
