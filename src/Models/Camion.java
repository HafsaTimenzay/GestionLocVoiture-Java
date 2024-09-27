package Models;

public class Camion extends Voiture{

    // Attribute
    private double capaciteDeCharge;

    // Constructor
    public Camion(String immatriculation, String marque, String modele, String etat, double capaciteDeCharge) {
        super(immatriculation, marque, modele, etat);
        this.capaciteDeCharge = capaciteDeCharge;
    }

    // Getters
    public double getCapaciteDeCharge() {
        return capaciteDeCharge;
    }
    public String getImmatriculation() {
        return super.immatriculation;
    }
    public String getMarque() {
        return super.marque;
    }
    public String getModele() {
        return super.modele;
    }
    public String getEtat() {
        return super.etat;
    }
    public static int getNbrTotVoiture() {
        return nbrTotVoiture;
    }


    @Override
    public String toString() {
        return "Camion [capaciteDeCharge = " + capaciteDeCharge + ",Immatriculation =" + getImmatriculation()
                + ", Marque  = " + getMarque() + ", Modele = " + getModele() + ", Etat  =" + getEtat() + "]";
    }

    public static void main(String[] args) {
        Camion c1= new Camion("ا1247","Audi", "lakher", "disponible",20);
        System.out.println(c1.toString());
    }
}
