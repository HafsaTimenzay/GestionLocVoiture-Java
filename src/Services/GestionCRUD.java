package Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import javax.swing.DefaultListModel;

public interface GestionCRUD {

    public void ajoute(DefaultListModel<String> listModel, HashMap<Integer, String> EtudiantMap);
    public void lire(int id);
    public void mettreAjour(int id, String nom, double note);
    public void supprimer(int id);
    
}