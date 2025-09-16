package desafios;

//Estratégia que recompensa rapidez.
public class PontuacaoPorTempo implements PontuacaoStrategy {
    @Override
    public int calcularPontuacao(int base, int acertos, int tempoSegundos, int dificuldade) {
        int bonusTempo = (tempoSegundos < 60) ? 50 : 0;
        return base + acertos * 10 + bonusTempo;
    }
}

