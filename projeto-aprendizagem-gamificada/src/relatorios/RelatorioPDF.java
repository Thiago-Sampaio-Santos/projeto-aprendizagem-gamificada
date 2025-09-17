package relatorios;

import java.io.File;
import java.util.List;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

/**
 * Relatório em formato PDF com layout mais organizado.
 */
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
                stream.newLineAtOffset(margin, yStart - 50); // início abaixo do título

                for (String linha : conteudo) {
                    stream.showText(linha);
                    stream.newLine();
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
}
