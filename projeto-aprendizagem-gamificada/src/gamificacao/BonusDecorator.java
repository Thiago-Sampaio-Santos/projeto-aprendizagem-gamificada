package gamificacao;

// Decorator base para aplicar bônus em conquistas.
public abstract class BonusDecorator extends Conquista {
    protected final Conquista conquistaDecorada;

    public BonusDecorator(Conquista conquistaDecorada, String nome, String descricao, String categoria) {
        super(conquistaDecorada.getId(), nome, descricao, categoria);
        this.conquistaDecorada = conquistaDecorada;
    }
}

