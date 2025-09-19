package util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Classe utilitária para centralizar logs do sistema.
 * Suporta log no console e em arquivo.
 */
public final class Log {
    private static boolean logEmArquivo = false;
    private static String caminhoArquivo = "logs/logs.txt";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private Log() { }

    public static void configurarLogArquivo(String caminho) {
        logEmArquivo = true;
        caminhoArquivo = caminho;

        // Garantir que a pasta existe
        File arquivo = new File(caminhoArquivo);
        File pasta = arquivo.getParentFile();
        if (pasta != null && !pasta.exists()) {
            pasta.mkdirs();
        }
    }

    public static void info(String mensagem) {
        registrar("INFO", mensagem);
    }

    public static void warn(String mensagem) {
        registrar("WARN", mensagem);
    }

    public static void error(String mensagem) {
        registrar("ERROR", mensagem);
    }

    private static void registrar(String nivel, String mensagem) {
        String linha = String.format("[%s] [%s]%n%s",
                LocalDateTime.now().format(FORMATTER), nivel, mensagem);

        // Console
        System.out.println(linha);

        // Arquivo (se ativado)
        if (logEmArquivo) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(caminhoArquivo, true))) {
                writer.println(linha);
                writer.println();
            } catch (IOException e) {
                System.err.println("Falha ao escrever no log: " + e.getMessage());
            }
        }
    }
}
