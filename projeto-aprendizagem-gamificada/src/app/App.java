package app;

import desafios.*;
import gamificacao.Medalha;
import historico.*;
import relatorios.RelatorioFacade;
import usuarios.*;
import util.Log;

import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        // === CONFIGURAÇÃO INICIAL ===
        Log.configurarLogArquivo("logs/logs.txt");
        System.out.println("=== Plataforma de Aprendizagem Gamificada ===\n");

        // Criar aluno via Factory
        Usuario aluno = PerfilFactory.criarPerfil(
                PerfilFactory.TipoPerfil.ALUNO,
                "u1", "Thiago", "thiago@ifba.edu"
        );

        // Login com Singleton
        SessaoUsuarioSingleton sessao = SessaoUsuarioSingleton.getInstance();
        sessao.login(aluno);
        Log.info("Usuário autenticado: " + sessao.getUsuarioAtual().get());

        // === CRIAR DESAFIO ===
        Quiz quiz = new Quiz(
                "q1",
                "Quiz de Padrões de Projeto",
                "Responda questões sobre Strategy e Observer.",
                2,
                3,
                List.of("A", "B", "C"), // gabarito
                new PontuacaoPorAcerto()
        );

        // Associar Observadores ao desafio
        quiz.adicionarObservador(new NotificacaoObserver("Console"));
        quiz.adicionarObservador(new EmailObserver("suporte@ifba.edu"));
        quiz.adicionarObservador(new GamificacaoObserver((Aluno) aluno));
        quiz.adicionarObservador(new LogObserver("logs/logs-desafios.txt"));

        // === EXECUTAR AÇÃO (Command) ===
        HistoricoInteracoes historico = HistoricoInteracoes.getInstance();

        ResponderQuizCommand cmdResponder = new ResponderQuizCommand(aluno, quiz, 3, 45);
        historico.executarAcao(cmdResponder);

        // Medalha como recompensa extra
        Medalha medalha = new Medalha("m1", "Primeiro Quiz",
                "Conquistada ao concluir o primeiro quiz", "Conquista Inicial", 100);

        DesbloquearMedalhaCommand cmdMedalha = new DesbloquearMedalhaCommand(aluno, medalha);
        historico.executarAcao(cmdMedalha);

        // Mostrar status do aluno
        System.out.println("\n=== Status do Aluno ===");
        System.out.println(aluno);

        // === HISTÓRICO ===
        System.out.println("\n=== Histórico de Interações ===");
        historico.listarRegistros().forEach(System.out::println);

        // Exportar histórico como texto para relatório
        List<String> conteudoRelatorio = historico.exportarComoTexto();

        // === RELATÓRIOS ===
        RelatorioFacade facade = new RelatorioFacade();
        facade.gerarPDF("projeto-aprendizagem-gamificada/docs/relatorio-final.pdf", conteudoRelatorio);
        facade.gerarCSV("projeto-aprendizagem-gamificada/docs/relatorio-final.csv", conteudoRelatorio);
        facade.gerarJSON("projeto-aprendizagem-gamificada/docs/relatorio-final.json", conteudoRelatorio);

        System.out.println("\nRelatórios gerados com sucesso!");
        System.out.println("Ranking Global:\n" + facade.obterRankingGlobal());

        // === DEMONSTRAÇÃO DE UNDO ===
        System.out.println("\n=== Undo da última ação ===");
        historico.desfazerUltimaAcao();
        historico.listarRegistros().forEach(System.out::println);

        System.out.println("\n=== Fim da execução ===");
    }
}
