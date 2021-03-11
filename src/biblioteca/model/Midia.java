package biblioteca.model;
/**
 * @author Maury
 */
public abstract class Midia {
    protected int id;
    protected String titulo;
    protected String autor;
    protected String genero;
    protected boolean disponibilidade;
    
    public Midia (){
        
    }
    
    public Midia (int id, String titulo, String autor, String genero, boolean disponibilidade){
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.disponibilidade = disponibilidade;
    }
    
    public int getId(){
        return this.id;
    }
    public String getTitulo(){
        return this.titulo;
    }
    public String getAutor(){
        return this.autor;
    }
    public boolean getDisponibilidade(){
        return this.disponibilidade;
    }
    public void setTitulo(String titulo){
        this.titulo = titulo;
    }
    public void setAutor(String autor){
        this.autor = autor;
    }
    public void setGenero(String genero){
        this.genero = genero;
    }
    public void setDisponibilidade(boolean disponibilidade){
        this.disponibilidade = disponibilidade;
    }
    
    //Sobreescrever o método equals, pois o equals do Java só considera
    //objetos iguais quando estão na mesma posição de memória.
    @Override
    public boolean equals(Object obj){
        if (obj == null) return false;
        return this.id == ((Midia)obj).id; //Aqui o objeto foi convertido em Midia.
    }
    @Override
    public String toString(){
        return "[cód. " + this.id + " - " + (this.disponibilidade ? "Disponível] " : "Indisponível] ") + (this.autor).toUpperCase() + ". " + this.titulo;
    }
    
}
