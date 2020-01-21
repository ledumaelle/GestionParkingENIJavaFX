package fr.eni.gestion_parking_eni_javafx.bll;

/**
 * Classe Exception BllException
 */
public class BllException extends Exception
{
    //Constructeurs
    public BllException() {
        super();
    }

    public BllException(String message) {
        super(message);
    }

    public BllException(String message, Throwable exception) {
        super(message, exception);
    }

    //Méthodes
    @Override
    public String getMessage() {
        StringBuffer sb = new StringBuffer("Couche BLL - ");
        sb.append(super.getMessage());

        return sb.toString() ;
    }
}
