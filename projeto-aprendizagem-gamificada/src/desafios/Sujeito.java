package desafios;

import java.util.ArrayList;
import java.util.List;

// Classe base Subject.
public abstract class Sujeito {
    private final List<Observador> observadores = new ArrayList<>();

    public void adicionarObservador(Observador o) {
        observadores.add(o);
    }

    public void removerObservador(Observador o) {
        observadores.remove(o);
    }

    protected void notificarObservadores(String mensagem, Observador.TipoEvento tipo) {
        for (Observador o : observadores) {
            o.atualizar(mensagem, tipo);
        }
    }
}
