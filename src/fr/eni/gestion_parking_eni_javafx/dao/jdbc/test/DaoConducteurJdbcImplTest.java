package fr.eni.gestion_parking_eni_javafx.dao.jdbc.test;

import fr.eni.gestion_parking_eni_javafx.bo.Conducteur;
import fr.eni.gestion_parking_eni_javafx.dao.DaoConducteur;
import fr.eni.gestion_parking_eni_javafx.dao.DaoException;
import fr.eni.gestion_parking_eni_javafx.dao.DaoFactory;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test unitaires
 */
class DaoConducteurJdbcImplTest {

    private static DaoConducteur daoConducteur = null;

    private static Conducteur unConducteurParDefaut = null;
    private static Conducteur unConducteurInsert = null;
    private static Conducteur unConducteurUpdate  = null;

    /**
     * Test unitaire avant pour ajouter un conducteur par défaut
     */
    @BeforeAll
    static void setUpBeforeClass()
    {
        try
        {
            //Ajouter 1 par défaut
            daoConducteur = DaoFactory.getDaoConducteur();

            unConducteurParDefaut = new Conducteur();
            unConducteurParDefaut.setNom("test_defaut");
            unConducteurParDefaut.setPrenom("test_defaut");

            daoConducteur.insert(unConducteurParDefaut);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test unitaire après pour supprimer les jeux d'essais
     */
    @AfterAll
    static void tearDownAfterClass()
    {
        try
        {
            if(unConducteurParDefaut != null && daoConducteur.selectById(unConducteurParDefaut.getNumConducteur()) != null)
            {
                daoConducteur.delete(unConducteurParDefaut.getNumConducteur());
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }
        try
        {
            if(unConducteurInsert != null && daoConducteur.selectById(unConducteurInsert.getNumConducteur()) != null)
            {
                daoConducteur.delete(unConducteurInsert.getNumConducteur());
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }
        try
        {
            if(unConducteurUpdate != null && daoConducteur.selectById(unConducteurUpdate.getNumConducteur()) != null)
            {
                daoConducteur.delete(unConducteurUpdate.getNumConducteur());
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
     * Test unitaire insert si le conducteur a bien été inséré dans la BdD
     */
    @Test
    void insert()
    {
        try
        {
            //Ajouter 1
            int sizeBeforeInsert = daoConducteur.selectAll().size();
            unConducteurInsert = new Conducteur();
            unConducteurInsert.setNom("TEST Nom Ajout");
            unConducteurInsert.setPrenom("TEST Prénom Ajout");
            daoConducteur.insert(unConducteurInsert);
            int sizeAfterInsert = daoConducteur.selectAll().size();
            assertEquals((sizeBeforeInsert + 1), sizeAfterInsert);
        } catch (DaoException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Test unitaire update si le conducteur a bien été mise à jour dans la BdD
     */
    @Test
    void update()
    {
        try
        {
            //Mettre à jour le premier conducteur
            List<Conducteur> lesConducteurs = daoConducteur.selectAll();
            int sizeBeforeUpdate = lesConducteurs.size();

            unConducteurUpdate = new Conducteur();
            unConducteurUpdate = lesConducteurs.get(0);
            unConducteurUpdate.setNom("Test Update");
            unConducteurUpdate.setPrenom("Test Update");

            daoConducteur.update(unConducteurUpdate);
            int sizeAfterUpdate = daoConducteur.selectAll().size();
            assertEquals(sizeBeforeUpdate , sizeAfterUpdate);

            Conducteur unConducteurUpdateOld = unConducteurUpdate;
            unConducteurUpdate = daoConducteur.selectById(unConducteurUpdate.getNumConducteur());

            assertTrue(unConducteurUpdate.getNumConducteur()>0);
            assertEquals(unConducteurUpdateOld.getNumConducteur() , unConducteurUpdate.getNumConducteur());

        } catch (DaoException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Test unitaire delete si le conducteur a bien été supprimé dans la BdD
     */
    @Test
    void delete()
    {
        try
        {
            //Supprimer le premier conducteur
            Conducteur unConducteurDelete;
            List<Conducteur> lesConducteurs = daoConducteur.selectAll();

            int sizeBeforeDelete = lesConducteurs.size();
            unConducteurDelete = lesConducteurs.get(0);

            daoConducteur.delete(unConducteurDelete.getNumConducteur());
            int sizeAfterDelete =  daoConducteur.selectAll().size();

            assertEquals((sizeBeforeDelete-1) , sizeAfterDelete);

            Conducteur unConducteur = daoConducteur.selectById(unConducteurDelete.getNumConducteur());
            assertNull(unConducteur);

        } catch (DaoException e)
        {
            e.printStackTrace();
        }
    }
}