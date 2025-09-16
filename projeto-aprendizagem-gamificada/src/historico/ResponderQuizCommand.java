package historico;

import desafios.Quiz;
import usuarios.Usuario;
import usuarios.Aluno;

/**
 * Command para quando um usuário responde um quiz.
 * Atualmente só Aluno ganha pontos, mas pode ser estendido
 * para outros perfis no futuro.
 */
public class ResponderQuizCommand implements Acao {
    private final Usuario usuario;
    private final Quiz quiz;
    private final int acertos;
    private final int tempoSegundos;

    private int pontosObtidos;

    public ResponderQuizCommand(Usuario usuario, Quiz quiz, int acertos, int tempoSegundos) {
        this.usuario = usuario;
        this.quiz = quiz;
        this.acertos = acertos;
        this.tempoSegundos = tempoSegundos;
    }

    @Override
    public void executar() {
        pontosObtidos = quiz.resolver(acertos, tempoSegundos);

        // Apenas alunos acumulam pontos
        if (usuario instanceof Aluno aluno) {
            aluno.adicionarPontos(pontosObtidos);
        }

        HistoricoInteracoes.getInstance().registrar(
                new RegistroInteracao(usuario.getId(),
                        "Respondeu quiz '" + quiz.getTitulo() + "' e ganhou " + pontosObtidos + " pontos.")
        );
    }

    @Override
    public void desfazer() {
        if (usuario instanceof Aluno aluno) {
            aluno.adicionarPontos(-pontosObtidos);
        }

        HistoricoInteracoes.getInstance().registrar(
                new RegistroInteracao(usuario.getId(),
                        "DESFAZER: Removidos " + pontosObtidos + " pontos do quiz '" + quiz.getTitulo() + "'.")
        );
    }

    @Override
    public String getDescricao() {
        return "Responder quiz: " + quiz.getTitulo();
    }
}
