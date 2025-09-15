package relatorios;

import java.util.List;
import java.util.stream.Collectors;

// Adapter que adapta o serviço externo para nosso formato interno de string.
public class RankingAdapter {
    private final RankingServico servico;

    public RankingAdapter(RankingServico servico) {
        this.servico = servico;
    }

    public String getRanking() {
        List<String> lista = servico.obterRanking();
        return lista.stream().collect(Collectors.joining("\n"));
    }
}

