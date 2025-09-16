package gamificacao;

// Bônus que dobra valor da conquista.
public class DoubleXPBonus extends BonusDecorator {
    public DoubleXPBonus(Conquista conquista) {
        super(conquista, conquista.getNome() + " + DoubleXP", "Bônus de pontos dobrados", conquista.getCategoria());
    }

    @Override
    public int getValor() {
        return conquistaDecorada.getValor() * 2;
    }

    @Override
    public String exibir() {
        return conquistaDecorada.exibir() + " ✨ [Double XP]";
    }

    @Override
    public String toRelatorio() {
        return String.format("DoubleXPBonus[id=%s, base=%d, valorFinal=%d]",
                id, conquistaDecorada.getValor(), getValor());
    }
}

