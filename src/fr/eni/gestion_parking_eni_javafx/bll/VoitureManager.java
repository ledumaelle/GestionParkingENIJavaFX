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

    public static Logger logger = MonLogger.getLogger("VoitureManager");

    private static VoitureManager instance;

    /**
     *  Design Pattern Singleton
     * @return VoitureManager
     */
    public static VoitureManager getInstance()
    {
        if(instance ==null)
        {
            instance = new VoitureManager();
        }

        return instance;
    }

    /**
     * Constructeur Privé
     */
    private VoitureManager()
    {
        daoVoiture = DaoFactory.getDaoVoiture();
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
     * @param uneVoiture uneVoiture
     * @return boolean pour savoir si l'immatriculation existe déjà en BdD
     * @throws BllException Si erreur remonte à la couche supp
     */
    public boolean findImmatriculation(Voiture uneVoiture) throws BllException
    {
        List<Voiture> lesVoitures;
        boolean succes = false;
        try
        {
            lesVoitures = daoVoiture.selectAll();
            for(Voiture voiture : lesVoitures)
            {
                if(voiture.getImmatriculation().trim().equals(uneVoiture.getImmatriculation()) && uneVoiture.getNumVoiture() != voiture.getNumVoiture())
                {
                    succes = true;
                    break;
                }
            }
        }
        catch(DaoException ex)
        {
            logger.severe("ERREUR VoitureManager.findImmatriculation() " + ex.getMessage());
            throw new BllException(ex.getMessage());
        }

        return succes;
    }

    /**
     * @param uneVoiture Voiture à ajouter
     * @return boolean pour savoir si l'ajout s'est bien passé
     * @throws BllException Si erreur remonte à la couche supp
     */
    public boolean addVoiture(Voiture uneVoiture) throws BllException
    {
        boolean succes = false;
        try
        {
            if(!findImmatriculation(uneVoiture))
            {
                succes = daoVoiture.insert(uneVoiture);
            }
        }
        catch(DaoException ex)
        {
            logger.severe("ERREUR VoitureManager.addVoiture() " + ex.getMessage());
            throw new BllException(ex.getMessage());
        }

        return succes;
    }

    /**
     * @param uneVoiture Voiture à mettre à jour
     * @return boolean pour savoir si l'ajout s'est bien passé
     * @throws BllException Si erreur remonte à la couche supp
     */
    public boolean updateVoiture(Voiture uneVoiture) throws BllException
    {
        boolean succes = false;
        try
        {
            if(!findImmatriculation(uneVoiture))
            {
                succes = daoVoiture.update(uneVoiture);
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
     * @param uneVoiture Voiture à supprimer
     * @return boolean pour savoir si l'ajout s'est bien passé
     * @throws BllException Si erreur remonte à la couche supp
     */
    public boolean removeVoiture(Voiture uneVoiture) throws BllException
    {
        boolean succes;
        try
        {
            succes = daoVoiture.delete(uneVoiture.getNumVoiture());
        }
        catch(DaoException ex)
        {
            logger.severe("ERREUR VoitureManager.removeVoiture() " + ex.getMessage());
            throw new BllException(ex.getMessage());
        }

        return succes;
    }



}
