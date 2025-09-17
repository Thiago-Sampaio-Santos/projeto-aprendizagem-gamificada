package relatorios;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;

// Relatório em formato JSON.
public class RelatorioJSON implements Relatorio {
    @Override
    public void gerar(String caminhoArquivo, List<String> conteudo) throws Exception {
        if (caminhoArquivo == null || caminhoArquivo.isBlank()) {
            throw new IllegalArgumentException("Caminho do arquivo inválido.");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo))) {
            writer.write("{\n  \"relatorio\": [\n");
            for (int i = 0; i < conteudo.size(); i++) {
                writer.write("    { \"indice\": " + (i + 1) + ", \"conteudo\": \"" + conteudo.get(i) + "\" }");
                if (i < conteudo.size() - 1) writer.write(",");
                writer.write("\n");
            }
            writer.write("  ]\n}");
        }
    }
}

