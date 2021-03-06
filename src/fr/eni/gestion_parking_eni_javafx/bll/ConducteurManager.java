package fr.eni.gestion_parking_eni_javafx.bll;

import fr.eni.gestion_parking_eni_javafx.bo.Conducteur;
import fr.eni.gestion_parking_eni_javafx.dao.DaoConducteur;
import fr.eni.gestion_parking_eni_javafx.dao.DaoException;
import fr.eni.gestion_parking_eni_javafx.dao.DaoFactory;
import fr.eni.gestion_parking_eni_javafx.utils.MonLogger;
import java.util.List;
import java.util.logging.Logger;

/**
 * Manager ConducteurManager
 */
public class ConducteurManager
{
    private DaoConducteur daoConducteur;
    private static ConducteurManager INSTANCE;
    public static Logger logger = MonLogger.getLogger("ConducteurManager");

    /**
     * Design Pattern Singleton
     * @return ConducteurManager
     */
    public static ConducteurManager getInstance(){
        if(INSTANCE ==null)
        {
            INSTANCE = new ConducteurManager();
        }
        return INSTANCE;
    }

    /**
     * Constructeur Privé
     */
    private ConducteurManager()
    {
        daoConducteur = DaoFactory.getDaoConducteur();
    }

    /**
     * Renvoie les conducteurs
     * @return Liste de conducteur
     * @throws BllException Si erreur remonte à la couche supp
     */
    public List<Conducteur> getLesConducteurs() throws BllException {
        List<Conducteur> lesConducteurs;
        try
        {
            lesConducteurs = daoConducteur.selectAll();
        }
        catch(DaoException ex)
        {
            logger.severe("ERREUR VoitureManager.getLesConducteurs() " + ex.getMessage());
            throw new BllException(ex.getMessage());
        }
        return lesConducteurs;
    }

    /**
     * Id du conducteur
     * @return L'objet conducteur correspond à l'id passé en param
     * @throws BllException Si erreur remonte à la couche supp
     */
    public Conducteur getConducteurById(int id) throws BllException
    {
        Conducteur unConducteur;
        try
        {
            unConducteur = daoConducteur.selectById(id);
        }
        catch(DaoException ex)
        {
            logger.severe("ERREUR VoitureManager.getConducteurById() " + ex.getMessage());
            throw new BllException(ex.getMessage());
        }
        return unConducteur;
    }

    /**
     * @param UnConducteur Conducteur à ajouter
     * @return boolean pour savoir si l'ajout s'est bien passé
     * @throws BllException Si erreur remonte à la couche supp
     */
    public boolean addConducteur(Conducteur UnConducteur) throws BllException
    {
        boolean succes = false;
        try
        {
            if(daoConducteur.insert(UnConducteur))
            {
                succes =  true;
            }
        }
        catch(DaoException ex)
        {
            logger.severe("ERREUR VoitureManager.addConducteur() " + ex.getMessage());
            throw new BllException(ex.getMessage());
        }
        return succes;
    }

    /**
     * @param UnConducteur Conducteur à mettre à jour
     * @return boolean pour savoir si l'ajout s'est bien passé
     * @throws BllException Si erreur remonte à la couche supp
     */
    public boolean updateConducteur(Conducteur UnConducteur) throws BllException
    {
        boolean succes = false;
        try
        {
            if(daoConducteur.update(UnConducteur))
            {
                succes = true;
            }
        }
        catch(DaoException ex)
        {
            logger.severe("ERREUR VoitureManager.updateConducteur() " + ex.getMessage());
            throw new BllException(ex.getMessage());
        }
        return succes;
    }

    /**
     * @param UnConducteur Conducteur à supprimer
     * @return boolean pour savoir si l'ajout s'est bien passé
     * @throws BllException Si erreur remonte à la couche supp
     */
    public boolean removeConducteur(Conducteur UnConducteur) throws BllException
    {
        boolean succes = false;
        try
        {
            if(daoConducteur.delete(UnConducteur.getNumConducteur()))
            {
                succes =  true;
            }
        }
        catch(DaoException ex)
        {
            logger.severe("ERREUR VoitureManager.removeConducteur() " + ex.getMessage());
            throw new BllException(ex.getMessage());
        }
        return succes;
    }
}
