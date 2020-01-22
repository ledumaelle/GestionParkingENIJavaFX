package fr.eni.gestion_parking_eni_javafx.bo;

/**
 *  Classe Voiture
 */
public class Voiture {


    private int numVoiture;
    private String designation;
    private String immatriculation;
    private Conducteur unConducteur;

    public Voiture()
    {

    }

    //Factoriser les constructeurs
    public Voiture(int numVoiture, String designation, String immatriculation, Conducteur unConducteur) {
        this(designation,immatriculation, unConducteur);
        this.numVoiture = numVoiture;
    }

    public Voiture(String designation, String immatriculation, Conducteur unConducteur) {
        this.designation = designation;
        this.immatriculation = immatriculation;
        this.unConducteur = unConducteur;
    }

    public int getNumVoiture() {
        return numVoiture;
    }

    public void setNumVoiture(int numVoiture) {
        this.numVoiture = numVoiture;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public Conducteur getUnConducteur() {
        return unConducteur;
    }

    public void setUnConducteur(Conducteur unConducteur) {
        this.unConducteur = unConducteur;
    }

     public String getConducteurNomPrenom() {
        return this.unConducteur.getNumConducteur() != 0 ? this.unConducteur.getNom().toUpperCase().trim() + " "+ this.unConducteur.getPrenom().trim() : "";
    }

    @Override
    public String toString() {
        return "Voiture{" +
                "numVoiture=" + numVoiture +
                ", designation='" + designation + '\'' +
                ", immatriculation='" + immatriculation + '\'' +
                ", unConducteur=" + unConducteur +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Voiture && ((Voiture)obj).getNumVoiture() == this.numVoiture;
    }
}
