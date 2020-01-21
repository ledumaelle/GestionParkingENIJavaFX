package fr.eni.gestion_parking_eni_javafx.bll;

import fr.eni.gestion_parking_eni_javafx.bo.Conducteur;
import fr.eni.gestion_parking_eni_javafx.dao.DaoConducteur;
import fr.eni.gestion_parking_eni_javafx.dao.DaoException;
import fr.eni.gestion_parking_eni_javafx.dao.DaoFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Manager ConducteurManager
 */
public class ConducteurManager
{
    private DaoConducteur daoConducteur;
    private List<Conducteur> lesConducteurs;

    //Design Pattern Singleton
    //Statique toute MAJ
    private static ConducteurManager INSTANCE;

    public static ConducteurManager getInstance() throws BllException
    {
        if(INSTANCE ==null)
        {
            INSTANCE = new ConducteurManager();
        }

        return INSTANCE;
    }

    private ConducteurManager() throws BllException
    {
        daoConducteur = DaoFactory.getDaoConducteur();
        try
        {
            lesConducteurs = daoConducteur.selectAll();
        }
        catch(DaoException ex)
        {
            throw new BllException(ex.getMessage());
        }
    }
    public List<Conducteur> getLesConducteurs() throws BllException {
        List<Conducteur> lesConducteurs = new ArrayList<>();
        try
        {
            lesConducteurs = daoConducteur.selectAll();
        }
        catch(DaoException ex)
        {
            throw new BllException(ex.getMessage());
        }
        return lesConducteurs;
    }

    public Conducteur getConducteurById(int id) throws DaoException,BllException
    {
        Conducteur unConducteur = new Conducteur();
        try
        {
            unConducteur = daoConducteur.selectById(id);
        }
        catch(DaoException ex)
        {
            throw new BllException(ex.getMessage());
        }
        return unConducteur;
    }

    public boolean addConducteur(Conducteur UnConducteur) throws DaoException,BllException
    {
        boolean succes = false;
        try
        {
            if(daoConducteur.insert(UnConducteur))
            {
                lesConducteurs.add(UnConducteur);
                succes =  true;
            }
        }
        catch(DaoException ex)
        {
            throw new BllException(ex.getMessage());
        }
        return succes;
    }

    public boolean updateConducteur(Conducteur UnConducteur) throws DaoException,BllException
    {
        boolean succes = false;
        try
        {
            if(daoConducteur.update(UnConducteur))
            {
                lesConducteurs.set(lesConducteurs.indexOf(UnConducteur), UnConducteur);
                succes = true;
            }
        }
        catch(DaoException ex)
        {
            throw new BllException(ex.getMessage());
        }
        return succes;
    }

    public boolean removeConducteur(Conducteur UnConducteur) throws DaoException,BllException
    {
        boolean succes = false;
        try
        {
            if(daoConducteur.delete(UnConducteur.getNumConducteur()))
            {
                lesConducteurs.remove(UnConducteur);
                succes =  true;
            }
        }
        catch(DaoException ex)
        {
            throw new BllException(ex.getMessage());
        }
        return succes;
    }
}
