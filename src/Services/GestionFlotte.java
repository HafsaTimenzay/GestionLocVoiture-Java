package Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import java.util.HashMap;
import java.util.Arrays;

public class GestionFlotte{ // implements GestionCRUD

    public void lire(DefaultTableModel tableModel, HashMap<Integer, String> voitureMap) {
     String query = "SELECT * FROM voiture";
    try (
        Connection connection = DbConnection.getConnection();
        PreparedStatement preparedStm = connection.prepareStatement(query);
        ResultSet resultSet = preparedStm.executeQuery();
    ) {
        tableModel.setRowCount(0);
        voitureMap.clear();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String immatriculation = resultSet.getString("immatriculation");
            String marque = resultSet.getString("marque");
            String modele = resultSet.getString("modele");
            String etat = resultSet.getString("etat");

            String[] voitures = {immatriculation, marque, modele, etat};
            tableModel.addRow(voitures);
            voitureMap.put(id, Arrays.toString(voitures));
            System.out.println(Arrays.toString(voitures));
            
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ajouter(String immatriculation, String marque,String modele, String etat){
        String query = "INSERT INTO voiture(immatriculation, marque, modele, etat) VALUES(?,?,?,?)";
        try (
            Connection connection = DbConnection.getConnection();
            PreparedStatement preparedStm = connection.prepareStatement(query);
        ){
            preparedStm.setString(1, immatriculation);
            preparedStm.setString(2, marque);
            preparedStm.setString(3, modele);
            preparedStm.setString(4, etat);
            preparedStm.executeUpdate();            
            
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


    public void mettreAjour(int id, String immatriculation, String marque, String modele, String etat){
        String query = "UPDATE voiture SET immatriculation = ?, marque = ?, modele = ?, etat = ?  WHERE id = ?";
        try (
            Connection connection = DbConnection.getConnection();
            PreparedStatement preparedStm = connection.prepareStatement(query);
        ){
            preparedStm.setString(1,immatriculation);
            preparedStm.setString(2,marque);
            preparedStm.setString(3,modele);
            preparedStm.setString(4,etat);
            preparedStm.setInt(5,id);

            int rowUpd = preparedStm.executeUpdate();
            if(rowUpd>0){System.out.println("Voiture mise a jour avec succes");}
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("no update");
        }
    }

    public void supprimer(int id){
        String query = "DELETE FROM voiture WHERE id=?";
        try (
            Connection connection = DbConnection.getConnection();
            PreparedStatement preparedStm = connection.prepareStatement(query);
        ) {
            preparedStm.setInt(1,id);
            int rowDelet = preparedStm.executeUpdate();
            if(rowDelet>0){System.out.println("row delete with secess");}
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
 