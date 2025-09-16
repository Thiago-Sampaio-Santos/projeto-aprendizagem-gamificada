package gamificacao;

// Medalha simples (folha no Composite).
public class Medalha extends Conquista {
    private final int valor;

    public Medalha(String id, String nome, String descricao, String categoria, int valor) {
        super(id, nome, descricao, categoria);
        if (valor <= 0) throw new IllegalArgumentException("Valor da medalha deve ser positivo");
        this.valor = valor;
    }

    @Override
    public int getValor() { return valor; }

    @Override
    public String exibir() {
        return " Medalha: " + nome + " (" + descricao + ") valor=" + valor;
    }

    @Override
    public String toRelatorio() {
        return String.format("Medalha[id=%s, nome=%s, categoria=%s, valor=%d, data=%s]",
                id, nome, categoria, valor, dataConquista);
    }
}

