package historico;

import java.time.LocalDateTime;

// Representa o registro de uma ação realizada pelo usuário
public class RegistroInteracao {
    private final String usuarioId;
    private final String descricao;
    private final LocalDateTime dataHora;

    public RegistroInteracao(String usuarioId, String descricao) {
        this.usuarioId = usuarioId;
        this.descricao = descricao;
        this.dataHora = LocalDateTime.now();
    }

    public String getUsuarioId() { return usuarioId; }
    public String getDescricao() { return descricao; }
    public LocalDateTime getDataHora() { return dataHora; }

    @Override
    public String toString() {
        return String.format("[%s] Usuario=%s, Acao=%s",
                dataHora, usuarioId, descricao);
    }
}
