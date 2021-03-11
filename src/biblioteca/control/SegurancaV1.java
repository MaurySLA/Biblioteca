package biblioteca.control;
/**
 * @author Maury
 */
public class SegurancaV1 implements PadraoSeguranca{
    
    //Essa classe implementa o padrão de segurança da classe PadraoSeguranca
    //Sobrescreve os métodos descritos no padrão de segurança.
    
    // admin/admin => administrador
    // user/user => usuário
    
    @Override
    public String autenticar(String usuario, String senha) throws SegurancaException{
        if (usuario.equals("admin") && senha.equals("admin")){
            return "admin";
        }
        else if (usuario.equals("user") && senha.equals("user")){
            return "user";
        }
        else {
            throw new SegurancaException(usuario);
        }
    }
    
    @Override
    public boolean autorizar(String usuario, int option){
        switch(option){
            case 1:
            case 2:
            case 3:
            case 99:
            case 0: return true;
            case 4:
            case 5:
            case 6: if (usuario.equals("admin")) return true;
            default: return false;
        }
    }
}
