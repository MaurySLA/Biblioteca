package biblioteca.model;
/**
 * @author Maury
 */
public class Livro extends Midia{
    private int anoPublicacao;
    private String editora;
    
    public Livro (){
        
    }
    public Livro (int id, String titulo, String autor, String genero, boolean disponibilidade, int anoPublicacao, String editora){
        super (id, titulo, autor, genero, disponibilidade);
        this.anoPublicacao = anoPublicacao;
        this.editora = editora;
    }
    
    public void setAnoPublicacao(int anoPublicacao){
        this.anoPublicacao = anoPublicacao;
    }
    public void setEditora(String editora){
        this.editora = editora;
    }
    
    @Override
    public String toString(){
        String texto = super.toString();
        return texto + ". " + this.editora + ". " + this.anoPublicacao + ".";
    }
    
}
