package br.com.zupacademy.matheus.transacao.transacao;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class TransacaoRequest {

    @NotBlank
    private String id;

    @NotBlank
    @Email
    private String email;

    public TransacaoRequest(String id, String email) {
        this.id = id;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}
