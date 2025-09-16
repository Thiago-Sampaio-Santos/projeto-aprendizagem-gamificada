package usuarios;

import java.io.*;
import java.util.*;

// Repositório simples de usuários, simulando persistência.
public class RepositorioUsuarios {
    private final Map<String, Usuario> usuarios = new HashMap<>();

    public void adicionarUsuario(Usuario usuario) {
        if (usuarios.containsKey(usuario.getId())) {
            throw new IllegalArgumentException("ID já existe: " + usuario.getId());
        }
        usuarios.put(usuario.getId(), usuario);
    }

    public Optional<Usuario> buscarPorId(String id) {
        return Optional.ofNullable(usuarios.get(id));
    }

    public List<Usuario> listarTodos() {
        return new ArrayList<>(usuarios.values());
    }

    // Persistência em CSV (id;nome;email;role)
    public void salvarEmArquivoCSV(String caminho) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminho))) {
            for (Usuario u : usuarios.values()) {
                writer.write(String.format("%s;%s;%s;%s%n",
                        u.getId(), u.getNome(), u.getEmail(), u.getRole()));
            }
        }
    }

    // Carrega apenas dados básicos
    public void carregarDeArquivoCSV(String caminho) throws IOException {
        usuarios.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(caminho))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] parts = linha.split(";");
                if (parts.length >= 4) {
                    Usuario u = PerfilFactory.criarPerfil(
                            PerfilFactory.TipoPerfil.valueOf(parts[3]),
                            parts[0], parts[1], parts[2]);
                    usuarios.put(u.getId(), u);
                }
            }
        }
    }
}

