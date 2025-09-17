package relatorios;

import java.util.List;

// Interface para serviços de ranking externo (Adapter target).
public interface RankingServico {
    List<String> obterRanking();
}

