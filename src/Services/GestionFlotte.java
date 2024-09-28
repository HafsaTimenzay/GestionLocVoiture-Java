package Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import javax.swing.table.DefaultTableModel;

public class GestionFlotte{ // implements GestionCRUD
    
    public void lire(DefaultTableModel tableModel) {
     String query = "SELECT * FROM voiture";
    try (
        Connection connection = DbConnection.getConnection();
        PreparedStatement preparedStm = connection.prepareStatement(query);
        ResultSet resultSet = preparedStm.executeQuery();
    ) {
        // EtudiantMap.clear();
        while (resultSet.next()) {
            // int id = resultSet.getInt("id");
            String immatriculation = resultSet.getString("immatriculation");
            String marque = resultSet.getString("marque");
            String modele = resultSet.getString("modele");
            String etat = resultSet.getString("etat");

            String[] voitures = {immatriculation, marque, modele, etat};
            // Store student ID and data in the map                               
            tableModel.addRow(voitures);
            // EtudiantMap.put(id, voitures);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


    // public Etudiant lire(int id){
    //     String query = "SELECT * FROM Etudiant WHERE id=?";
    //     Etudiant etudiant = null;
    //     try (
    //         Connection connection = DbConnection.getConnection();
    //         PreparedStatement preparedStm = connection.prepareStatement(query);
    //     ) {
    //         preparedStm.setInt(1, id);
    //         ResultSet result = preparedStm.executeQuery();
    //         if (result.next()) {
    //             etudiant = new Etudiant();
    //             etudiant.nom = result.getString("nom");
    //             etudiant.note = result.getDouble("note");
    //         }
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    //     return etudiant;
    // }


    // public void mettreAjour(int id, String nom, double note){
    //     String query = "UPDATE Etudiant SET nom = ?, note = ? WHERE id = ?";
    //     try (
    //         Connection connection = DbConnection.getConnection();
    //         PreparedStatement preparedStm = connection.prepareStatement(query);
    //     ){
    //         preparedStm.setString(1,nom);
    //         preparedStm.setDouble(2,note);
    //         preparedStm.setInt(3,id);

    //         int rowUpd = preparedStm.executeUpdate();
    //         if(rowUpd>0){System.out.println("Etudiant mise a jour avec succes");}
            
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //         System.out.println("no update");
    //     }
    // }

    // public void supprimer(int id){
    //     String query = "DELETE FROM Etudiant WHERE id=?";
    //     try (
    //         Connection connection = DbConnection.getConnection();
    //         PreparedStatement preparedStm = connection.prepareStatement(query);
    //     ) {
    //         preparedStm.setInt(1,id);
    //         int rowDelet = preparedStm.executeUpdate();
    //         if(rowDelet>0){System.out.println("row delete with secess");}
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    // }

}
 