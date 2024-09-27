package Models;

public class Employe extends Personne {
    
    // Constructor
    public Employe(String nom, String prenom){
        super(nom, prenom);
    }

    // Getters
    public String getNom() {
        return super.getNom();
    }
    public String getPrenom() {
        return super.getPrenom();
    }
}
