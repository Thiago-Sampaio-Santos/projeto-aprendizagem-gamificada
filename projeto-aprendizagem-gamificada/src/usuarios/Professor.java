package usuarios;

import java.util.ArrayList;
import java.util.List;

// Representa um professor da plataforma.
public class Professor extends Usuario {
    private String disciplina;
    private int nivelInstrutor;
    private final List<String> listaTurmas;

    public Professor(String id, String nome, String email, String disciplina) {
        super(id, nome, email);
        this.disciplina = disciplina;
        this.nivelInstrutor = 1;
        this.listaTurmas = new ArrayList<>();
    }

    @Override
    public String getRole() {
        return "PROFESSOR";
    }

    @Override
    public boolean temPermissao(String acao) {
        // professor pode criar desafios, ver relatórios de turmas
        return acao.equalsIgnoreCase("criarDesafio")
                || acao.equalsIgnoreCase("visualizarRelatorioTurma");
    }

    public String getDisciplina() { return disciplina; }
    public void setDisciplina(String disciplina) { this.disciplina = disciplina; }

    public int getNivelInstrutor() { return nivelInstrutor; }
    public void promoverInstrutor() { this.nivelInstrutor++; }

    public void adicionarTurma(String turma) { listaTurmas.add(turma); }
    public List<String> getListaTurmas() { return List.copyOf(listaTurmas); }

    @Override
    public String toString() {
        return String.format("%s, disciplina='%s', nivelInstrutor=%d, turmas=%s",
                super.toString(), disciplina, nivelInstrutor, listaTurmas);
    }
}

