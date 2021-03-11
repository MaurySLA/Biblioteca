package biblioteca.model;
/**
 * @author Maury
 */
public abstract class Genero {
    public final static int ROMANCE = 1;
    public final static int FICCAO = 2;
    public final static int FANTASIA = 3;
    public final static int AUTO_AJUDA = 4;
    public final static int TECNICO_CIENTIFICO = 5;
    public final static int RELIGIAO = 6;
    public final static int [] GENEROS = {ROMANCE, FICCAO, FANTASIA, AUTO_AJUDA, TECNICO_CIENTIFICO, RELIGIAO};
    
    public static String getGenero(int genero){
        switch(genero){
            case ROMANCE: return "Romance";
            case FICCAO: return "Ficção";
            case FANTASIA: return "Fantasia";
            case AUTO_AJUDA: return "Auto-Ajuda";
            case TECNICO_CIENTIFICO: return "Técnico-Científico";
            case RELIGIAO: return "Religião";
            default: return null;
        }
    }
    
    public static String[] getGeneros(){
        String[] generos = new String[GENEROS.length];
        for (int i = 0; i < GENEROS.length; i++) {
            generos[i] = getGenero(GENEROS[i]);
        }
        return generos;
    }
    
}
