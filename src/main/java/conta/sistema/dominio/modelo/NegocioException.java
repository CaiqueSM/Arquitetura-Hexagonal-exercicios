package conta.sistema.dominio.modelo;

public class NegocioException extends RuntimeException {
    public NegocioException(String mesagem){
        super(mesagem);
    }
}
