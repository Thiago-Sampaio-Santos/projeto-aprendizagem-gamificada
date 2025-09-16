package usuarios;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.regex.Pattern;


 //Classe base para todos os tipos de usuário.
public abstract class Usuario {
    public enum Status {
        ATIVO, INATIVO, BLOQUEADO
    }

    private final String id;
    private String nome;
    private String email;
    private final LocalDateTime dataCriacao;
    private Status status;
    private String fotoPerfil; // caminho ou URL

    private static final Pattern EMAIL_REGEX =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    public Usuario(String id, String nome, String email) {
        this.id = Objects.requireNonNull(id, "id não pode ser nulo");
        setNome(nome);
        setEmail(email);
        this.dataCriacao = LocalDateTime.now();
        this.status = Status.ATIVO;
    }

    public String getId() { return id; }
    public String getNome() { return nome; }
    public void setNome(String nome) {
        if (nome == null || nome.trim().length() < 3) {
            throw new IllegalArgumentException("Nome deve ter pelo menos 3 caracteres");
        }
        this.nome = nome;
    }

    public String getEmail() { return email; }
    public void setEmail(String email) {
        if (email == null || !EMAIL_REGEX.matcher(email).matches()) {
            throw new IllegalArgumentException("Email inválido");
        }
        this.email = email;
    }

    public LocalDateTime getDataCriacao() { return dataCriacao; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public String getFotoPerfil() { return fotoPerfil; }
    public void setFotoPerfil(String fotoPerfil) { this.fotoPerfil = fotoPerfil; }

    public boolean isAtivo() { return this.status == Status.ATIVO; }

    //Polimórfico: cada perfil define suas permissões.
    public abstract boolean temPermissao(String acao);

    public abstract String getRole();

    @Override
    public String toString() {
        return String.format("%s{id='%s', nome='%s', email='%s', status='%s'}",
                getRole(), id, nome, email, status);
    }
}

