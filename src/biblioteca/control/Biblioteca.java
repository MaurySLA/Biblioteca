package biblioteca.control;

import biblioteca.model.Disco;
import biblioteca.model.FormatoDisco;
import biblioteca.model.Genero;
import biblioteca.model.Livro;
import biblioteca.model.Midia;
import biblioteca.model.Revista;

/**
 * @author Maury
 */
public class Biblioteca {
    Midia[] midias;
    int nextId = 1;
    
    public Biblioteca (int capacidade){
        midias = new Midia[capacidade];
    }
    
    private String normalizarTexto(String texto){
        return texto.toLowerCase().replaceAll("[áàãâä]", "a")
                                  .replaceAll("[éèêë]", "e")
                                  .replaceAll("[íìîï]", "i")
                                  .replaceAll("[óòõôö]", "o")
                                  .replaceAll("[úùûü]", "u")
                                  .replace("ç", "c")
                                  .replaceAll("[^\\w]", " ");
    }
    
    public Midia[] pesquisar(String titulo){
        if (titulo == null || titulo.equals("")){
            return midias;
        }
        Midia[] aux = new Midia[midias.length];
        titulo =  normalizarTexto(titulo);
        for (int i = 0; i < midias.length; i++) {
            if (midias[i] != null && normalizarTexto(midias[i].getTitulo()).contains(titulo)){
                aux[i] = midias[i];
            }
        }
        return aux;
    }
    
    public Midia pesquisar(int id){
        for (int i = 0; i < midias.length; i++) {
            if (midias[i] != null && midias[i].getId() == id){
                return midias[i];
            }
        }
        return null;
    }
    
    private boolean cadastrar (Midia midia){
        for (int i = 0; i < midias.length; i++) {
            if (midias[i] == null){
                midias[i] = midia;
                nextId++;
                return true;
            }
        }
        return false;
    }
    
    public boolean cadastrarLivro(String titulo, String autor, int genero, int anoPublicacao, String editora){
        Livro livro = new Livro(nextId, titulo, autor, Genero.getGenero(genero), true, anoPublicacao, editora);
        return cadastrar(livro);
    }
    
    public boolean cadastrarRevista(String titulo, String autor, int genero, String materias, String dataLancamento){
        Revista revista = new Revista(nextId, titulo, autor, Genero.getGenero(genero), true, materias, dataLancamento);
        return cadastrar(revista);
    }
    
    public boolean cadastrarDisco(String titulo, String autor, int genero, int formato, int duracao){
        Disco disco = new Disco(nextId, titulo, autor, Genero.getGenero(genero), true, FormatoDisco.getFormato(formato), duracao);
        return cadastrar(disco);
    }
    
    public boolean emprestarMidia(int id){
        Midia midia = pesquisar(id);
        if (midia == null){
            return false;
        }
        if (midia.getDisponibilidade() == false){
            return false;
        }
        else {
            midia.setDisponibilidade(false);
            return true;
        }
    }
    
    public boolean devolverMidia(int id){
        Midia midia = pesquisar(id);
        if (midia == null){
            return false;
        }
        if (midia.getDisponibilidade() == true){
            return false;
        }
        else {
            midia.setDisponibilidade(true);
            return true;
        }
    }
    
    private void editar (Midia midia, String novoTitulo, String novoAutor, int novoGenero){
        if (novoTitulo != null && !novoTitulo.equals("")){
            midia.setTitulo(novoTitulo);
        }
        if (novoAutor != null && !novoAutor.equals("")){
            midia.setAutor(novoAutor);
        }
        if (novoGenero != 0){
            midia.setGenero(Genero.getGenero(novoGenero));
        }
    }
    
    public boolean editarLivro(int id, String novoTitulo, String novoAutor, int novoGenero, int novoAnoPublicacao, String novaEditora){
        Livro livro = (Livro) pesquisar(id);
        if (livro == null){
            return false;
        }
        editar(livro, novoTitulo, novoAutor, novoGenero);
        if (novoAnoPublicacao != 0){
            livro.setAnoPublicacao(novoAnoPublicacao);
        }
        if (novaEditora != null && !novaEditora.equals("")){
            livro.setEditora(novaEditora);
        }
        return true;
    }
    
    public boolean editarRevista(int id, String novoTitulo, String novoAutor, int novoGenero, String novasMaterias, String novaDataLancamento){
        Revista revista = (Revista) pesquisar(id);
        if (revista == null){
            return false;
        }
        editar(revista, novoTitulo, novoAutor, novoGenero);
        if (novasMaterias != null && !novasMaterias.equals("")){
            revista.setMaterias(novasMaterias);
        }
        if (novaDataLancamento != null && !novaDataLancamento.equals("")){
            revista.setDataLancamento(novaDataLancamento);
        }
        return true;
    }
    
    public boolean editarDisco(int id, String novoTitulo, String novoAutor, int novoGenero, int novoFormato, int novaDuracao){
        Disco disco = (Disco) pesquisar(id);
        if (disco == null){
            return false;
        }
        editar(disco, novoTitulo, novoAutor, novoGenero);
        if (novoFormato != 0){
            disco.setFormato(FormatoDisco.getFormato(novoFormato));
        }
        if (novaDuracao != 0){
            disco.setDuracao(novaDuracao);
        }
        return true;
    }
    
    public boolean excluirMidia (int id){
        for (int i = 0; i < midias.length; i++) {
            if (midias[i] != null && midias[i].getId() == id){
                midias[i] = null;
                return true;
            }
        }
        return false;
    }
}
