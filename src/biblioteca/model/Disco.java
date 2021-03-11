package biblioteca.model;
/**
 * @author Maury
 */
public class Disco extends Midia {
    
    private int duracao;
    private String formato;
    
    public Disco (){
        
    }
    
    public Disco (int id, String titulo, String autor, String genero, boolean disponibilidade, String formato, int duracao){
        super (id, titulo, autor, genero, disponibilidade);
        this.formato = formato;
        this.duracao = duracao;
    }
    
    public void setDuracao (int duracao){
        this.duracao = duracao;
    }
    public void setFormato (String formato){
        this.formato = formato;
    }
    
    @Override
    public String toString(){
        String texto = super.toString();
        return texto + ". " + this.formato + ", " + this.duracao + " minutos.";
    }
}
