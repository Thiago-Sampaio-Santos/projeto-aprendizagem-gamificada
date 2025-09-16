package gamificacao;

// Troféu: conquista rara e de valor alto.
public class Trofeu extends Conquista {
    private final int valor;

    public Trofeu(String id, String nome, String descricao, String categoria, int valor) {
        super(id, nome, descricao, categoria);
        if (valor < 100) throw new IllegalArgumentException("Troféu deve ter valor >= 100");
        this.valor = valor;
    }

    @Override
    public int getValor() { return valor; }

    @Override
    public String exibir() {
        return "🏆 Troféu: " + nome + " (" + descricao + ") valor=" + valor;
    }

    @Override
    public String toRelatorio() {
        return String.format("Trofeu[id=%s, nome=%s, categoria=%s, valor=%d, data=%s]",
                id, nome, categoria, valor, dataConquista);
    }
}

