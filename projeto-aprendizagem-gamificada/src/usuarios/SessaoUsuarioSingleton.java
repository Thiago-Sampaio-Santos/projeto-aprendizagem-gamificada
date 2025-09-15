package usuarios;

import java.util.Optional;
import java.util.UUID;

// Singleton de sessão do usuário.
public final class SessaoUsuarioSingleton {
    private static volatile SessaoUsuarioSingleton instancia;
    private Usuario usuarioAtual;
    private String tokenSimulado;

    private SessaoUsuarioSingleton() { }

    public static SessaoUsuarioSingleton getInstance() {
        if (instancia == null) {
            synchronized (SessaoUsuarioSingleton.class) {
                if (instancia == null) instancia = new SessaoUsuarioSingleton();
            }
        }
        return instancia;
    }

    public void login(Usuario usuario) {
        this.usuarioAtual = usuario;
        this.tokenSimulado = UUID.randomUUID().toString();
    }

    public void logout() {
        this.usuarioAtual = null;
        this.tokenSimulado = null;
    }

    public Optional<Usuario> getUsuarioAtual() {
        return Optional.ofNullable(usuarioAtual);
    }

    public boolean isAutenticado() { return usuarioAtual != null; }
    public String getTokenSimulado() { return tokenSimulado; }
}

