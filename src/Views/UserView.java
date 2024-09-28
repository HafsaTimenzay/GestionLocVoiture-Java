package Views;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

import Services.GestionCRUD;
import Services.GestionFlotte;

import java.awt.event.*;
import java.util.HashMap;
public class UserView extends JFrame {

    private JButton logoutBtn;
    private JTable table;
    private DefaultTableModel tableModel;
    private HashMap<Integer, String> voitureMap = new HashMap<>();


    public UserView() {
        // inialisation de la fonetre
        setTitle("Gestion Flotte Client");
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
        JLabel title = new JLabel("CLIENT GESTION FLOTTE", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        TopPanel.add(title, BorderLayout.CENTER);

        // add to the edges
        add(TopPanel, BorderLayout.NORTH);

        String[] voitrues = { "Immatriculation", "Marque", "Model", "Etat" };
        tableModel = new DefaultTableModel(voitrues, 0);

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
                int row = table.rowAtPoint(e.getPoint());
                int col = table.columnAtPoint(e.getPoint());
                if (row >= 0 && col >= 0) {
                    Object etat = table.getValueAt(row, 3);
                    if (etat.equals("maintenance")) {
                        JOptionPane.showMessageDialog(scrolPane, "cette voiture en maintenance");
                    } else if (etat.equals("louee")) {
                        JOptionPane.showMessageDialog(scrolPane, "cette voiture est déja louée");
                    } else if (etat.equals("disponible")) {
                        Object marque = table.getValueAt(row, 1);
                        Object model = table.getValueAt(row, 2);
                        int option = JOptionPane.showConfirmDialog(
                                new UserView(),
                                "Voulez-Vous louer le voiture " + marque + " " + model + " ?",
                                "Confirmation",
                                JOptionPane.YES_NO_OPTION);

                        if (option == JOptionPane.YES_OPTION) {
                            JOptionPane.showMessageDialog(new UserView(), "tu louee cette voiture");
                            table.setValueAt("louée", row, 3);
                        } // else if(option == JOptionPane.NO_OPTION){}

                    }
                }
            }
        });

    }

    public static void main(String[] args) {
        UserView user_view = new UserView();
        user_view.setVisible(true);
    }

}
