package desafios;

import java.util.ArrayList;
import java.util.List;

// Classe abstrata para diferentes tipos de desafios.
public abstract class Desafio extends Sujeito {
    protected final String id;
    protected final String titulo;
    protected final String descricao;
    protected final int dificuldade;
    protected PontuacaoStrategy estrategiaPontuacao;

    protected int pontuacaoMaxima;
    protected int tentativas;
    protected final List<String> tags;

    public Desafio(String id, String titulo, String descricao, int dificuldade, PontuacaoStrategy estrategia) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.dificuldade = dificuldade;
        this.estrategiaPontuacao = estrategia;
        this.pontuacaoMaxima = 0;
        this.tentativas = 0;
        this.tags = new ArrayList<>();
    }

    public String getId() { return id; }
    public String getTitulo() { return titulo; }
    public int getDificuldade() { return dificuldade; }
    public int getTentativas() { return tentativas; }
    public int getPontuacaoMaxima() { return pontuacaoMaxima; }
    public List<String> getTags() { return List.copyOf(tags); }

    public void adicionarTag(String tag) { tags.add(tag); }

    public void registrarTentativa() { tentativas++; }

    protected void atualizarPontuacaoMaxima(int pontos) {
        if (pontos > pontuacaoMaxima) {
            pontuacaoMaxima = pontos;
        }
    }

    public String getResumoDesempenho() {
        return String.format("Desafio %s: tentativas=%d, pontuacaoMaxima=%d", titulo, tentativas, pontuacaoMaxima);
    }

    public void setEstrategiaPontuacao(PontuacaoStrategy estrategia) {
        this.estrategiaPontuacao = estrategia;
    }

    public abstract int resolver(int acertos, int tempoSegundos);

    public abstract String toRelatorio();

    @Override
    public String toString() {
        return String.format("Desafio{id='%s', titulo='%s', dificuldade=%d, tags=%s}", id, titulo, dificuldade, tags);
    }
}

