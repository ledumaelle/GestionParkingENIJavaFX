package fr.eni.gestion_parking_eni_javafx.dao.jdbc;

import fr.eni.gestion_parking_eni_javafx.bo.Conducteur;
import fr.eni.gestion_parking_eni_javafx.bo.Voiture;
import fr.eni.gestion_parking_eni_javafx.dao.DaoException;
import fr.eni.gestion_parking_eni_javafx.dao.DaoVoiture;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe DaoVoitureJdbcImpl
 */
public class DaoVoitureJdbcImpl implements DaoVoiture {

    public static final String RQT_SELECT_ALL = "SELECT numVoiture,designation,immatriculation,C.numConducteur as numConducteur,nom,prenom FROM VOITURES as V LEFT JOIN CONDUCTEURS as C ON C.numConducteur = V.numConducteur";
    public static final String RQT_SELECT_BY_ID = "SELECT numVoiture,designation,immatriculation,C.numConducteur as numConducteur,nom,prenom FROM VOITURES as V LEFT JOIN CONDUCTEURS as C ON C.numConducteur = V.numConducteur WHERE numVoiture = ?";
    public static final String RQT_INSERT = "INSERT INTO VOITURES (designation,immatriculation,numConducteur) VALUES (?,?,?)";
    public static final String RQT_UPDATE = "UPDATE VOITURES SET designation = ?, immatriculation = ?, numConducteur= ? WHERE numVoiture = ?";
    public static final String RQT_DELETE = "DELETE FROM VOITURES WHERE numVoiture = ?";

    /**
     * Sélectionne toutes les voitures
     *
     * @return La liste de toutes les voitures
     * @throws DaoException Pour remonter la couche de l'exception au Main
     */
    @Override
    public List<Voiture> selectAll() throws  DaoException {
        List<Voiture> lesVoitures = new ArrayList<>();

        try (Connection cnx = Connexion.getConnection()) {
            PreparedStatement requete = cnx.prepareStatement(RQT_SELECT_ALL);

            ResultSet rs = requete.executeQuery();

            while (rs.next()) {
                lesVoitures.add(itemBuilder(rs));
            }
        } catch (Exception ex) {
            throw new DaoException(ex.getMessage());
        }

        return lesVoitures;
    }

    /**
     *
     * @param id ID de la voiture
     * @return La voiture correspondante à l'id passé en param
     * @throws DaoException Pour remonter la couche de l'exception au Main
     */
    @Override
    public Voiture selectById(int id) throws  DaoException {
        Voiture UneVoiture = null ;

        try (Connection cnx = Connexion.getConnection()) {
            PreparedStatement requete = cnx.prepareStatement(RQT_SELECT_BY_ID);
            requete.setInt(1,id);

            ResultSet rs = requete.executeQuery();

            if (rs.next()) {
                UneVoiture = itemBuilder(rs);
            }
        } catch (Exception ex) {
            throw new DaoException(ex.getMessage());
        }
        return UneVoiture;
    }

    /**
     * Insert une voiture
     *
     * @param UneVoiture Voiture
     * @return Un Boolean pour avoir succès ou false de la fonction
     * @throws DaoException Pour remonter la couche de l'exception au Main
     */
    @Override
    public boolean insert(Voiture UneVoiture) throws DaoException {
        int nbLignes;

        try (Connection cnx = Connexion.getConnection())
        {
            PreparedStatement requete = cnx.prepareStatement(RQT_INSERT);
            requete.setString(1, UneVoiture.getDesignation());
            requete.setString(2, UneVoiture.getImmatriculation());
            requete.setInt(3, UneVoiture.getUnConducteur().getNumConducteur());

            nbLignes = requete.executeUpdate();

        } catch (Exception ex)
        {
            throw new DaoException(ex.getMessage());
        }
        return nbLignes > 0;
    }

    /**
     * Met à jour une voiture
     *
     * @param UneVoiture Voiture
     * @return Un Boolean pour avoir succès ou false de la fonction
     * @throws DaoException Pour remonter la couche de l'exception au Main
     */
    @Override
    public boolean update(Voiture UneVoiture) throws  DaoException {
        int nbLignes;

        try (Connection cnx = Connexion.getConnection())
        {
            PreparedStatement requete = cnx.prepareStatement(RQT_UPDATE);
            requete.setString(1, UneVoiture.getDesignation());
            requete.setString(2, UneVoiture.getImmatriculation());
            requete.setInt(3, UneVoiture.getUnConducteur().getNumConducteur());
            requete.setInt(4, UneVoiture.getNumVoiture());

            nbLignes = requete.executeUpdate();

        } catch (Exception ex)
        {
            throw new DaoException(ex.getMessage());
        }
        return nbLignes > 0;
    }

    /**
     * Supprime une voiture
     *
     * @param id ID de la voiture
     * @return Un Boolean pour avoir succès ou false de la fonction
     * @throws DaoException Pour remonter la couche de l'exception au Main
     */
    @Override
    public boolean delete(int id) throws  DaoException {
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
     * Construit l'objet Voiture grâce au ResultSet
     *
     * @param rs réponse de la requête SQL
     * @return Voiture
     * @throws SQLException Si erreur de SQL
     */
    private Voiture itemBuilder(ResultSet rs) throws SQLException
    {
        Conducteur UnConducteur = new Conducteur();
        UnConducteur.setNumConducteur(rs.getInt("numConducteur"));
        UnConducteur.setNom(rs.getString("nom"));
        UnConducteur.setPrenom(rs.getString("prenom"));

        Voiture UneVoiture = new Voiture();
        UneVoiture.setNumVoiture(rs.getInt("numVoiture"));
        UneVoiture.setDesignation(rs.getString("designation"));
        UneVoiture.setImmatriculation(rs.getString("immatriculation"));
        UneVoiture.setUnConducteur(UnConducteur);

        return UneVoiture;
    }
}
