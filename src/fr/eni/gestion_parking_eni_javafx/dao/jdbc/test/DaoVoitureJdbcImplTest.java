package fr.eni.gestion_parking_eni_javafx.dao.jdbc.test;

import fr.eni.gestion_parking_eni_javafx.bo.Conducteur;
import fr.eni.gestion_parking_eni_javafx.bo.Voiture;
import fr.eni.gestion_parking_eni_javafx.dao.DaoConducteur;
import fr.eni.gestion_parking_eni_javafx.dao.DaoVoiture;
import fr.eni.gestion_parking_eni_javafx.dao.DaoException;
import fr.eni.gestion_parking_eni_javafx.dao.DaoFactory;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test unitaires
 */
class DaoVoitureJdbcImplTest {

    private static DaoVoiture daoVoiture = null;
    private static DaoConducteur daoConducteur = null;

    private static Conducteur conducteurTest = null;
    private static Voiture uneVoitureParDefaut = null;
    private static Voiture uneVoitureInsert = null;
    private static Voiture uneVoitureUpdate  = null;

    /**
     * Test unitaire avant pour ajouter une voiture et conducteur par défaut
     */
    @BeforeAll
    static void setUpBeforeClass()
    {
        try
        {
            //Ajouter 1 par défaut
            daoVoiture = DaoFactory.getDaoVoiture();
            daoConducteur = DaoFactory.getDaoConducteur();

            conducteurTest = new Conducteur();
            conducteurTest.setNom("TEST ajout");
            conducteurTest.setPrenom("TEST ajout");
            daoConducteur.insert(conducteurTest);
            conducteurTest = daoConducteur.selectAll().get(0);

            uneVoitureParDefaut = new Voiture();
            uneVoitureParDefaut.setDesignation("test_def");
            uneVoitureParDefaut.setImmatriculation("test_def");
            uneVoitureParDefaut.setUnConducteur(conducteurTest);
            daoVoiture.insert(uneVoitureParDefaut);

        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test unitaire après pour supprimer les jeux d'essais
     */
    @AfterAll
    static void tearDownAfterClass() {
        try {
            if (uneVoitureParDefaut != null && daoVoiture.selectById(uneVoitureParDefaut.getNumVoiture()) != null) {
                daoVoiture.delete(uneVoitureParDefaut.getNumVoiture());
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }
        try {
            if (uneVoitureInsert != null && daoVoiture.selectById(uneVoitureInsert.getNumVoiture()) != null) {
                daoVoiture.delete(uneVoitureInsert.getNumVoiture());
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }
        try {
            if (uneVoitureUpdate != null && daoVoiture.selectById(uneVoitureUpdate.getNumVoiture()) != null) {
                daoVoiture.delete(uneVoitureUpdate.getNumVoiture());
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }
        try {
            if (conducteurTest != null && daoConducteur.selectById(conducteurTest.getNumConducteur()) != null) {
                daoConducteur.delete(conducteurTest.getNumConducteur());
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }



    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void selectAll() {

    }

    @Test
    void selectById() {

    }

    /**
     * Test unitaire insert si la voiture a bien été insérée dans la BdD
     */
    @Test
    void insert()
    {
        try
        {
            //Ajouter 1
            int sizeBeforeInsert = daoVoiture.selectAll().size();

            uneVoitureInsert = new Voiture();
            uneVoitureInsert.setDesignation("TEST Desi");
            uneVoitureInsert.setImmatriculation("TEST Imma");
            uneVoitureInsert.setUnConducteur(daoConducteur.selectAll().get(0));

            assertTrue(uneVoitureInsert.getImmatriculation().length() <=10);

            daoVoiture.insert(uneVoitureInsert);
            int sizeAfterInsert = daoVoiture.selectAll().size();
            assertEquals((sizeBeforeInsert + 1), sizeAfterInsert);
        } catch (DaoException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Test unitaire update si la voiture a bien été mise à jour dans la BdD
     */
    @Test
    void update()
    {
        try
        {
            //Mettre à jour le premier conducteur
            List<Voiture> lesVoitures = daoVoiture.selectAll();
            int sizeBeforeUpdate = lesVoitures.size();

            uneVoitureUpdate = new Voiture();
            uneVoitureUpdate = lesVoitures.get(0);
            uneVoitureUpdate.setDesignation("Test up");
            uneVoitureUpdate.setImmatriculation("Test up");
            uneVoitureUpdate.setUnConducteur(daoConducteur.selectAll().get(0));

            assertTrue(uneVoitureUpdate.getImmatriculation().length() <=10);

            daoVoiture.update(uneVoitureUpdate);
            int sizeAfterUpdate = daoVoiture.selectAll().size();
            assertEquals(sizeBeforeUpdate , sizeAfterUpdate);

            Voiture unVoitureUpdateOld = uneVoitureUpdate;
            uneVoitureUpdate = daoVoiture.selectById(uneVoitureUpdate.getNumVoiture());

            assertTrue(uneVoitureUpdate.getNumVoiture()>0);
            assertEquals(unVoitureUpdateOld.getNumVoiture() , uneVoitureUpdate.getNumVoiture());

        } catch (DaoException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Test unitaire delete si la voiture a bien été supprimé dans la BdD
     */
    @Test
    void delete()
    {
        try
        {
            //Supprimer le premier conducteur
            Voiture unVoitureDelete;
            List<Voiture> lesVoitures = daoVoiture.selectAll();

            int sizeBeforeDelete = lesVoitures.size();
            unVoitureDelete = lesVoitures.get(0);

            daoVoiture.delete(unVoitureDelete.getNumVoiture());
            int sizeAfterDelete =  daoVoiture.selectAll().size();

            assertEquals((sizeBeforeDelete-1) , sizeAfterDelete);

            Voiture unVoiture = daoVoiture.selectById(unVoitureDelete.getNumVoiture());
            assertNull(unVoiture);

        } catch (DaoException e)
        {
            e.printStackTrace();
        }
    }
}