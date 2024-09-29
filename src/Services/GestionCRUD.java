package Services;

import java.util.HashMap;
import javax.swing.table.DefaultTableModel;

public interface GestionCRUD {

    public void lire(DefaultTableModel tableModel, HashMap<Integer, String> voitureMap);
    public void ajoute(String immatriculation, String marque, String modele, String etat);
    public void mettreAjour(int id, String immatriculation, String marque, String modele, String etat);
    public void supprimer(int id);
    
}