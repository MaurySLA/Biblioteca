package biblioteca.model;
/**
 * @author Maury
 */
public abstract class FormatoDisco {
    public final static int CD = 1;
    public final static int DVD = 2;
    public final static int BLUERAY = 3;
    public final static int OUTRO = 4;
    public final static int [] FORMATOS = {CD, DVD, BLUERAY, OUTRO};
    
    public static String getFormato(int formato){
        switch(formato){
            case CD: return "CD";
            case DVD: return "DVD";
            case BLUERAY: return "Blueray";
            case OUTRO: return "Outro";
            default: return null;
        }
    }
    
    public static String[] getFormatos(){
        String[] formatos = new String[FORMATOS.length];
        for (int i = 0; i < FORMATOS.length; i++) {
            formatos[i] = getFormato(FORMATOS[i]);
        }
        return formatos;
    }
}
