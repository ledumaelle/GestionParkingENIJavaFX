package fr.eni.GestionParkingENIJavaFX.dao.jdbc;

import fr.eni.GestionParkingENIJavaFX.bo.Conducteur;
import fr.eni.GestionParkingENIJavaFX.dao.DaoConducteur;
import fr.eni.GestionParkingENIJavaFX.dao.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoConducteurJdbcImpl implements DaoConducteur {

    public static final String RQT_SELECT_ALL = "SELECT * FROM CONDUCTEURS";
    public static final String RQT_SELECT_BY_ID = "SELECT * FROM CONDUCTEURS WHERE numConducteur = ?";
    public static final String RQT_INSERT = "INSERT INTO CONDUCTEURS (nom,prenom) VALUES (?,?)";
    public static final String RQT_UPDATE = "UPDATE CONDUCTEURS SET nom = ?, prenom = ? WHERE numConducteur = ?";

    public static final String RQT_BEFORE_DELETE = "UPDATE VOITURES SET numConducteur is NULL WHERE nomConducteur = ?";
    public static final String RQT_DELETE = "DELETE FROM CONDUCTEURS WHERE numConducteur = ?";

    /**
     * Sélectionne tous les conducteurs
     *
     * @return La liste de tous les conducteurs
     * @throws DaoException Pour remonter la couche de l'exception au Main
     */
    @Override
    public List<Conducteur> selectAll() throws DaoException {
        List<Conducteur> lesConducteurs = new ArrayList<>();

        try (Connection cnx = Connexion.getConnection()) {
            PreparedStatement requete = cnx.prepareStatement(RQT_SELECT_ALL);

            ResultSet rs = requete.executeQuery();

            while (rs.next()) {
                lesConducteurs.add(itemBuilder(rs));
            }
        } catch (Exception ex) {
            throw new DaoException(ex.getMessage());
        }

        return lesConducteurs;
    }

    /**
     *
     * @param id ID du conducteur
     * @return Le conducteur correspondant à l'id passé en param
     * @throws DaoException Pour remonter la couche de l'exception au Main
     */
    @Override
    public Conducteur selectById(int id)  throws DaoException {
        Conducteur UnConducteur = null ;

        try (Connection cnx = Connexion.getConnection()) {
            PreparedStatement requete = cnx.prepareStatement(RQT_SELECT_BY_ID);
            requete.setInt(1,id);

            ResultSet rs = requete.executeQuery();

            if (rs.next()) {
                UnConducteur = itemBuilder(rs);
            }
        } catch (Exception ex) {
            throw new DaoException(ex.getMessage());
        }
        return UnConducteur;
    }

    /**
     * Insert un conducteur
     *
     * @param UnConducteur Conducteur
     * @return Un Boolean pour avoir succès ou false de la fonction
     * @throws DaoException Pour remonter la couche de l'exception au Main
     */
    @Override
    public boolean insert(Conducteur UnConducteur) throws DaoException
    {
        int nbLignes;

        try (Connection cnx = Connexion.getConnection())
        {
            PreparedStatement requete = cnx.prepareStatement(RQT_INSERT);
            requete.setString(1, UnConducteur.getNom());
            requete.setString(2, UnConducteur.getPrenom());

            nbLignes = requete.executeUpdate();

        } catch (Exception ex)
        {
            throw new DaoException(ex.getMessage());
        }
        return nbLignes > 0;
    }

    /**
     * Met à jour un conducteur
     *
     * @param UnConducteur Conducteur
     * @return Un Boolean pour avoir succès ou false de la fonction
     * @throws DaoException Pour remonter la couche de l'exception au Main
     */
    @Override
    public boolean update(Conducteur UnConducteur)  throws DaoException {

        int nbLignes;

        try (Connection cnx = Connexion.getConnection())
        {
            PreparedStatement requete = cnx.prepareStatement(RQT_UPDATE);
            requete.setString(1, UnConducteur.getNom());
            requete.setString(2, UnConducteur.getPrenom());
            requete.setInt(3, UnConducteur.getNumConducteur());

            nbLignes = requete.executeUpdate();

        } catch (Exception ex)
        {
            throw new DaoException(ex.getMessage());
        }
        return nbLignes > 0;

    }

    /**
     * Met tous les numConducteurs à NULL qd suppression d'un conducteur
     *
     * @param id ID du conducteur
     * @throws DaoException Pour remonter la couche de l'exception au Main
     */
    private void beforedelete(int id) throws DaoException
    {
        try (Connection cnx = Connexion.getConnection())
        {
            PreparedStatement requete = cnx.prepareStatement(RQT_BEFORE_DELETE);
            requete.setInt(1, id);

            requete.executeUpdate();

        } catch (Exception ex)
        {
            throw new DaoException(ex.getMessage());
        }
    }

    /**
     * Supprime un conducteur
     *
     * @param id ID du conducteur
     * @return Un Boolean pour avoir succès ou false de la fonction
     * @throws DaoException Pour remonter la couche de l'exception au Main
     */
    @Override
    public boolean delete(int id) throws DaoException
    {
        beforedelete(id);

        int nbLignes;

        try (Connection cnx = Connexion.getConnection())
        {
            PreparedStatement requete = cnx.prepareStatement(RQT_DELETE);
            requete.setInt(1, id);

            nbLignes = requete.executeUpdate();

        } catch (Exception ex)
        {
            throw new DaoException(ex.getMessage());
        }
        return nbLignes > 0 ;

    }

    /**
     * Construit l'objet Conducteur grâce au ResultSet
     *
     * @param rs réponse de la requête SQL
     * @return Conducteur
     * @throws SQLException Si erreur de SQL
     */
    private Conducteur itemBuilder(ResultSet rs) throws SQLException
    {
        Conducteur UnConducteur = new Conducteur();
        UnConducteur.setNumConducteur(rs.getInt("numConducteur"));
        UnConducteur.setNom(rs.getString("nom"));
        UnConducteur.setPrenom(rs.getString("prenom"));

        return UnConducteur;
    }
}
