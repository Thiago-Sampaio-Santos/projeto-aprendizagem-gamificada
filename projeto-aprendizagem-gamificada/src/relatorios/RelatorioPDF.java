package relatorios;

import java.io.File;
import java.util.List;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

// Relatório em formato PDF

public class RelatorioPDF implements Relatorio {

    @Override
    public void gerar(String caminhoArquivo, List<String> conteudo) throws Exception {
        if (caminhoArquivo == null || caminhoArquivo.isBlank()) {
            throw new IllegalArgumentException("Caminho do arquivo inválido.");
        }

        try (PDDocument documento = new PDDocument()) {
            PDPage pagina = new PDPage(PDRectangle.A4);
            documento.addPage(pagina);

            try (PDPageContentStream stream = new PDPageContentStream(documento, pagina)) {
                float margin = 50;
                float yStart = pagina.getMediaBox().getHeight() - margin;
                float xStart = margin;
                float larguraMaxima = pagina.getMediaBox().getWidth() - 2 * margin;

                // === TÍTULO CENTRALIZADO ===
                stream.beginText();
                stream.setFont(PDType1Font.HELVETICA_BOLD, 16);
                float titleWidth = PDType1Font.HELVETICA_BOLD.getStringWidth("Relatório de Interações") / 1000 * 16;
                float titleX = (pagina.getMediaBox().getWidth() - titleWidth) / 2;
                stream.newLineAtOffset(titleX, yStart);
                stream.showText("Relatório de Interações");
                stream.endText();

                // === LINHAS DO RELATÓRIO ===
                stream.beginText();
                stream.setFont(PDType1Font.HELVETICA, 12);
                stream.setLeading(18f);
                stream.newLineAtOffset(xStart, yStart - 50); // margem esquerda

                for (String linha : conteudo) {
                    int start = 0;
                    while (start < linha.length()) {
                        int end = findBreakPosition(linha, start, larguraMaxima, PDType1Font.HELVETICA, 12);
                        stream.showText(linha.substring(start, end));
                        stream.newLine();
                        start = end;
                    }
                }

                stream.endText();
            }

            // Garante que termina com ".pdf"
            if (!caminhoArquivo.endsWith(".pdf")) {
                caminhoArquivo += ".pdf";
            }

            // Criar pasta caso não exista
            File arquivo = new File(caminhoArquivo);
            File pasta = arquivo.getParentFile();
            if (pasta != null && !pasta.exists()) {
                pasta.mkdirs();
            }

            documento.save(caminhoArquivo);
        }
    }

    /**
     * Encontra a posição de quebra de linha para não ultrapassar a largura máxima.
     */
    private int findBreakPosition(String text, int start, float maxWidth, PDType1Font font, float fontSize) throws Exception {
        int end = start;
        while (end < text.length()) {
            float width = font.getStringWidth(text.substring(start, end + 1)) / 1000 * fontSize;
            if (width > maxWidth) {
                break;
            }
            end++;
        }
        return end == start ? start + 1 : end;
    }
}
