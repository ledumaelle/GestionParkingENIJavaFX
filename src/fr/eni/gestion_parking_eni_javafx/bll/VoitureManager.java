package fr.eni.gestion_parking_eni_javafx.bll;

import fr.eni.gestion_parking_eni_javafx.bo.Voiture;
import fr.eni.gestion_parking_eni_javafx.dao.DaoVoiture;
import fr.eni.gestion_parking_eni_javafx.dao.DaoException;
import fr.eni.gestion_parking_eni_javafx.dao.DaoFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Manager VoitureManager
 */
public class VoitureManager
{
    private DaoVoiture daoVoiture;
    private List<Voiture> lesVoitures;

    //Design Pattern Singleton
    private static VoitureManager instance;

    public static VoitureManager getInstance() throws BllException
    {
        if(instance ==null)
        {
            instance = new VoitureManager();
        }

        return instance;
    }

    private VoitureManager() throws BllException
    {
        daoVoiture = DaoFactory.getDaoVoiture();
        try
        {
            lesVoitures = daoVoiture.selectAll();
        }
        catch(DaoException ex)
        {
            throw new BllException(ex.getMessage());
        }
    }
    public List<Voiture> getLesVoitures() throws DaoException,BllException
    {
        List<Voiture> lesVoitures = new ArrayList<>();
        try
        {
            lesVoitures = daoVoiture.selectAll();
        }
        catch(DaoException ex)
        {
            throw new BllException(ex.getMessage());
        }

        return lesVoitures;
    }

    public Voiture getVoitureById(int id) throws DaoException,BllException
    {
        return daoVoiture.selectById(id);
    }

    public boolean addVoiture(Voiture UneVoiture) throws DaoException,BllException
    {
        if(daoVoiture.insert(UneVoiture))
        {
            lesVoitures.add(UneVoiture);
            return true;
        }
        return false;
    }

    public boolean updateVoiture(Voiture UnVoiture) throws DaoException,BllException
    {
        if(daoVoiture.update(UnVoiture))
        {
            lesVoitures.set(lesVoitures.indexOf(UnVoiture), UnVoiture);
            return true;
        }
        return false;
    }

    public boolean removeVoiture(Voiture UnVoiture) throws DaoException,BllException
    {
        if(daoVoiture.delete(UnVoiture.getNumVoiture()))
        {
            lesVoitures.remove(UnVoiture);
            return true;
        }
        return false;
    }



}
