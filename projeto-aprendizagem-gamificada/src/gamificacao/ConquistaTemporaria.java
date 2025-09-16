package gamificacao;

import java.time.LocalDate;

// Conquista válida apenas até uma data limite.
public class ConquistaTemporaria extends Conquista {
    private final int valor;
    private final LocalDate validade;

    public ConquistaTemporaria(String id, String nome, String descricao, String categoria, int valor, LocalDate validade) {
        super(id, nome, descricao, categoria);
        if (valor <= 0) throw new IllegalArgumentException("Valor deve ser positivo");
        if (validade == null || validade.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Validade deve ser futura");
        }
        this.valor = valor;
        this.validade = validade;
    }

    public boolean estaValida() {
        return LocalDate.now().isBefore(validade) || LocalDate.now().isEqual(validade);
    }

    @Override
    public int getValor() {
        return estaValida() ? valor : 0;
    }

    @Override
    public String exibir() {
        return " Conquista Temporária: " + nome + " (" + descricao + ") até " + validade;
    }

    @Override
    public String toRelatorio() {
        return String.format("ConquistaTemporaria[id=%s, nome=%s, categoria=%s, valor=%d, validade=%s, ativa=%s]",
                id, nome, categoria, valor, validade, estaValida());
    }
}

