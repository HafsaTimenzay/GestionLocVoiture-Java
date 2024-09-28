package Models;

public class Voiture {

    // Attribute
    protected String immatriculation;
    protected String marque;
    protected String modele;
    protected String etat;
    protected static int nbrTotVoiture;

    // Constructor
    public Voiture(String immatriculation, String marque, String modele, String etat) {
        this.immatriculation = immatriculation;
        this.marque = marque;
        this.modele = modele;
        this.etat = etat;
        nbrTotVoiture++;
    }

    // Getters
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

    // Affichage
    @Override
    public String toString() {
        return "Voiture [immatriculation=" + immatriculation + ", marque=" + marque + ", modele=" + modele + ", etat="
                + etat + "]";
    }

    // Equals
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Voiture voiture = (Voiture) obj;
        return immatriculation.equals(voiture.immatriculation);
    }

}
