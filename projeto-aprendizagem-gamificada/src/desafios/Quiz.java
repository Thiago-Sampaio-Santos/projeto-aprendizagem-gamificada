package desafios;

import java.util.List;

// Desafio do tipo quiz.
public class Quiz extends Desafio {
    private final int totalPerguntas;
    private final List<String> gabarito;

    public Quiz(String id, String titulo, String descricao, int dificuldade, int totalPerguntas,
                List<String> gabarito, PontuacaoStrategy estrategia) {
        super(id, titulo, descricao, dificuldade, estrategia);
        this.totalPerguntas = totalPerguntas;
        this.gabarito = gabarito;
    }

    public boolean validarResposta(List<String> respostas) {
        if (respostas.size() != gabarito.size()) return false;
        return respostas.equals(gabarito);
    }

    @Override
    public int resolver(int acertos, int tempoSegundos) {
        registrarTentativa();
        int base = totalPerguntas * 5;
        int pontos = estrategiaPontuacao.calcularPontuacao(base, acertos, tempoSegundos, dificuldade);
        atualizarPontuacaoMaxima(pontos);
        notificarObservadores("Quiz '" + titulo + "' resolvido! Pontos ganhos: " + pontos,
                Observador.TipoEvento.DESAFIO_CONCLUIDO);
        return pontos;
    }

    @Override
    public String toRelatorio() {
        return String.format("Quiz [%s]: %s | Tentativas = %d | Melhor Pontuação = %d",
                id, titulo, tentativas, pontuacaoMaxima);
    }
}

