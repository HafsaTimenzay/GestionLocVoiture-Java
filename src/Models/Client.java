package Models;

public class Client extends Personne {

    // Constructor
    public Client(String nom, String prenom){
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
