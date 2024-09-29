package Views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;
import Services.GestionFlotte;

// disable modification the table from the interface graphic
class NonEditableTableModel extends DefaultTableModel {
    public NonEditableTableModel(Object[] columnNames, int rowCount) {
        super(columnNames, rowCount);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}

public class AdminView extends JFrame {

    private JTextField immatriculField, marqueField, modelField;
    private JButton logoutBtn, submitBtn, UpdateBtn, DeleteBtn;
    private JComboBox<String> etatCombox;
    private JTable table;
    private NonEditableTableModel tableModel; // Use custom model
    private HashMap<Integer, String> voitureMap = new HashMap<>();
    private int selectedId = -1;

    public JComboBox<String> getEtatCombox() {
        return etatCombox;
    }

    public AdminView() {
        // Initialization of the window
        setTitle("Gestion Flotte Admin");
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
        JLabel title = new JLabel("ADMIN GESTION FLOTTE", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        TopPanel.add(title, BorderLayout.CENTER);

        // Add to the edges
        add(TopPanel, BorderLayout.NORTH);

        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Form panel
        JPanel formPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        formPanel.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));

        // Labels and fields
        JLabel immatricuLabel = new JLabel("Immatriculation:");
        immatriculField = new JTextField(10);
        JLabel marquLabel = new JLabel("Marque: ");
        marqueField = new JTextField(15);
        JLabel modelLabel = new JLabel("Model: ");
        modelField = new JTextField(15);
        JLabel etatLabel = new JLabel("Etat: ");
        String[] etats = { "disponible", "louée", "maintenance" };
        etatCombox = new JComboBox<>(etats);

        // Add to form panel
        formPanel.add(immatricuLabel);
        formPanel.add(immatriculField);
        formPanel.add(marquLabel);
        formPanel.add(marqueField);
        formPanel.add(modelLabel);
        formPanel.add(modelField);
        formPanel.add(etatLabel);
        formPanel.add(etatCombox);

        // Add form panel to main panel
        mainPanel.add(formPanel);

        // Submit button panel
        JPanel submitPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        submitBtn = new JButton("Ajouter");
        submitPanel.add(submitBtn);
        mainPanel.add(submitPanel);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(30, 10, 5, 10));
        UpdateBtn = new JButton("Modifier");
        DeleteBtn = new JButton("Supprimer");
        buttonPanel.add(UpdateBtn);
        buttonPanel.add(DeleteBtn);
        mainPanel.add(buttonPanel, BorderLayout.EAST);

        // Table
        String[] voitrues = { "Immatriculation", "Marque", "Model", "Etat" };
        tableModel = new NonEditableTableModel(voitrues, 0); // Use custom model

        table = new JTable(tableModel);
        JScrollPane scrolPane = new JScrollPane(table);
        scrolPane.setBorder(BorderFactory.createEmptyBorder(5, 20, 20, 20));
        mainPanel.add(scrolPane, BorderLayout.CENTER);

        add(mainPanel, BorderLayout.CENTER);

        logoutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                LoginView login_view = new LoginView();
                login_view.setVisible(true);
            }
        });

        GestionFlotte voiture = new GestionFlotte();

        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String immatriculation = immatriculField.getText();
                String marque = marqueField.getText();
                String modele = modelField.getText();
                Object etat = etatCombox.getSelectedItem();
                if (!immatriculation.isEmpty() && !marque.isEmpty() && !modele.isEmpty()
                        && !String.valueOf(etat).isEmpty()) {
                    if (selectedId == -1) {
                        voiture.ajouter(immatriculation, marque, modele, String.valueOf(etat));
                        voiture.lire(tableModel, voitureMap);
                    } else {
                        voiture.mettreAjour(selectedId, immatriculation, marque, modele, String.valueOf(etat));
                        voiture.lire(tableModel, voitureMap);
                        selectedId = -1;
                    }
                    immatriculField.setText("");
                    marqueField.setText("");
                    modelField.setText("");
                    etatCombox.setSelectedItem("disponible");
                } else {
                    JOptionPane.showMessageDialog(scrolPane, "Tous les champs sont requis!");
                }
            }
        });

        // Supprimer
        DeleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedId != -1) {
                    voiture.supprimer(selectedId);
                    int row = table.getSelectedRow();
                    tableModel.removeRow(row);
                    table.clearSelection();
                    selectedId = -1;
                    System.out.println("Voiture supprimée.");
                } else {
                    System.out.println("Erreur: voiture n'est pas sélectionnée");
                }
            }
        });

        // Modifier
        UpdateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = table.getSelectedRow();
                if (selectedIndex != -1) {
                    immatriculField.setText(String.valueOf(table.getValueAt(selectedIndex, 0)));
                    marqueField.setText(String.valueOf(table.getValueAt(selectedIndex, 1)));
                    modelField.setText(String.valueOf(table.getValueAt(selectedIndex, 2)));
                    etatCombox.setSelectedItem(table.getValueAt(selectedIndex, 3));
                } else {
                    JOptionPane.showMessageDialog(null, "Sélectionnez une voiture à modifier");
                }
            }
        });

        // Selection
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
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
                            System.out.println("selected id: " + selectedId);
                            break;
                        }
                    }
                } else {
                    selectedId = -1;
                    System.out.println("Erreur: aucune voiture sélectionnée");
                }
            }
        });

        voiture.lire(tableModel, voitureMap);
    }

}
