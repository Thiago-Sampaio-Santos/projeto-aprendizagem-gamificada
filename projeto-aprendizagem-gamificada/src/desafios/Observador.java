package desafios;

// Interface Observer para notificações.
public interface Observador {
    void atualizar(String mensagem, TipoEvento tipo);

    enum TipoEvento {
        DESAFIO_CONCLUIDO,
        MEDALHA_GANHA,
        LOG_SISTEMA
    }
}

