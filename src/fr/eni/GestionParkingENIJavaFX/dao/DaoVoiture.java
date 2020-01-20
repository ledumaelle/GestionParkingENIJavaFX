package fr.eni.GestionParkingENIJavaFX.dao;

import fr.eni.GestionParkingENIJavaFX.bo.Conducteur;
import fr.eni.GestionParkingENIJavaFX.bo.Voiture;

import java.util.List;

public interface DaoVoiture {

    List<Voiture> selectAll();
    Voiture selectById(int id);
    boolean insert(Voiture UneVoiture) throws DaoException;
    boolean update(Voiture UneVoiture);
    boolean delete(int id);
}
