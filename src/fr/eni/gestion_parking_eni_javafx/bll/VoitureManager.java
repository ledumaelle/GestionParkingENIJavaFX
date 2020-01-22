package fr.eni.gestion_parking_eni_javafx.bll;

import fr.eni.gestion_parking_eni_javafx.bo.Voiture;
import fr.eni.gestion_parking_eni_javafx.dao.DaoVoiture;
import fr.eni.gestion_parking_eni_javafx.dao.DaoException;
import fr.eni.gestion_parking_eni_javafx.dao.DaoFactory;
import fr.eni.gestion_parking_eni_javafx.utils.MonLogger;

import java.util.List;
import java.util.logging.Logger;

/**
 * Manager VoitureManager
 */
public class VoitureManager
{
    private DaoVoiture daoVoiture;
    private List<Voiture> lesVoitures;

    public static Logger logger = MonLogger.getLogger("VoitureManager");

    private static VoitureManager instance;

    /**
     *  Design Pattern Singleton
     * @return VoitureManager
     * @throws BllException Si erreur remonte à la couche supp
     */
    public static VoitureManager getInstance() throws BllException
    {
        if(instance ==null)
        {
            instance = new VoitureManager();
        }

        return instance;
    }

    /**
     * Constructeur Privé
     * @throws BllException Si erreur remonte à la couche supp
     */
    private VoitureManager() throws BllException
    {
        daoVoiture = DaoFactory.getDaoVoiture();
        try
        {
            lesVoitures = daoVoiture.selectAll();
        }
        catch(DaoException ex)
        {
            logger.severe("ERREUR VoitureManager() " + ex.getMessage());
            throw new BllException(ex.getMessage());
        }
    }

    /**
     * Renvoie les voitures
     * @return Liste de voiture
     * @throws BllException Si erreur remonte à la couche supp
     */
    public List<Voiture> getLesVoitures() throws BllException
    {
        List<Voiture> lesVoitures;
        try
        {
            lesVoitures = daoVoiture.selectAll();
        }
        catch(DaoException ex)
        {
            logger.severe("ERREUR VoitureManager.getLesVoitures() " + ex.getMessage());
            throw new BllException(ex.getMessage());
        }

        return lesVoitures;
    }

    /**
     *
     * @param id Id de la voiture
     * @return L'objet voiture correspond à l'id passé en param
     * @throws BllException Si erreur remonte à la couche supp
     */
    public Voiture getVoitureById(int id) throws BllException
    {
        Voiture uneVoiture;

        try
        {
            uneVoiture = daoVoiture.selectById(id);
        }
        catch(DaoException ex)
        {
            logger.severe("ERREUR VoitureManager.getVoitureById() " + ex.getMessage());
            throw new BllException(ex.getMessage());
        }

        return uneVoiture;
    }

    /**
     * @param UneVoiture Voiture à ajouter
     * @return boolean pour savoir si l'ajout s'est bien passé
     * @throws BllException Si erreur remonte à la couche supp
     */
    public boolean addVoiture(Voiture UneVoiture) throws BllException
    {
        boolean succes;
        try
        {
            succes = daoVoiture.insert(UneVoiture);
        }
        catch(DaoException ex)
        {
            logger.severe("ERREUR VoitureManager.addVoiture() " + ex.getMessage());
            throw new BllException(ex.getMessage());
        }

        return succes;
    }

    /**
     * @param UnVoiture Voiture à mettre à jour
     * @return boolean pour savoir si l'ajout s'est bien passé
     * @throws BllException Si erreur remonte à la couche supp
     */
    public boolean updateVoiture(Voiture UnVoiture) throws BllException
    {
        boolean succes;
        try
        {
            succes = daoVoiture.update(UnVoiture);
            if(succes)
            {
                lesVoitures.set(lesVoitures.indexOf(UnVoiture), UnVoiture);
            }
        }
        catch(DaoException ex)
        {
            logger.severe("ERREUR VoitureManager.updateVoiture() " + ex.getMessage());
            throw new BllException(ex.getMessage());
        }

        return succes;
    }

    /**
     * @param UnVoiture Voiture à supprimer
     * @return boolean pour savoir si l'ajout s'est bien passé
     * @throws BllException Si erreur remonte à la couche supp
     */
    public boolean removeVoiture(Voiture UnVoiture) throws BllException
    {
        boolean succes;
        try
        {
            succes = daoVoiture.delete(UnVoiture.getNumVoiture());

            if(succes)
            {
                lesVoitures.remove(UnVoiture);
            }
        }
        catch(DaoException ex)
        {
            logger.severe("ERREUR VoitureManager.removeVoiture() " + ex.getMessage());
            throw new BllException(ex.getMessage());
        }

        return succes;
    }



}
