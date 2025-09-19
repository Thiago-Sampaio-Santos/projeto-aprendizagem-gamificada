package relatorios;

import java.util.Arrays;
import java.util.List;

// Serviço externo simulado (não segue nosso padrão interno).
public class ServicoRankingExterno implements RankingServico {
    @Override
    public List<String> obterRanking() {
        return Arrays.asList(
                "1 - Thiago (1500 pts)",
                "2 - Kleberson (1200 pts)",
                "3 - Hildemar (900 pts)"
        );
    }
}

