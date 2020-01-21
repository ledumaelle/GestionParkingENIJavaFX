package fr.eni.gestion_parking_eni_javafx.dao;

import fr.eni.gestion_parking_eni_javafx.bo.Voiture;

import java.util.List;

/**
 * Interface DaoVoiture
 */
public interface DaoVoiture {

    List<Voiture> selectAll() throws DaoException;
    Voiture selectById(int id) throws DaoException;
    boolean insert(Voiture UneVoiture) throws DaoException;
    boolean update(Voiture UneVoiture) throws DaoException;
    boolean delete(int id) throws DaoException;
}
