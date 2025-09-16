package desafios;

// Estratégia que foca na taxa de acertos.
public class PontuacaoPorAcerto implements PontuacaoStrategy {
    @Override
    public int calcularPontuacao(int base, int acertos, int tempoSegundos, int dificuldade) {
        return base + acertos * 20;
    }
}

