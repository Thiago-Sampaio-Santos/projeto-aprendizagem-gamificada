package gamificacao;

import java.util.List;

// Aplica múltiplos bônus combinados.
public class BonusMultiploDecorator extends BonusDecorator {
    private final List<BonusDecorator> bonusAplicados;

    public BonusMultiploDecorator(Conquista conquista, List<BonusDecorator> bonusAplicados) {
        super(conquista, conquista.getNome() + " + MultiBonus", "Vários bônus aplicados", conquista.getCategoria());
        this.bonusAplicados = bonusAplicados;
    }

    @Override
    public int getValor() {
        int valor = conquistaDecorada.getValor();
        for (BonusDecorator b : bonusAplicados) {
            valor = b.getValor(); // aplica na sequência
        }
        return valor;
    }

    @Override
    public String exibir() {
        StringBuilder sb = new StringBuilder(conquistaDecorada.exibir());
        for (BonusDecorator b : bonusAplicados) {
            sb.append(" + ").append(b.getClass().getSimpleName());
        }
        return sb.toString();
    }

    @Override
    public String toRelatorio() {
        return String.format("BonusMultiplo[id=%s, conquistasBase=%s, totalFinal=%d]",
                id, conquistaDecorada.getNome(), getValor());
    }
}

