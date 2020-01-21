package fr.eni.gestion_parking_eni_javafx.ihm.controller;

import fr.eni.gestion_parking_eni_javafx.bll.BllException;
import fr.eni.gestion_parking_eni_javafx.bll.ConducteurManager;
import fr.eni.gestion_parking_eni_javafx.bll.VoitureManager;
import fr.eni.gestion_parking_eni_javafx.bo.Conducteur;
import fr.eni.gestion_parking_eni_javafx.bo.Voiture;
import fr.eni.gestion_parking_eni_javafx.dao.DaoException;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Controller GestionParkingController
 */
public class GestionParkingController implements Initializable
{
    private List<Conducteur> lesConducteurs = new ArrayList<>();
    private List<Voiture> lesVoitures = new ArrayList<>();

    //Manager
    private ConducteurManager conducteurManager;
    private VoitureManager voitureManager;

    @FXML
    private TableView tabVoitures;
    @FXML
    private TableColumn<Voiture, String> colDesignation;
    @FXML
    private TableColumn<Voiture, String> colImmatriculation;
    @FXML
    private TableColumn<Voiture, String> colConducteur;

    @FXML
    private TextField txtDesignation;
    @FXML
    private TextField txtImmatriculation;
    @FXML
    private ComboBox cmbConducteur;
    @FXML
    private ImageView imgSaveVoiture;

    @FXML
    private Button btnAjouterVoiture;
    @FXML
    private Button btnModifierVoiture;
    @FXML
    private Button btnSupprimerVoiture;

    @FXML
    private TableView<Conducteur> tabConducteurs;
    @FXML
    private TableColumn<Conducteur, String> colNom;
    @FXML
    private TableColumn<Conducteur, String> colPrenom;

    @FXML
    private TextField txtNom;
    @FXML
    private TextField txtPrenom;

    @FXML
    private Button btnAjouterConducteur;
    @FXML
    private Button btnModifierConducteur;
    @FXML
    private Button btnSupprimerConducteur;
    @FXML
    private ImageView imgSaveConducteur;

    /**
     * Initialise le composant
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        try
        {
            //CONDUCTEURS
            conducteurManager = ConducteurManager.getInstance();

            // Initialise le TableView Conducteur
            colNom.setCellValueFactory(new PropertyValueFactory<Conducteur, String>("nom"));
            colPrenom.setCellValueFactory(new PropertyValueFactory<Conducteur, String>("prenom"));

            // Pour redimensionner les colonnes automatiquement
            tabConducteurs.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

            refreshConducteur();

            //VOITURES
            voitureManager = VoitureManager.getInstance();

            // Initialise le TableView Conducteur
            colDesignation.setCellValueFactory(new PropertyValueFactory<Voiture, String>("designation"));
            colImmatriculation.setCellValueFactory(new PropertyValueFactory<Voiture, String>("immatriculation"));

            colConducteur.setCellValueFactory(new PropertyValueFactory<Voiture, String>("ConducteurNomPrenom"));
            //colConducteur.setSortable(false);

            // Pour redimensionner les colonnes automatiquement
            tabConducteurs.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

            refreshVoiture();

        } catch (BllException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Retourne l'item sélectionné
     * @return Voiture
     */
    private Voiture getVoitureSelected()
    {
        return ((Voiture)tabVoitures.getSelectionModel().getSelectedItem());
    }

    /**
     * Retourne l'item sélectionné
     * @return Conducteur
     */
    private Conducteur getConducteurSelected()
    {
        return tabConducteurs.getSelectionModel().getSelectedItem();
    }

    /**
     * Fonction qui se déclenche lorsque je clique sur la ligne de la tabConducteurs et qui va appeler remplirConducteur (affichage dans les champs)
     */
    @FXML
    public void handleSelectConducteur()
    {
        Conducteur unConducteur = getConducteurSelected();
        if(unConducteur != null)
        {
            remplirConducteur(unConducteur);
            btnModifierConducteur.setDisable(false);
            btnSupprimerConducteur.setDisable(false);

            btnAjouterConducteur.setDisable(true);
            imgSaveConducteur.setVisible(false);
            imgSaveConducteur.setDisable(true);
        }
    }

    /**
     * Fonction qui se déclenche lorsque je clique sur la ligne de la tabVoitures et qui va appeler remplirVoiture (affichage dans les champs)
     */
    @FXML
    public void handleSelectVoiture()
    {
        Voiture uneVoiture = getVoitureSelected();
        if(uneVoiture != null)
        {
            remplirVoiture(uneVoiture);
            imgSaveVoiture.setVisible(false);
            imgSaveVoiture.setDisable(true);

            btnAjouterVoiture.setDisable(false);
            btnModifierVoiture.setDisable(false);
            btnSupprimerVoiture.setDisable(false);
        }
    }

    /**
     * Rénitialise le formulaire pour ajouter un conducteur
     */
    public void handleAddConducteur() {

        txtNom.setText("");
        txtPrenom.setText("");

        imgSaveConducteur.setVisible(true);
        imgSaveConducteur.setDisable(false);

        btnAjouterConducteur.setDisable(true);
        btnModifierConducteur.setDisable(true);
        btnSupprimerConducteur.setDisable(true);
    }

    /**
     * Rénitialise le formulaire pour ajouter une voiture
     */
    public void handleAddVoiture() {

        txtDesignation.setText("");
        txtImmatriculation.setText("");
        cmbConducteur.setValue(null);

        imgSaveVoiture.setVisible(true);
        imgSaveVoiture.setDisable(false);

        btnAjouterVoiture.setDisable(true);
        btnModifierVoiture.setDisable(true);
        btnSupprimerVoiture.setDisable(true);
    }

    /**
     * Remplir les champs associés à la voiture
     * @param uneVoiture Voiture passée en param
     */
    private void remplirVoiture(Voiture uneVoiture)
    {
        txtDesignation.setText("");
        txtImmatriculation.setText("");
        cmbConducteur.setItems(null);

        txtDesignation.setText(uneVoiture.getDesignation());
        txtImmatriculation.setText(uneVoiture.getImmatriculation());
        cmbConducteur.setItems(FXCollections.observableList(lesConducteurs));
        if(uneVoiture.getUnConducteur() != null )
        {
            cmbConducteur.setValue(uneVoiture.getUnConducteur());
        }
    }

    /**
     * Remplir les champs associés au conducteur
     * @param unConducteur Conducteur passé en param
     */
    private void remplirConducteur(Conducteur unConducteur)
    {
        txtNom.setText("");
        txtPrenom.setText("");

        txtNom.setText(unConducteur.getNom());
        txtPrenom.setText(unConducteur.getPrenom());
    }

    /**
     * Refresh les infos du conducteur de la view
     */
    private void refreshConducteur()
    {
        try
        {
            tabConducteurs.setItems(null);
            lesConducteurs = conducteurManager.getLesConducteurs();
            tabConducteurs.setItems(FXCollections.observableList(lesConducteurs));
            tabConducteurs.refresh();

            txtNom.setText("");
            txtPrenom.setText("");
            txtNom.setStyle(null);
            txtPrenom.setStyle(null);

            imgSaveConducteur.setVisible(false);
            imgSaveConducteur.setDisable(false);

            btnAjouterConducteur.setDisable(false);
            btnModifierConducteur.setDisable(true);
            btnSupprimerConducteur.setDisable(true);

        } catch (BllException e) {
            e.printStackTrace();
        }
    }

    /**
     * Refresh les infos de la voiture de la view
     */
    private void refreshVoiture()
    {
        try
        {
            tabVoitures.setItems(null);
            lesVoitures = voitureManager.getLesVoitures();
            tabVoitures.setItems(FXCollections.observableList(lesVoitures));
            tabVoitures.refresh();

            txtDesignation.setText("");
            txtDesignation.setStyle(null);
            txtImmatriculation.setText("");
            txtImmatriculation.setStyle(null);
            cmbConducteur.setValue(null);

            btnAjouterVoiture.setDisable(false);
            btnModifierVoiture.setDisable(true);
            btnSupprimerVoiture.setDisable(true);

        } catch (BllException | DaoException e) {
            e.printStackTrace();
        }
    }

    /**
     * Insère un conducteur
     */
    public void addConducteur()
    {
        Conducteur unConducteur = new Conducteur();
        unConducteur.setNom(txtNom.getText());
        unConducteur.setPrenom(txtPrenom.getText());

        if(isInputValidConducteur())
        {
            try {
                // Affichage du message
                Alert alert=new Alert(Alert.AlertType.INFORMATION);

                if(conducteurManager.addConducteur(unConducteur))
                {
                    alert.setTitle("Succès");
                    alert.setHeaderText("Ajout du conducteur effectué");
                    alert.showAndWait();

                    refreshConducteur();
                }
                else
                {
                    alert.setTitle("Echec");
                    alert.setHeaderText("Ajout impossible");
                    alert.setContentText("Erreur rencontrée lors de l'ajout du conducteur.");
                }

                alert.showAndWait();

            } catch (DaoException | BllException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Met à jour un conducteur
     */
    public void updateConducteur()
    {
        Conducteur unConducteur = getConducteurSelected();
        if(unConducteur != null)
        {
            unConducteur.setNom(txtNom.getText());
            unConducteur.setPrenom(txtPrenom.getText());
            if(isInputValidConducteur())
            {
                try
                {
                    // Affichage du message
                    Alert alert=new Alert(Alert.AlertType.INFORMATION);

                    if(conducteurManager.updateConducteur(unConducteur))
                    {
                        alert.setTitle("Succès");
                        alert.setHeaderText("Mis à jour du conducteur effectué");

                        //Refresh la vue
                        refreshConducteur();
                    }
                    else
                    {
                        alert.setTitle("Echec");
                        alert.setHeaderText("Mis à jour impossible");
                        alert.setContentText("Erreur rencontrée lors de la mise à jour du conducteur.");
                    }

                    alert.showAndWait();

                } catch (DaoException | BllException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Supprime un conducteur
     */
    public void deleteConducteur()
    {
        Conducteur unConducteur = getConducteurSelected();
        if(unConducteur != null)
        {
            try
            {
                // Affichage du message
                Alert alert=new Alert(Alert.AlertType.INFORMATION);

                if(conducteurManager.removeConducteur(unConducteur))
                {
                    alert.setTitle("Succès");
                    alert.setHeaderText("Suppression du conducteur effectuée");

                    //Refresh la vue
                    refreshConducteur();
                }
                else
                {
                    alert.setTitle("Echec");
                    alert.setHeaderText("Suppresion impossible");
                    alert.setContentText("Erreur rencontrée lors de la suppresion du conducteur.");
                }
                alert.showAndWait();

            } catch (DaoException | BllException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Insère une voiture
     */
    public void addVoiture()
    {
        Voiture uneVoiture = itemVoiture();

        if(isInputValidVoiture())
        {
            try {
                // Affichage du message
                Alert alert=new Alert(Alert.AlertType.INFORMATION);

                if(voitureManager.addVoiture(uneVoiture))
                {
                    alert.setTitle("Succès");
                    alert.setHeaderText("Ajout de la voiture effectuée");
                    alert.showAndWait();

                    refreshVoiture();
                }
                else
                {
                    alert.setTitle("Echec");
                    alert.setHeaderText("Ajout impossible");
                    alert.setContentText("Erreur rencontrée lors de l'ajout de la voiture.");
                }

                alert.showAndWait();

            } catch (DaoException | BllException e) {
                e.printStackTrace();
            }
        }
    }

    private Voiture itemVoiture()
    {
        Voiture uneVoiture = new Voiture();
        uneVoiture.setDesignation(txtDesignation.getText());
        uneVoiture.setImmatriculation(txtImmatriculation.getText());
        uneVoiture.setUnConducteur((Conducteur)cmbConducteur.getSelectionModel().getSelectedItem());

        return uneVoiture;
    }

    /**
     * Met à jour d'une voiture
     */
    public void updateVoiture()
    {
        Voiture uneVoiture = itemVoiture();
        uneVoiture.setNumVoiture(getVoitureSelected().getNumVoiture());

        if(uneVoiture != null)
        {
            try
            {
                // Affichage du message
                Alert alert=new Alert(Alert.AlertType.INFORMATION);

                if(voitureManager.updateVoiture(uneVoiture))
                {
                    alert.setTitle("Succès");
                    alert.setHeaderText("Mis à jour de la voiture effectuée");

                    //Refresh la vue
                    refreshVoiture();
                }
                else
                {
                    alert.setTitle("Echec");
                    alert.setHeaderText("Mis à jour impossible");
                    alert.setContentText("Erreur rencontrée lors de la mise à jour de la voiture.");
                }

                alert.showAndWait();

            } catch (DaoException | BllException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Supprime une voiture
     */
    public void deleteVoiture()
    {
        Voiture uneVoiture = getVoitureSelected();
        if(uneVoiture != null)
        {
            try
            {
                // Affichage du message
                Alert alert=new Alert(Alert.AlertType.INFORMATION);

                if(voitureManager.removeVoiture(uneVoiture))
                {
                    alert.setTitle("Succès");
                    alert.setHeaderText("Suppression de la voiture effectuée");

                    refreshVoiture();
                }
                else
                {
                    alert.setTitle("Echec");
                    alert.setHeaderText("Suppresion impossible");
                    alert.setContentText("Erreur rencontrée lors de la suppresion de la voiture.");
                }

                alert.showAndWait();

            } catch (DaoException | BllException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean isInputValidVoiture()
    {
        String messageErreur = "";
        boolean retour = true;

        if (txtDesignation.getText() == null || txtDesignation.getText().length()<=0)
        {
            messageErreur =messageErreur + "\n" + "Désignation invalide";
            txtDesignation.setStyle("-fx-background-color: red;");
        }
        if (txtImmatriculation.getText() == null || txtImmatriculation.getText().length()<=0)
        {
            messageErreur =messageErreur + "\n" + "Immatriculation invalide";
            txtImmatriculation.setStyle("-fx-background-color: red;");
        }

        if (messageErreur.length() > 0)
        {
            // Affichage du message
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erreur");
            alert.setHeaderText("Vous avez oublié de saisir une valeur");
            alert.setContentText(messageErreur);
            alert.showAndWait();
            retour = false;
        }
        return retour;
    }

    private boolean isInputValidConducteur()
    {
        String messageErreur = "";
        boolean retour = true;

        if (txtNom.getText() == null || txtNom.getText().length()<=0)
        {
            messageErreur =messageErreur + "\n" + "Nom invalide";
            txtNom.setStyle("-fx-background-color: red;");
        }
        if (txtPrenom.getText() == null || txtPrenom.getText().length()<=0)
        {
            messageErreur =messageErreur + "\n" + "Prénom invalide";
            txtPrenom.setStyle("-fx-background-color: red;");
        }

        if (messageErreur.length() > 0)
        {
            // Affichage du message
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erreur");
            alert.setHeaderText("Vous avez oublié de saisir une valeur");
            alert.setContentText(messageErreur);
            alert.showAndWait();
            retour = false;
        }
        return retour;
    }

}
