package gamificacao;

import java.time.LocalDate;
import java.util.Objects;

// Classe base para conquistas (Composite).
public abstract class Conquista {
    protected final String id;
    protected final String nome;
    protected final String descricao;
    protected final String categoria;
    protected final LocalDate dataConquista;

    public Conquista(String id, String nome, String descricao, String categoria) {
        this.id = Objects.requireNonNull(id, "id não pode ser nulo");
        this.nome = Objects.requireNonNull(nome, "nome não pode ser nulo");
        this.descricao = Objects.requireNonNull(descricao, "descricao não pode ser nula");
        this.categoria = categoria != null ? categoria : "Geral";
        this.dataConquista = LocalDate.now();
    }

    public String getId() { return id; }
    public String getNome() { return nome; }
    public String getDescricao() { return descricao; }
    public String getCategoria() { return categoria; }
    public LocalDate getDataConquista() { return dataConquista; }

    public abstract int getValor();
    public abstract String exibir();
    public abstract String toRelatorio();
}

