package desafios;

import java.util.ArrayList;
import java.util.List;

// Strategy composta: combina várias estratégias.
public class PontuacaoCompostaStrategy implements PontuacaoStrategy {
    private final List<PontuacaoStrategy> estrategias = new ArrayList<>();

    public void adicionarEstrategia(PontuacaoStrategy estrategia) {
        estrategias.add(estrategia);
    }

    @Override
    public int calcularPontuacao(int base, int acertos, int tempoSegundos, int dificuldade) {
        int total = 0;
        for (PontuacaoStrategy e : estrategias) {
            total += e.calcularPontuacao(base, acertos, tempoSegundos, dificuldade);
        }
        return total / Math.max(1, estrategias.size()); // média das estratégias
    }
}

