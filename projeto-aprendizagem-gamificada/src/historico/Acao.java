package historico;

// Interface base do padrão Command
public interface Acao {
    void executar();
    void desfazer();
    String getDescricao();
}
