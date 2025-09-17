package relatorios;

import java.util.List;

// Interface comum para relatórios em diferentes formatos.
public interface Relatorio {
    void gerar(String caminhoArquivo, List<String> conteudo) throws Exception;
}

