package relatorios;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;

/**
 * Relatório em formato CSV.
 * Cada linha contém: data;usuario;acao
 */
public class RelatorioCSV implements Relatorio {
    @Override
    public void gerar(String caminhoArquivo, List<String> conteudo) throws Exception {
        if (caminhoArquivo == null || caminhoArquivo.isBlank()) {
            throw new IllegalArgumentException("Caminho do arquivo inválido.");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo))) {
            // Cabeçalho
            writer.write("timestamp;usuario;acao\n");

            for (String linha : conteudo) {
                // Exemplo de entrada: [2025-09-15T19:09:06.562657400] Usuario=u1, Acao=Respondeu quiz 'Quiz de Padrões de Projeto' e ganhou 75 pontos.
                String[] partes = linha.split(" ", 2); // separa apenas timestamp e o resto
                String timestamp = partes[0].replace("[", "").replace("]", "");
                String descricao = partes.length > 1 ? partes[1] : "";

                // Garante que campos com espaços vão entre aspas
                writer.write(String.format("%s;\"%s\"\n", timestamp, descricao));
            }
        }
    }
}
