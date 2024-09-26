package Models;


public class Voiture {

    protected String immatriculation;
    protected String marque;
    protected String modele;
    protected String etat;
    protected static int nbrTotVoiture;

    public Voiture(String immatriculation, String marque, String modele, String etat) {
        this.immatriculation = immatriculation;
        this.marque = marque;
        this.modele = modele;
        this.etat = etat;
        nbrTotVoiture++ ;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public String getMarque() {
        return marque;
    }

    public String getModele() {
        return modele;
    }

    public String getEtat() {
        return etat;
    }

    public static int getNbrTotVoiture() {
        return nbrTotVoiture;
    }

    @Override
    public String toString() {
        return "Voiture [immatriculation=" + immatriculation + ", marque=" + marque + ", modele=" + modele + ", etat="
                + etat + "]";
    }
    
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(obj == null || getClass() != obj.getClass()){
            return false;
        }
        Voiture voiture = (Voiture) obj;
        return immatriculation.equals(voiture.immatriculation);
    }

    
public static void main(String[] args) {
    
    Voiture v1= new Voiture("34334k","Audi", "lakher", "disponible");
    v1.toString();
}



}
