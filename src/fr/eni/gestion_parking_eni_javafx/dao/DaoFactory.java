package fr.eni.gestion_parking_eni_javafx.dao;

import fr.eni.gestion_parking_eni_javafx.dao.jdbc.DaoConducteurJdbcImpl;
import fr.eni.gestion_parking_eni_javafx.dao.jdbc.DaoVoitureJdbcImpl;

/**
 * Classe DaoFactory
 */
public class DaoFactory {

    public static DaoConducteur getDaoConducteur ()
    {
        return new DaoConducteurJdbcImpl();
    }

    public static DaoVoiture getDaoVoiture ()
    {
        return new DaoVoitureJdbcImpl();
    }
}
