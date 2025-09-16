package desafios;

// Observador simples que mostra mensagens no console.
public class NotificacaoObserver implements Observador {
    private final String nome;

    public NotificacaoObserver(String nome) {
        this.nome = nome;
    }

    @Override
    public void atualizar(String mensagem, TipoEvento tipo) {
        System.out.println("[Notificação - " + nome + "][" + tipo + "]: " + mensagem);
    }
}

