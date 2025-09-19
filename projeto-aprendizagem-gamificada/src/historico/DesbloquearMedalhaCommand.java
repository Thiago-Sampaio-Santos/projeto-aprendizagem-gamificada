package historico;

import gamificacao.Medalha;
import usuarios.Usuario;
import usuarios.Aluno;

/**
 * Command para quando um usuário desbloqueia uma medalha.
 * Atualmente apenas alunos recebem conquistas.
 */
public class DesbloquearMedalhaCommand implements Acao {
    private final Usuario usuario;
    private final Medalha medalha;

    public DesbloquearMedalhaCommand(Usuario usuario, Medalha medalha) {
        this.usuario = usuario;
        this.medalha = medalha;
    }

    @Override
    public void executar() {
        if (usuario instanceof Aluno aluno) {
            // Evita duplicação
            boolean jaPossui = aluno.getConquistas()
                    .stream()
                    .anyMatch(c -> c.getId().equals(medalha.getId()));

            if (!jaPossui) {
                aluno.ganharConquista(medalha);
            }
        }

        HistoricoInteracoes.getInstance().registrar(
                new RegistroInteracao(usuario.getId(),
                        "Desbloqueou medalha '" + medalha.getNome() + "'.")
        );
    }

    @Override
    public void desfazer() {
        if (usuario instanceof Aluno aluno) {
            aluno.removerConquista(medalha);
        }

        HistoricoInteracoes.getInstance().registrar(
                new RegistroInteracao(usuario.getId(),
                        "DESFAZER: Medalha '" + medalha.getNome() + "' removida.")
        );
    }

    @Override
    public String getDescricao() {
        return "Desbloquear medalha: " + medalha.getNome();
    }
}
