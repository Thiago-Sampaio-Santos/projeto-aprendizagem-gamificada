package historico;

import util.Log;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Singleton que armazena todas as interações realizadas.
 * Implementa suporte a undo múltiplo e exportação de registros.
 */
public final class HistoricoInteracoes {
    private static volatile HistoricoInteracoes instancia;

    private final List<RegistroInteracao> registros;
    private final Deque<Acao> pilhaAcoes; // suporte a desfazer ações

    private HistoricoInteracoes() {
        registros = new ArrayList<>();
        pilhaAcoes = new ArrayDeque<>();
    }

    public static HistoricoInteracoes getInstance() {
        if (instancia == null) {
            synchronized (HistoricoInteracoes.class) {
                if (instancia == null) instancia = new HistoricoInteracoes();
            }
        }
        return instancia;
    }

    public void registrar(RegistroInteracao registro) {
        registros.add(registro);
        Log.info("[Historico] " + registro);
    }

    public void executarAcao(Acao acao) {
        acao.executar();
        pilhaAcoes.push(acao);
    }

    public void desfazerUltimaAcao() {
        if (!pilhaAcoes.isEmpty()) {
            Acao ultima = pilhaAcoes.pop();
            ultima.desfazer();
        }
    }

    public void desfazerUltimas(int n) {
        for (int i = 0; i < n && !pilhaAcoes.isEmpty(); i++) {
            desfazerUltimaAcao();
        }
    }

    public List<RegistroInteracao> listarRegistros() {
        return List.copyOf(registros);
    }

    public List<String> exportarComoTexto() {
        return registros.stream()
                .map(RegistroInteracao::toString)
                .collect(Collectors.toList());
    }
}
