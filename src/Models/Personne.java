package Models;

public abstract class Personne {

    // Attribute
    protected String nom;
    protected String prenom;

    // Constructer
    public Personne(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    // Getters
    public String getNom() {
        return nom;
    }
    public String getPrenom() {
        return prenom;
    }


}
