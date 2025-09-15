package gamificacao;

import java.util.ArrayList;
import java.util.List;

// Composite de conquistas (pode agrupar medalhas, troféus, etc).
public class ConjuntoMedalhasComposite extends Conquista {
    private final List<Conquista> conquistas = new ArrayList<>();

    public ConjuntoMedalhasComposite(String id, String nome, String descricao, String categoria) {
        super(id, nome, descricao, categoria);
    }

    public void adicionar(Conquista c) { conquistas.add(c); }
    public void remover(Conquista c) { conquistas.remove(c); }
    public List<Conquista> getConquistas() { return List.copyOf(conquistas); }

    @Override
    public int getValor() {
        return conquistas.stream().mapToInt(Conquista::getValor).sum();
    }

    @Override
    public String exibir() {
        StringBuilder sb = new StringBuilder("🎖️ Conjunto: " + nome + "\n");
        for (Conquista c : conquistas) {
            sb.append("   -> ").append(c.exibir()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public String toRelatorio() {
        return String.format("ConjuntoMedalhas[id=%s, nome=%s, conquistas=%d, valorTotal=%d]",
                id, nome, conquistas.size(), getValor());
    }
}

