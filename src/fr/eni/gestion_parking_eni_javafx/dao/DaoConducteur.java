package fr.eni.gestion_parking_eni_javafx.dao;

import fr.eni.gestion_parking_eni_javafx.bo.Conducteur;

import java.util.List;

/**
 * Interface DaoConducteur
 */
public interface DaoConducteur {

    List<Conducteur> selectAll() throws DaoException;
    Conducteur selectById(int id) throws DaoException;
    boolean insert(Conducteur UnConducteur) throws DaoException;
    boolean update(Conducteur UnConducteur) throws DaoException;
    boolean delete(int id) throws DaoException;

}
