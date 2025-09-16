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
            writer.write("[" + tipo + "] " + mensagem + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
