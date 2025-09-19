package desafios;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

// Observer que grava notificações em um arquivo de log.
public class LogObserver implements Observador {
    private final String arquivo;

    public LogObserver(String arquivo) {
        this.arquivo = arquivo;
    }

    @Override
    public void atualizar(String mensagem, TipoEvento tipo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo, true))) {
            String logEntry = String.format(
                "[%s] %s%n",
                tipo.name(),
                mensagem
            );
            writer.write(logEntry);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
