package biblioteca.model;
/**
 * @author Maury
 */
public class Revista extends Midia {
    private String materias;
    private String dataLancamento;
    
    public Revista(){
        
    }
    
    public Revista (int id, String titulo, String autor, String genero, boolean disponibilidade, String materias, String dataLancamento){
        super (id, titulo, autor, genero, disponibilidade);
        this.materias = materias;
        this.dataLancamento = dataLancamento;
    }
    
    public void setMaterias (String materias){
        this.materias = materias;
    }
    public void setDataLancamento (String dataLancamento){
        this.dataLancamento = dataLancamento;
    }
    
    @Override
    public String toString(){
        String texto = super.toString();
        return texto + " - " + this.materias + ". " + this.dataLancamento + ".";
    }
}
