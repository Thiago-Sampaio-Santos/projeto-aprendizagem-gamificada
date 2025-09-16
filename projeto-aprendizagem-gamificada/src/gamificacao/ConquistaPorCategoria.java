package gamificacao;

import java.util.ArrayList;
import java.util.List;

// Agrupa conquistas por categoria (Composite).
public class ConquistaPorCategoria extends Conquista {
    private final List<Conquista> conquistas = new ArrayList<>();

    public ConquistaPorCategoria(String id, String nome, String descricao, String categoria) {
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
        StringBuilder sb = new StringBuilder("📚 Categoria: " + nome + "\n");
        for (Conquista c : conquistas) {
            sb.append("   -> ").append(c.exibir()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public String toRelatorio() {
        return String.format("ConquistaPorCategoria[id=%s, nome=%s, categoria=%s, total=%d, itens=%d]",
                id, nome, categoria, getValor(), conquistas.size());
    }
}

