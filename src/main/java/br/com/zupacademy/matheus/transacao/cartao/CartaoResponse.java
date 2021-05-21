package br.com.zupacademy.matheus.transacao.cartao;

public class CartaoResponse {

    private String id;
    private String email;

    public CartaoResponse() {
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public Cartao toModel() {
        return new Cartao(id, email);
    }

    @Override
    public String toString() {
        return "CartaoRequest{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
