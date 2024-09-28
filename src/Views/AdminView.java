package Views;

import javax.swing.*;

import java.awt.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// import javax.swing.event.ListSelectionEvent;
// import javax.swing.event.ListSelectionListener;
// import java.util.HashMap;
// import java.util.Map;

public class AdminView extends JFrame{

    private JTextField immatriculField, marqueField, modelField;
    private JButton logoutBtn, submitBtn, UpdateBtn, DeleteBtn;
    private JComboBox<String> etatCombox;
    private JTable table;
    private DefaultTableModel tableModel;

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

        Object[] row1 = {"ABC123", "Toyota", "Camry", "disponible"};
        Object[] row2 = {"DEF456", "Honda", "Civic", "louée"};
        Object[] row3 = {"GHI789", "Ford", "Focus", "maintenance"};

        tableModel.addRow(row1);
        tableModel.addRow(row2);
        tableModel.addRow(row3);

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
        
    }
    public static void main(String[] args) {
        AdminView admin_view = new AdminView();
        admin_view.setVisible(true);
    }
}
