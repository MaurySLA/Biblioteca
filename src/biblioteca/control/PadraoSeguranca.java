package biblioteca.control;
/**
 * @author Maury
 */
public interface PadraoSeguranca {
    
    String autenticar(String usuario, String senha) throws SegurancaException;
    boolean autorizar(String usuario, int option);
}
