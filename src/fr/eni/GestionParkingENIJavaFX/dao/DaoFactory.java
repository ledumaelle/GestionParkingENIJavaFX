package fr.eni.GestionParkingENIJavaFX.dao;

import fr.eni.GestionParkingENIJavaFX.dao.jdbc.DaoConducteurJdbcImpl;

public class DaoFactory {

    public static DaoConducteur getDaoConducteur ()
    {
        return new DaoConducteurJdbcImpl();
    }

    /*public static DaoVoiture getDaoVoiture ()
    {
        return new DaoConducteurJdbcImpl();
    }*/
}
