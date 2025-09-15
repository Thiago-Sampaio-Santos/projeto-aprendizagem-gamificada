package usuarios;

// Factory para criação de perfis de usuário.
public final class PerfilFactory {
    private PerfilFactory() { }

    public enum TipoPerfil {
        ALUNO, PROFESSOR, VISITANTE
    }

    public static Usuario criarPerfil(TipoPerfil tipo, String id, String nome, String email, String extraInfo) {
        switch (tipo) {
            case ALUNO:
                return new Aluno(id, nome, email);
            case PROFESSOR:
                return new Professor(id, nome, email, (extraInfo != null ? extraInfo : "Geral"));
            case VISITANTE:
            default:
                return new Visitante(id, nome, email);
        }
    }

    public static Usuario criarPerfil(TipoPerfil tipo, String id, String nome, String email) {
        return criarPerfil(tipo, id, nome, email, null);
    }
}

