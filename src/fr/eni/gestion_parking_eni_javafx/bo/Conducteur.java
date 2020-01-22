package fr.eni.gestion_parking_eni_javafx.bo;

/**
 * Classe Conducteur
 */
public class Conducteur {

    private int numConducteur;
    private String nom;
    private String prenom;

    public Conducteur()
    {

    }

    public Conducteur(String nom, String prenom)
    {
        this.nom = nom;
        this.prenom = prenom;
    }

    //Factoriser les constructeurs
    public Conducteur(int numConducteur, String nom, String prenom) {
        this(nom,prenom);
        this.numConducteur = numConducteur;
    }

    public int getNumConducteur() {
        return numConducteur;
    }

    public void setNumConducteur(int numConducteur) {
        this.numConducteur = numConducteur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Override
    public String toString() {
        return  numConducteur != 0 ? nom.toUpperCase() + " "+ prenom : null;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Conducteur && ((Conducteur) obj).getNumConducteur() == this.numConducteur;
    }
}
