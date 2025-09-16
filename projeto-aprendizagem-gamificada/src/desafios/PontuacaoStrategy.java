package desafios;

// Interface do padrão Strategy para cálculo de pontuação.
public interface PontuacaoStrategy {
    int calcularPontuacao(int base, int acertos, int tempoSegundos, int dificuldade);
}

