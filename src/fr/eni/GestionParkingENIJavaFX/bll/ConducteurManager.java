package fr.eni.GestionParkingENIJavaFX.bll;

import fr.eni.GestionParkingENIJavaFX.bo.Conducteur;
import fr.eni.GestionParkingENIJavaFX.dao.DaoConducteur;
import fr.eni.GestionParkingENIJavaFX.dao.DaoException;
import fr.eni.GestionParkingENIJavaFX.dao.DaoFactory;

import java.util.List;

public class ConducteurManager
{
    private DaoConducteur daoConducteur;
    private List<Conducteur> lesConducteurs;

    //Design Pattern Singleton
    private static ConducteurManager instance;

    public static ConducteurManager getInstance() throws BllException
    {
        if(instance ==null)
        {
            instance = new ConducteurManager();
        }

        return instance;
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

    public Conducteur getConducteurById(int id) throws DaoException,BllException
    {
        return daoConducteur.selectById(id);
    }

    public boolean addConducteur(Conducteur UnConducteur) throws DaoException,BllException
    {
        if(daoConducteur.insert(UnConducteur))
        {
            lesConducteurs.add(UnConducteur);
            return true;
        }
        return false;
    }

    public boolean updateConducteur(Conducteur UnConducteur) throws DaoException,BllException
    {
        if(daoConducteur.update(UnConducteur))
        {
            lesConducteurs.set(lesConducteurs.indexOf(UnConducteur), UnConducteur);
            return true;
        }
        return false;
    }

    public boolean removeConducteur(Conducteur UnConducteur) throws DaoException,BllException
    {
        if(daoConducteur.delete(UnConducteur.getNumConducteur()))
        {
            lesConducteurs.remove(UnConducteur);
            return true;
        }
        return false;
    }



}
