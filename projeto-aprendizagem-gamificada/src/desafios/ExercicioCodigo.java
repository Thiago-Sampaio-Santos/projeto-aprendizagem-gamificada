package desafios;

// Desafio do tipo exercício de código.
public class ExercicioCodigo extends Desafio {
    private final int linhasEsperadas;
    private final String linguagemEsperada;
    private final String tipoProblema;

    public ExercicioCodigo(String id, String titulo, String descricao, int dificuldade,
                           int linhasEsperadas, String linguagemEsperada, String tipoProblema,
                           PontuacaoStrategy estrategia) {
        super(id, titulo, descricao, dificuldade, estrategia);
        this.linhasEsperadas = linhasEsperadas;
        this.linguagemEsperada = linguagemEsperada;
        this.tipoProblema = tipoProblema;
    }

    public boolean simularExecucao(String codigo) {
        return codigo != null && codigo.contains("return");
    }

    @Override
    public int resolver(int acertos, int tempoSegundos) {
        registrarTentativa();
        int base = linhasEsperadas * 2;
        int pontos = estrategiaPontuacao.calcularPontuacao(base, acertos, tempoSegundos, dificuldade);
        atualizarPontuacaoMaxima(pontos);
        notificarObservadores("Exercício de código '" + titulo + "' concluído! Pontos ganhos: " + pontos,
                Observador.TipoEvento.DESAFIO_CONCLUIDO);
        return pontos;
    }

    @Override
    public String toRelatorio() {
        return String.format("ExercicioCodigo[%s]: %s (%s/%s) | Tentativas=%d | Melhor Pontuação=%d",
                id, titulo, linguagemEsperada, tipoProblema, tentativas, pontuacaoMaxima);
    }
}

