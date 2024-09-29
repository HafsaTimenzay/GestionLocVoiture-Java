package Views;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import Services.GestionFlotte;
import java.awt.event.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// disable modification the table from the interface graphic
class NonEditableTableModel extends DefaultTableModel {public NonEditableTableModel(Object[] columnNames, int rowCount) { super(columnNames, rowCount);}
    @Override
    public boolean isCellEditable(int row, int column) {return false;}
}

public class UserView extends JFrame {

    private JButton logoutBtn;
    private JTable table;
    private NonEditableTableModel tableModel; // Use custom model
    private HashMap<Integer, String> voitureMap = new HashMap<>();
    private int selectedId = -1;

    public UserView() {
        // Initialization of the window
        setTitle("Gestion Flotte Client");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top panel
        JPanel TopPanel = new JPanel(new BorderLayout());
        TopPanel.setBorder(BorderFactory.createEmptyBorder(30, 10, 10, 10));

        // Logout button
        logoutBtn = new JButton("Déconnecter");
        TopPanel.add(logoutBtn, BorderLayout.EAST);

        // Heading title
        JLabel title = new JLabel("CLIENT GESTION FLOTTE", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        TopPanel.add(title, BorderLayout.CENTER);

        // Add to the edges
        add(TopPanel, BorderLayout.NORTH);

        String[] voitrues = { "Immatriculation", "Marque", "Model", "Etat" };
        tableModel = new NonEditableTableModel(voitrues, 0); // Use custom model

        table = new JTable(tableModel);

        GestionFlotte voiture = new GestionFlotte();
        voiture.lire(tableModel, voitureMap);

        JScrollPane scrolPane = new JScrollPane(table);
        scrolPane.setBorder(BorderFactory.createEmptyBorder(40, 20, 20, 20));
        add(scrolPane, BorderLayout.CENTER);

        logoutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                LoginView login_view = new LoginView();
                login_view.setVisible(true);
            }
        });

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Get the selected value
                int row = table.getSelectedRow();
                String[] rowData = new String[4];
                for (int i = 0; i <= 3; i++) {
                    rowData[i] = String.valueOf(table.getValueAt(row, i));
                }
                String selectedValue = Arrays.toString(rowData);
                System.out.println("selectedValue : " + selectedValue);

                if (selectedValue != null) {
                    for (Map.Entry<Integer, String> entry : voitureMap.entrySet()) {
                        if (entry.getValue().equals(selectedValue)) {
                            selectedId = entry.getKey();
                            if (row >= 0) {
                                Object etat = table.getValueAt(row, 3);
                                if (etat.equals("maintenance")) {
                                    JOptionPane.showMessageDialog(scrolPane, "cette voiture en maintenance");
                                } else if (etat.equals("louée")) {
                                    JOptionPane.showMessageDialog(scrolPane, "cette voiture est déja louée");
                                } else if (etat.equals("disponible")) {
                                    String immatriculation = String.valueOf(table.getValueAt(row, 0));
                                    String marque = String.valueOf(table.getValueAt(row, 1));
                                    String model = String.valueOf(table.getValueAt(row, 2));
                                    int option = JOptionPane.showConfirmDialog(
                                            new UserView(),
                                            "Voulez-Vous louer le voiture " + marque + " " + model + " ?",
                                            "Confirmation",
                                            JOptionPane.YES_NO_OPTION);
                                    if (option == JOptionPane.YES_OPTION) {
                                        JOptionPane.showMessageDialog(new UserView(), "tu louee cette voiture");
                                        voiture.mettreAjour(selectedId, immatriculation, marque, model, "louée");
                                        table.setValueAt("louée", row, 3);
                                    }
                                }
                            }
                            break;
                        }
                    }
                } else {
                    selectedId = -1;
                    System.out.println("Error: no etudiant selectioner");
                }
            }
        });
    }

    public static void main(String[] args) {
        UserView user_view = new UserView();
        user_view.setVisible(true);
    }
}
