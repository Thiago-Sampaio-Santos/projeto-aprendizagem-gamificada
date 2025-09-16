package gamificacao;

// Bônus por streak de dias consecutivos.
public class StreakBonus extends BonusDecorator {
    private final int streakDias;

    public StreakBonus(Conquista conquista, int streakDias) {
        super(conquista, conquista.getNome() + " + Streak", "Bônus por streak", conquista.getCategoria());
        if (streakDias < 0) throw new IllegalArgumentException("Streak não pode ser negativo");
        this.streakDias = streakDias;
    }

    @Override
    public int getValor() {
        return conquistaDecorada.getValor() + streakDias * 5;
    }

    @Override
    public String exibir() {
        return conquistaDecorada.exibir() + "  [Streak +" + (streakDias * 5) + "]";
    }

    @Override
    public String toRelatorio() {
        return String.format("StreakBonus[id=%s, base=%d, streak=%d, valorFinal=%d]",
                id, conquistaDecorada.getValor(), streakDias, getValor());
    }
}

