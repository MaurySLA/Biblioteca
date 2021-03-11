package biblioteca.view;

import biblioteca.control.SegurancaV1;
import biblioteca.control.Biblioteca;
import biblioteca.control.PadraoSeguranca;
import biblioteca.control.SegurancaException;
import biblioteca.model.Disco;
import biblioteca.model.FormatoDisco;
import biblioteca.model.Genero;
import biblioteca.model.Livro;
import biblioteca.model.Midia;
import biblioteca.model.Revista;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Maury
 */
public class Main {
    
    static Biblioteca biblioteca;
    static Scanner board = new Scanner(System.in);
    static String usuarioAutenticado = null;
    static PadraoSeguranca seguranca = new SegurancaV1();
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        biblioteca = new Biblioteca(100);
        biblioteca.cadastrarLivro("O Monge e o Executivo", "James C. Hunter", Genero.AUTO_AJUDA, 1989, "Editora Sextante");
        biblioteca.cadastrarLivro("O Inimigo do Mundo", "Leonel Caldela", Genero.FANTASIA, 2015, "Jambô Editora");
        biblioteca.cadastrarLivro("Java: como programar", "Paul J. Deitel; Harvey M. Deitel", Genero.TECNICO_CIENTIFICO, 1996, "Pearson Prentice Hall");
        biblioteca.cadastrarLivro("Querido John", "Nicholas Spark", Genero.ROMANCE, 2006, "Editora Arqueiro");
        biblioteca.cadastrarLivro("Mammalogy: adaptation, diversity and ecology", "George A. Feldhamer; Lee C. Drickamer; Stephen H. Vessey; Joseph F. Merritt; Carey Krajewsk", Genero.TECNICO_CIENTIFICO, 1999, "Johns Hopkins University Press");
        biblioteca.cadastrarLivro("O Navio Arcano", "Robin Hobb", Genero.FANTASIA, 2017, "LeYa");
        biblioteca.cadastrarLivro("Drácula", "Bram Stoker", Genero.FICCAO, 2014, "Nova Fronteira");
        biblioteca.cadastrarRevista("National Geographic", "Vários Autores", Genero.TECNICO_CIENTIFICO, "Planet or plastic?", "06/2018");
        biblioteca.cadastrarRevista("Almanaque do Rio dos Sinos", "Grupo Editorial Sinos S/A", Genero.TECNICO_CIENTIFICO, "Conhecer para preservar", "2011");
        biblioteca.cadastrarRevista("Dragão Brasil #156", "Vários Autores", Genero.FICCAO, "Street of Rage", "2020");
        biblioteca.cadastrarDisco("Videoaulas de Java", "Luiz Duarte", Genero.TECNICO_CIENTIFICO, FormatoDisco.DVD, 120);
        
        //Segurança
        System.out.println("=====================");
        System.out.println("==== AUTENTICAÇÃO ===");
        System.out.println("=====================");
        System.out.print("Usuário: ");
        String usuario = board.nextLine();
        System.out.print("Senha: ");
        String senha = board.nextLine();
        try{
            usuarioAutenticado = seguranca.autenticar(usuario, senha);
        } catch(SegurancaException se){
            System.out.println("Usuário e/ou senha inválidos.");
            return;
        }
        System.out.println("Usuário autenticado com sucesso.");
        System.out.println("Aperte ENTER para acessar o Menu Principal.");
        board.nextLine();
        
        System.out.println("=====================");
        System.out.println("==== BIBLIOTECA =====");
        System.out.println("=====================");
        System.out.println("Bem-vindo à biblioteca.");
        while (true){
            System.out.println("");
            exibirMenu();
        }
    }

    private static void exibirMenu() {
        System.out.println("--- Menu Principal --");
        System.out.println("Escolha uma das opções abaixo:");
        System.out.println("(1) Pesquisar um Livro");
        System.out.println("(2) Emprestar um Livro");
        System.out.println("(3) Devolver um Livro");
        if (usuarioAutenticado.equals("admin")){
            System.out.println("(4) Cadastrar um Livro");
            System.out.println("(5) Editar um Livro");
            System.out.println("(6) Excluir um Livro");
        }
        System.out.println("(0) Sair do Sistema");
        System.out.print("Sua opção: ");
        int option = 99;
        try{
            option = board.nextInt();
        } catch (InputMismatchException imex) {
            //Não faz nada, pois a variavel option vai permanecer 0.
        } finally{
            board.nextLine();
        }
        if(seguranca.autorizar(usuarioAutenticado, option) == false){
            System.out.println("O usuário não possui permissão para acessar esta opção.");
            System.out.println("Aperte ENTER para acessar o Menu Principal.");
            board.nextLine();
            return;
        }
        switch (option){
            case 1: pesquisar(); break;
            case 2: emprestar(); break;
            case 3: devolver(); break;
            case 4: cadastrar(); break;
            case 5: editar(); break;
            case 6: excluir(); break;
            case 0: System.exit(0); break;
            case 99: System.out.println("Opção Inválida");
                     System.out.println("Aperte ENTER para acessar o Menu Principal.");
                     board.nextLine();
                     break;
            default: System.out.println("Opção Inválida");
                     System.out.println("Aperte ENTER para acessar o Menu Principal.");
                     board.nextLine();
                     break;
        }
    }
    
    private static void imprimirGeneros(){
        for (int i = 0; i < Genero.GENEROS.length; i++) {
            System.out.println("(" + Genero.GENEROS[i] + ") " + Genero.getGenero(Genero.GENEROS[i]));
        }
    }
    private static void imprimirFormatos(){
        for (int i = 0; i < FormatoDisco.FORMATOS.length; i++) {
            System.out.println("(" + FormatoDisco.FORMATOS[i] + ") " + FormatoDisco.getFormato(FormatoDisco.FORMATOS[i]));
        }
    }
    
    private static void pesquisar(){
        System.out.println("");
        System.out.println("-- Pesquisar Mídia --");
        System.out.println("Escolha uma opção de pesquisa:");
        System.out.println(" (1) Pesquisa por Código da Mídia;");
        System.out.println(" (2) Pesquisa por Título da Mídia;");
        System.out.print("Sua opção: ");
        int searchOption = 0;
        try{
            searchOption = board.nextInt();
        } catch(InputMismatchException imex){
            //Não faz nada, pois a variavel searchOption vai permanecer 0.
        } finally {
            board.nextLine();
        }
        if (searchOption == 1){
            System.out.print("Digite o código da mídia: ");
            Midia midia = null;
            try{
                int id = board.nextInt();
                midia = biblioteca.pesquisar(id);
            }catch(InputMismatchException imex){
                //Não faz nada, pois a variavel midia vai ficar nula.
            }
            finally{
                board.nextLine();
            }
            if (midia == null){
                System.out.println("Nenhuma mídia encontrada.");
                System.out.println("Aperte ENTER para acessar o Menu Principal.");
                board.nextLine();
            }
            else{
                System.out.println(midia);
                System.out.println("Aperte ENTER para acessar o Menu Principal.");
                board.nextLine();
            }
        }
        else if (searchOption == 2){
            System.out.print("Digite o título da mídia: ");
            String titulo = board.nextLine();
            Midia[] midias = biblioteca.pesquisar(titulo);
            
            if (midias == null){
                System.out.println("Nenhuma mídia encontrada.");
                System.out.println("Aperte ENTER para acessar o Menu Principal.");
                board.nextLine();
            }
            else{
                System.out.println("Mídias encontradas:");
                for (int i = 0; i < midias.length; i++) {
                    if (midias[i] != null){
                        System.out.println(midias[i]);
                    }
                }
                System.out.println("Aperte ENTER para acessar o Menu Principal.");
                board.nextLine();
            }
        }
        else{
            System.out.println("Opção de pesquisa inválida.");
            System.out.println("Aperte ENTER para acessar o Menu Principal.");
            board.nextLine();
        }
    }
    
    private static void emprestar(){
        System.out.println("");
        System.out.println("-- Emprestar Mídia --");
        System.out.print("Digite o código da mídia a emprestar: ");
        int idMidia = 0;
        try{
            idMidia = board.nextInt();
        } catch (InputMismatchException imex){
            System.out.println("Código inválido.");
        } finally {
            board.nextLine();
        }
        boolean sucess = biblioteca.emprestarMidia(idMidia);
        if (sucess){
            System.out.println("Mídia emprestada com sucesso.");
        }
        else{
            System.out.println("Não foi possível emprestar a mídia.");
        }
        System.out.println("Aperte ENTER para acessar o Menu Principal.");
        board.nextLine();
    }
    
    private static void devolver(){
        System.out.println("");
        System.out.println("-- Devolver Mídia --");
        System.out.print("Digite o código da mídia a devolver: ");
        int idMidia = 0;
        try{
            idMidia = board.nextInt();
        }catch (InputMismatchException imex){
            System.out.println("Código inválido.");
        } finally {
            board.nextLine();
        }
        boolean sucess = biblioteca.devolverMidia(idMidia);
        if (sucess){
            System.out.println("Mídia devolvida com sucesso.");
        }
        else{
            System.out.println("Não foi possível devolver a mídia.");
        }
        System.out.println("Aperte ENTER para acessar o Menu Principal.");
        board.nextLine();
    }

    private static void cadastrar(){
        int midiaOption = 0;
        System.out.println("");
        System.out.println("-- Cadastrar Mídia --");
        System.out.println("Qual o tipo de mídia que deseja cadastrar?");
        System.out.println("(1) Livro");
        System.out.println("(2) Revista");
        System.out.println("(3) Disco");
        System.out.print("Digite o código: ");
        try{
            midiaOption = board.nextInt();
        } catch (InputMismatchException imex){
            System.out.println("Opção inválida.");
        } finally {
            board.nextLine();
        }
        switch (midiaOption){
            case 0: break;
            case 1: cadastrarLivro(); break;
            case 2: cadastrarRevista(); break;
            case 3: cadastrarDisco(); break;
            default: System.out.println("Opção inválida.");
                     System.out.println("Aperte ENTER para acessar o Menu Principal.");
                     board.nextLine(); break;
        }
    }
    
    private static void cadastrarLivro(){
        int genero = 0;
        int anoPublicacao = 0;
        String editora = "";
        boolean sucess = false;
        System.out.println("");
        System.out.println("-- Cadastrar Livro --");
        System.out.print("Digite o título do livro: ");
        String titulo = board.nextLine();
        System.out.print("Digite o(s) autor(es) do livro: ");
        String autor = board.nextLine();
        System.out.println("Escolha um dos gêneros abaixo:");
        imprimirGeneros();
        System.out.print("Código do gênero: ");
        try{
            genero = board.nextInt();
            board.nextLine();
            System.out.print("Digite o ano de publicação do livro: ");
            try{
                anoPublicacao = board.nextInt();
                board.nextLine();
                System.out.print("Digite a editora do livro: ");
                editora = board.nextLine();
                sucess = biblioteca.cadastrarLivro(titulo, autor, genero, anoPublicacao, editora);
            } catch (InputMismatchException imex){
                System.out.println("Código inválido.");
                sucess = false;
            } finally {
                board.nextLine();
            }
        } catch (InputMismatchException imex){
            System.out.println("Código inválido.");
            sucess = false;
        } finally {
            board.nextLine();
        }
        if (sucess){
            System.out.println("Livro cadastrado com sucesso.");
            System.out.println("Aperte ENTER para acessar o Menu Principal.");
            board.nextLine();
        }
        else {
            System.out.println("Cadastro não realizado.");
            System.out.println("Aperte ENTER para acessar o Menu Principal.");
            board.nextLine();
        }
    }
    
    private static void cadastrarRevista(){
        int genero = 0;
        String materias = "";
        String dataLancamento = "";
        boolean sucess = false;
        System.out.println("");
        System.out.println("-- Cadastrar Revista --");
        System.out.print("Digite o título da revista: ");
        String titulo = board.nextLine();
        System.out.print("Digite o(s) autor(es) ou editor(es) da revista: ");
        String autor = board.nextLine();
        System.out.println("Escolha um dos gêneros abaixo:");
        imprimirGeneros();
        System.out.print("Código do gênero: ");
        try{
            genero = board.nextInt();
            board.nextLine();
            System.out.print("Digite a matéria de capa da revista: ");
            materias = board.nextLine();
            System.out.print("Digite a data de publicação da revista (mês e ano ou apenas mês): ");
            dataLancamento = board.nextLine();
            sucess = biblioteca.cadastrarRevista(titulo, autor, genero, materias, dataLancamento);
        } catch (InputMismatchException imex){
            System.out.println("Código inválido.");
            sucess = false;
            board.nextLine();
        } 
        if (sucess){
            System.out.println("Revista cadastrada com sucesso.");
            System.out.println("Aperte ENTER para acessar o Menu Principal.");
            board.nextLine();
        }
        else {
            System.out.println("Cadastro não realizado.");
            System.out.println("Aperte ENTER para acessar o Menu Principal.");
            board.nextLine();
        }
    }
    
    private static void cadastrarDisco(){
        int genero = 0;
        int duracao = 0;
        int formato = 0;
        boolean sucess = false;
        System.out.println("");
        System.out.println("-- Cadastrar Disco --");
        System.out.print("Digite o título do disco: ");
        String titulo = board.nextLine();
        System.out.print("Digite o(s) autor(es) do disco: ");
        String autor = board.nextLine();
        System.out.println("Escolha o formato do disco:");
        imprimirFormatos();
        System.out.print("Código do formato: ");
        try{
            genero = board.nextInt();
            board.nextLine();
            System.out.println("Qual o formato do disco?");
            imprimirFormatos();
            System.out.print("Código do formato: ");
            try{
                formato = board.nextInt();
                board.nextLine();
                System.out.print("Digite a duração do disco em minutos: ");
                duracao = board.nextInt();
                try{
                    duracao = board.nextInt();
                    board.nextLine();
                    sucess = biblioteca.cadastrarDisco(titulo, autor, genero, formato, duracao);
                } catch (InputMismatchException imex) {
                    System.out.println("Código inválido.");
                    sucess = false;
                } finally {
                    board.nextLine();
                }
            } catch (InputMismatchException imex){
                System.out.println("Código inválido.");
                sucess = false;
            } finally {
                board.nextLine();
            }
        } catch (InputMismatchException imex){
            System.out.println("Código inválido.");
            sucess = false;
        } finally {
            board.nextLine();
        }
        if (sucess){
            System.out.println("Disco cadastrado com sucesso.");
            System.out.println("Aperte ENTER para acessar o Menu Principal.");
            board.nextLine();
        }
        else {
            System.out.println("Cadastro não realizado.");
            System.out.println("Aperte ENTER para acessar o Menu Principal.");
            board.nextLine();
        }
    }
    
    private static void editar() {
        int id = 0;
        int novoGenero = 0;
        int novoAnoPublicacao = 0;
        String novaMateria = "";
        String novaDataLancamento = "";
        int novoFormato = 0;
        int novaDuracao = 0;
        boolean sucess = false;
        System.out.println("");
        System.out.println("--- Editar Mídia ---");
        System.out.print("Digite o código da mídia a ser editada: ");
        id = board.nextInt();
        board.nextLine();
        Midia midia = biblioteca.pesquisar(id);
        if (midia == null){
            System.out.println("Mídia não encontrada.");
            board.nextLine();
            return;
        }
        System.out.println("Digite o novo título da mídia.");
        System.out.println("Deixe em branco para não manter o título original.");
        System.out.print("Novo título: ");
        String novoTitulo = board.nextLine();
        System.out.println("Digite o(s) novo(s) autor(es) da mídia.");
        System.out.println("Deixe em branco para manter o(s) autor(es) original(ais).");
        System.out.print("Novo(s) autor(es): ");
        String novoAutor = board.nextLine();
        System.out.println("Digite código do novo gênero, entre as opções:");
        imprimirGeneros();
        System.out.println("Digite '0' para manter o gênero atual.");
        System.out.print("Código do novo gênero: ");
        try{
            novoGenero = board.nextInt();
            board.nextLine();
            if(midia.getClass() == Livro.class){
                System.out.println("Digite o novo ano de publicação do livro.");
                System.out.println("Digite '0' para não alterar o ano de publicação.");
                System.out.print("Novo ano de publicação: ");
                try{
                    novoAnoPublicacao = board.nextInt();
                    board.nextLine();
                    System.out.println("Digite a nova editora do livro.");
                    System.out.println("Deixe em branco para manter a editora original.");
                    System.out.print("Nova editora: ");
                    String novaEditora = board.nextLine();
                    sucess = biblioteca.editarLivro(id, novoTitulo, novoAutor, novoGenero, novoAnoPublicacao, novaEditora);
                } catch (InputMismatchException imex){
                    System.out.println("Código inválido.");
                    board.nextLine();
                }
            }
            else if (midia.getClass() == Revista.class){
                System.out.println("Digite a nova matéria de capa da revista.");
                System.out.println("Deixe em branco para não alterar a matéria de capa.");
                System.out.print("Nova matéria: ");
                novaMateria = board.nextLine();
                System.out.println("Digite a nova data de lançamento da revista.");
                System.out.println("Deixe em branco para não alterar a data de lançamento.");
                System.out.print("Nova data: ");
                novaDataLancamento = board.nextLine();
                sucess = biblioteca.editarRevista(id, novoTitulo, novoAutor, novoGenero, novaMateria, novaDataLancamento);
            }
            else if (midia.getClass() == Disco.class){
                System.out.println("Escolha o novo formato do disco.");
                System.out.println("Digite '0' para manter o formato original.");
                imprimirFormatos();
                System.out.print("Código do novo Formato: ");
                try{
                    novoFormato = board.nextInt();
                    board.nextLine();
                    System.out.println("Digite a nova duração em minutos.");
                    System.out.println("Digite '0' para manter a duração original.");
                    System.out.print("Nova duração: ");
                    try{
                        novaDuracao = board.nextInt();
                        board.nextLine();
                        sucess = biblioteca.editarDisco(id, novoTitulo, novoAutor, novoGenero, novoFormato, novaDuracao);
                    } catch (InputMismatchException imex){
                        System.out.println("Opção inválida.");
                        board.nextLine();
                        sucess = false;
                    }
                } catch (InputMismatchException imex){
                    System.out.println("Código inválido.");
                    board.nextLine();
                    sucess = false;
                }
            }
        } catch (InputMismatchException imex) {
            System.out.println("Opção inválida.");
            sucess = false;
            board.nextLine();
        }
        if (sucess){
            System.out.println("Edição realizada com sucesso.");
        }
        else{
            System.out.println("Mídia não encontrada.");
        }
        System.out.println("Aperte ENTER para acessar o Menu Principal.");
        board.nextLine();
    }

    private static void excluir() {
        int id = 0;
        System.out.println("");
        System.out.println("--- Excluir Mídia --");
        System.out.print("Digite o código da mídia a ser excluída: ");
        try{
            id = board.nextInt();
        } catch (InputMismatchException imex){
            System.out.println("Código inválido.");
        } finally {
            board.nextLine();
        }
        boolean sucess = biblioteca.excluirMidia(id);
        if (sucess){
            System.out.println("Mídia excluída com sucesso.");
        }
        else{
            System.out.println("Mídia não encontrada.");
        }
        System.out.println("Aperte ENTER para acessar o Menu Principal.");
        board.nextLine();
    }

}
