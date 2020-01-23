package fr.eni.gestion_parking_eni_javafx.dao;

import fr.eni.gestion_parking_eni_javafx.dao.jdbc.DaoConducteurJdbcImpl;
import fr.eni.gestion_parking_eni_javafx.dao.jdbc.DaoVoitureJdbcImpl;

/**
 * Classe DaoFactory
 */
public class DaoFactory {

    /**
     * Renvoie la factory de DaoConducteur
     * @return DaoConducteur
     */
    public static DaoConducteur getDaoConducteur ()
    {
        return new DaoConducteurJdbcImpl();
    }

     /**
     * Renvoie la factory de DaoVoiture
     * @return DaoVoiture
     */
    public static DaoVoiture getDaoVoiture ()
    {
        return new DaoVoitureJdbcImpl();
    }
}
