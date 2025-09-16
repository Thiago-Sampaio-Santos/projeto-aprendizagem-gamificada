package usuarios;

// Representa um visitante da plataforma.
public class Visitante extends Usuario {
    public Visitante(String id, String nome, String email) {
        super(id, nome, email);
    }

    @Override
    public String getRole() {
        return "VISITANTE";
    }

    @Override
    public boolean temPermissao(String acao) {
        // visitante só pode visualizar conteúdo público
        return acao.equalsIgnoreCase("visualizarConteudo");
    }
}
