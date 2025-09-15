package relatorios;

import java.util.List;

// Facade para centralizar geração de relatórios.
public class RelatorioFacade {
    private final Relatorio relatorioPDF;
    private final Relatorio relatorioCSV;
    private final Relatorio relatorioJSON;
    private final RankingAdapter rankingAdapter;

    public RelatorioFacade() {
        this.relatorioPDF = new RelatorioPDF();
        this.relatorioCSV = new RelatorioCSV();
        this.relatorioJSON = new RelatorioJSON();
        this.rankingAdapter = new RankingAdapter(new ServicoRankingExterno());
    }

    public void gerarPDF(String caminho, List<String> conteudo) throws Exception {
        relatorioPDF.gerar(caminho, conteudo);
    }

    public void gerarCSV(String caminho, List<String> conteudo) throws Exception {
        relatorioCSV.gerar(caminho, conteudo);
    }

    public void gerarJSON(String caminho, List<String> conteudo) throws Exception {
        relatorioJSON.gerar(caminho, conteudo);
    }

    public String obterRankingGlobal() {
        return rankingAdapter.getRanking();
    }
}

