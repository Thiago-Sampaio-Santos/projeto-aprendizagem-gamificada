package desafios;

// Observer que simula envio de email.
public class EmailObserver implements Observador {
    private final String emailDestino;

    public EmailObserver(String emailDestino) {
        this.emailDestino = emailDestino;
    }

    @Override
    public void atualizar(String mensagem, TipoEvento tipo) {
        System.out.println("Enviando email para " + emailDestino + ": [" + tipo + "] " + mensagem);
    }
}

