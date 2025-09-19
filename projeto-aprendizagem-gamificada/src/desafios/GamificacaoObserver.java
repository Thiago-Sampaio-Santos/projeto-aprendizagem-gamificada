package desafios;

import usuarios.Aluno;
import gamificacao.Medalha;
import gamificacao.Conquista;

// Observer que atualiza gamificação (medalhas, streaks).
public class GamificacaoObserver implements Observador {
    private final Aluno aluno;

    public GamificacaoObserver(Aluno aluno) {
        this.aluno = aluno;
    }

    @Override
    public void atualizar(String mensagem, TipoEvento tipo) {
        if (tipo == TipoEvento.DESAFIO_CONCLUIDO) {
            aluno.incrementarStreak();
            int streak = aluno.getStreakAtual();

            // A cada 5 dias de streak concedemos uma medalha (exemplo)
            if (streak % 5 == 0) {
                String id = "streak-" + aluno.getId() + "-" + streak;
                String nome = "Streak de " + streak + " dias";
                String descricao = "Bônus por manter streak de " + streak + " dias";
                String categoria = "Streak";
                int valor = 20 + streak * 5; // Podemos ajustar a regra se quisermos

                Medalha novaMedalha = new Medalha(id, nome, descricao, categoria, valor);

                // Evita adicionar medalha duplicada com mesmo id
                boolean jaPossui = aluno.getConquistas()
                        .stream()
                        .map(Conquista::getId)
                        .anyMatch(existingId -> existingId.equals(id));

                if (!jaPossui) {
                    aluno.ganharConquista(novaMedalha);
                }
            }
        }
    }
}

