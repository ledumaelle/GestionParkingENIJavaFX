package fr.eni.GestionParkingENIJavaFX.dao;

public class DaoException extends Exception
{
    //Constructeurs
    public DaoException() {
        super();
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable exception) {
        super(message, exception);
    }

    //Méthodes
    @Override
    public String getMessage() {
        StringBuffer sb = new StringBuffer("Couche DAO - ");
        sb.append(super.getMessage());

        return sb.toString() ;
    }
}
