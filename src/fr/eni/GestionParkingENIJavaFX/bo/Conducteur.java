package fr.eni.GestionParkingENIJavaFX.bo;

import java.text.CompactNumberFormat;

public class Conducteur {

    private int numConducteur;
    private String nom;
    private String prenom;

    public Conducteur()
    {

    }

    public Conducteur(int numConducteur, String nom, String prenom) {
        this.numConducteur = numConducteur;
        this.nom = nom;
        this.prenom = prenom;
    }

    public Conducteur(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
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
        return "Conducteur{" +
                "numConducteur=" + numConducteur +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        return ((Conducteur)obj).getNumConducteur() == this.getNumConducteur();
    }
}
