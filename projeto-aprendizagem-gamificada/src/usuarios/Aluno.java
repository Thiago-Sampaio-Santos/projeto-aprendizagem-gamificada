package usuarios;

import gamificacao.Conquista;

import java.util.ArrayList;
import java.util.List;

// Representa um aluno da plataforma.
public class Aluno extends Usuario {
    private int pontos;
    private int nivel;
    private int streakAtual; // dias consecutivos
    private final List<Conquista> conquistas; // conquistas desbloqueadas
    private final List<Integer> historicoPontos; // evolução por desafio

    public Aluno(String id, String nome, String email) {
        super(id, nome, email);
        this.pontos = 0;
        this.nivel = 1;
        this.streakAtual = 0;
        this.conquistas = new ArrayList<>();
        this.historicoPontos = new ArrayList<>();
    }

    @Override
    public String getRole() {
        return "ALUNO";
    }

    @Override
    public boolean temPermissao(String acao) {
        // aluno pode responder desafios, ver relatórios pessoais
        return acao.equalsIgnoreCase("responderDesafio")
                || acao.equalsIgnoreCase("visualizarRelatorio");
    }

    public int getPontos() { return pontos; }
    public int getNivel() { return nivel; }
    public int getStreakAtual() { return streakAtual; }
    public List<Conquista> getConquistas() { return List.copyOf(conquistas); }
    public List<Integer> getHistoricoPontos() { return List.copyOf(historicoPontos); }

    public void adicionarPontos(int pontos) {
        if (pontos > 0) {
            this.pontos += pontos;
            this.historicoPontos.add(pontos);
            int novoNivel = 1 + (this.pontos / 1000);
            if (novoNivel > this.nivel) this.nivel = novoNivel;
        }
    }

    public void ganharConquista(Conquista conquista) {
        if (!conquistas.contains(conquista)) {
            conquistas.add(conquista);
        }
    }

    public void removerConquista(Conquista conquista) {
        conquistas.removeIf(c -> c.getId().equals(conquista.getId()));
    }
    
    public void incrementarStreak() { streakAtual++; }
    public void resetarStreak() { streakAtual = 0; }

    @Override
    public String toString() {
        return String.format(
                "%s, pontos=%d, nivel=%d, streak=%d, conquistas=%s",
                super.toString(), pontos, nivel, streakAtual,
                conquistas.stream().map(Conquista::getNome).toList()
        );
    }
}

