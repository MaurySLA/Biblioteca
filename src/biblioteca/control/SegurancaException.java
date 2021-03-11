package biblioteca.control;
/**
 * @author Maury
 */
public class SegurancaException extends Exception {
    
    private String usuario;
    
    public SegurancaException (String usuario){
        super("Houve uma tentativa de violação de segurança pelo usuário " + usuario + ".");
        this.usuario = usuario;
    }
}
