package fr.eni.GestionParkingENIJavaFX.bo;

public class Voiture {

    private int numVoiture;
    private String nom;
    private String PI;
    private Conducteur UnConducteur;

    public Voiture()
    {

    }

    public Voiture(int numVoiture, String nom, String PI, Conducteur unConducteur) {
        this.numVoiture = numVoiture;
        this.nom = nom;
        this.PI = PI;
        UnConducteur = unConducteur;
    }

    public Voiture(String nom, String PI, Conducteur unConducteur) {
        this.nom = nom;
        this.PI = PI;
        UnConducteur = unConducteur;
    }

    public Voiture(String nom, String PI) {
        this.nom = nom;
        this.PI = PI;
    }

    public int getNumVoiture() {
        return numVoiture;
    }

    public void setNumVoiture(int numVoiture) {
        this.numVoiture = numVoiture;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPI() {
        return PI;
    }

    public void setPI(String PI) {
        this.PI = PI;
    }

    public Conducteur getUnConducteur() {
        return UnConducteur;
    }

    public void setUnConducteur(Conducteur unConducteur) {
        UnConducteur = unConducteur;
    }
}
