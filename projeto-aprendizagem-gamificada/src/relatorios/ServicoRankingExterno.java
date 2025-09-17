package relatorios;

import java.util.Arrays;
import java.util.List;

// Serviço externo simulado (não segue nosso padrão interno).
public class ServicoRankingExterno implements RankingServico {
    @Override
    public List<String> obterRanking() {
        return Arrays.asList(
                "1 - Alice (1500 pts)",
                "2 - Bob (1200 pts)",
                "3 - Carol (900 pts)"
        );
    }
}

