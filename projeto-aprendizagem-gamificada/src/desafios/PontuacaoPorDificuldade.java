package desafios;

// Estratégia que multiplica pontos pelo nível de dificuldade.
public class PontuacaoPorDificuldade implements PontuacaoStrategy {
    @Override
    public int calcularPontuacao(int base, int acertos, int tempoSegundos, int dificuldade) {
        return (base + acertos * 15) * dificuldade;
    }
}

