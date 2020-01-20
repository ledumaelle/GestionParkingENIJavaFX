package fr.eni.GestionParkingENIJavaFX.dao;

import fr.eni.GestionParkingENIJavaFX.bo.Conducteur;

import java.util.List;

public interface DaoConducteur {

    List<Conducteur> selectAll() throws DaoException;
    Conducteur selectById(int id) throws DaoException;
    boolean insert(Conducteur UnConducteur) throws DaoException;
    boolean update(Conducteur UnConducteur) throws DaoException;
    boolean delete(int id) throws DaoException;

}
