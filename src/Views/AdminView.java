package Views;

import javax.swing.*;

import java.awt.*;
import javax.swing.table.DefaultTableModel;

import Services.GestionFlotte;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
// import javax.swing.event.ListSelectionEvent;
// import javax.swing.event.ListSelectionListener;
import java.util.HashMap;
// import java.util.Map;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class AdminView extends JFrame{

    private JTextField immatriculField, marqueField, modelField;
    private JButton logoutBtn, submitBtn, UpdateBtn, DeleteBtn;
    private JComboBox<String> etatCombox;
    private JTable table;
    private DefaultTableModel tableModel;
    private HashMap<Integer, String> voituretMap = new HashMap<>();
    private int selectedId = -1;


    public AdminView(){
        // inialisation de la fonetre
        setTitle("Gestion Flotte Admin");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // top pannel
        JPanel TopPanel = new JPanel(new BorderLayout());
        TopPanel.setBorder(BorderFactory.createEmptyBorder(30, 10, 10, 10));

        // logout button
        logoutBtn = new JButton("Déconnecter");
        TopPanel.add(logoutBtn, BorderLayout.EAST);

        // heading title
        JLabel title = new JLabel("ADMIN GESTION FLOTTE", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        TopPanel.add(title, BorderLayout.CENTER);

        // add to the edges
        add(TopPanel, BorderLayout.NORTH);


        // ajouter panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS)); // Utiliser BoxLayout pour empiler les composants

        // form panel
        JPanel formPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        formPanel.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));

        // labels et champs
        JLabel immatricuLabel = new JLabel("Immatriculation:");
        immatriculField = new JTextField(10);
        JLabel marquLabel = new JLabel("Marque: ");
        marqueField = new JTextField(15);
        JLabel modelLabel = new JLabel("Model: ");
        modelField = new JTextField(15);
        JLabel etatLabel = new JLabel("Etat: ");
        String[] etats = {"disponible", "louée", "maintenance"};
        etatCombox = new JComboBox<>(etats);

        // submit button
        
        // ajouter a form panel 
        formPanel.add(immatricuLabel);
        formPanel.add(immatriculField);
        formPanel.add(marquLabel);
        formPanel.add(marqueField);
        formPanel.add(modelLabel);  
        formPanel.add(modelField);
        formPanel.add(etatLabel);  
        formPanel.add(etatCombox); 
        
        // ajouter from panel a main panel
        mainPanel.add(formPanel);
        
        // ajouter submit button a btn panel
        JPanel submitPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        submitBtn = new JButton("Ajouter Voiture");
        submitPanel.add(submitBtn);
        mainPanel.add(submitPanel);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(30, 10, 5, 10));
        UpdateBtn = new JButton("Modefier");
        DeleteBtn = new JButton("supprimer");
        buttonPanel.add(UpdateBtn);
        buttonPanel.add(DeleteBtn);
        mainPanel.add(buttonPanel, BorderLayout.EAST);

        // afficher panel

        String[] voitrues = {"Immatriculation", "Marque", "Model", "Etat"};
        tableModel = new DefaultTableModel(voitrues, 0);

        table = new JTable(tableModel);


        JScrollPane scrolPane = new JScrollPane(table);
        scrolPane.setBorder(BorderFactory.createEmptyBorder(5, 20, 20, 20));

        mainPanel.add(scrolPane, BorderLayout.CENTER);

        add(mainPanel, BorderLayout.CENTER);

        logoutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
                LoginView login_view = new LoginView();
                login_view.setVisible(true);
            }
        });

        GestionFlotte voiture = new GestionFlotte();
        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String immatriculation = immatriculField.getText();
                String marque = marqueField.getText();
                String modele = modelField.getText();
                Object etat = etatCombox.getSelectedItem();
                voiture.ajouter(immatriculation, marque, modele, etat);
                voiture.lire(tableModel, voituretMap);
            }
        });

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                int row = table.rowAtPoint(e.getPoint());
                UpdateBtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e){
                        immatriculField.setText(String.valueOf(table.getValueAt(row, 0)));
                        marqueField.setText(String.valueOf(table.getValueAt(row,1)));
                        modelField.setText(String.valueOf(table.getValueAt(row,2)));
                        etatCombox.setSelectedItem(table.getValueAt(row,3));
                        
                        String immatriculation = immatriculField.getText();
                        String marque = marqueField.getText();
                        String modele = modelField.getText();
                        Object etat = etatCombox.getSelectedItem();
                        
        
                        voiture.mettreAjour(1, immatriculation, marque, modele, etat);
                        voiture.lire(tableModel, );
                    }
                });
            }
        });

                // Supprimer
                DeleteBtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (selectedId != -1) {
                            voiture.supprimer(selectedId);
        
                            String selectedValue = EtudiantList.getSelectedValue();
                            listModel.removeElement(selectedValue);
        
                            EtudiantList.clearSelection();
                            selectedId = -1;
                            System.out.println("etudiant supprimer.");
                        } else {
                            System.out.println("Error: etudiant n'est pas selectionner");
                        }
                    }
                });
                    
                // Modefier
                UpdateBtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int selectedIndex = EtudiantList.getSelectedIndex();
                        
                        if (selectedIndex != -1) {
                            String selectedValue = EtudiantList.getSelectedValue();
                            String[] splitValue = selectedValue.split(" : ");
                            nomField.setText(splitValue[0]);
                            noteField.setText(splitValue[1]);
                
                            System.out.println("modifier etudiant: " + selectedValue);
                        } else {
                            JOptionPane.showMessageDialog(null, "selectionner un etudiant a modifier");
                        }
                    }
                });
                                    
                    
                // Selection
                EtudiantList.addListSelectionListener(new ListSelectionListener() {
                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        if (!e.getValueIsAdjusting()) {
                            String selectedValue = EtudiantList.getSelectedValue();
                            if (selectedValue != null) {
                                for (Map.Entry<Integer, String> entry : EtudiantMap.entrySet()) {
                                    if (entry.getValue().equals(selectedValue)) {
                                        selectedId = entry.getKey();
                                        System.out.println("selected id: " + selectedId);
                                        break;
                                    }
                                }
                            } else {
                                selectedId = -1;
                                System.out.println("Error: no etudiant selectioner");
                            }
                        }
                    }
                });
        

        voiture.lire(tableModel);

    }
    public static void main(String[] args) {
        AdminView admin_view = new AdminView();
        admin_view.setVisible(true);
    }
}
