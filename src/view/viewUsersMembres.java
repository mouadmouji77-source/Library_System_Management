package view;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.EventObject;

public class viewUsersMembres extends JPanel {

    private JTable membresTable = new JTable(){
        @Override
        public boolean editCellAt(int row, int column, EventObject e) {
            return false; // Prevent cell editing
        }
    };
    private DefaultTableModel membresModel = new DefaultTableModel() {
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    private JButton modiferMembre = new JButton("Modifier");
    private JButton supprimerMembre = new JButton("Supprimer");
    private JButton ajouterMembre = new JButton("Ajouter");

    private JLabel rechercheLabel = new JLabel("Rechercher");
    private JTextField rechercheTextField = new JTextField(30);

    private MembreFormulaire membreFormulaire = new MembreFormulaire();

    public viewUsersMembres() {
        ajouterComposantes();
    }

    public void ajouterComposantes() {
        setLayout(new BorderLayout());
        setBorder(new TitledBorder("Gestion des Membres"));

        JPanel tablePanel = new JPanel(new BorderLayout());
        JScrollPane tableScrollPane = new JScrollPane(membresTable);
        membresTable.setModel(membresModel);
        tablePanel.setBorder(new TitledBorder("Liste des Membres"));
        tablePanel.add(tableScrollPane, BorderLayout.CENTER);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.add(rechercheLabel);
        searchPanel.add(rechercheTextField);

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonsPanel.add(ajouterMembre);
        buttonsPanel.add(modiferMembre);
        buttonsPanel.add(supprimerMembre);

        JPanel formPanel = new JPanel(new BorderLayout());
        formPanel.setBorder(new TitledBorder("Formulaire"));
        formPanel.add(membreFormulaire, BorderLayout.CENTER);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, tablePanel, formPanel);
        splitPane.setDividerLocation(1000); 

        add(searchPanel, BorderLayout.NORTH);
        add(splitPane, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);
    }

    public void setModelMembresTable(DefaultTableModel model) {
    	membresModel = model;
        this.membresTable.setModel(model);
    }

    public JTable getMembresTable() {
        return membresTable;
    }

    public void setMembresTable(JTable membresTable) {
        this.membresTable = membresTable;
    }

    public DefaultTableModel getMembresModel() {
        return membresModel;
    }

    public void setMembresModel(DefaultTableModel membresModel) {
        this.membresModel = membresModel;
    }

    public JButton getModiferMembre() {
        return modiferMembre;
    }

    public void setModiferMembre(JButton modiferMembre) {
        this.modiferMembre = modiferMembre;
    }

    public JButton getSupprimerMembre() {
        return supprimerMembre;
    }

    public void setSupprimerMembre(JButton supprimerMembre) {
        this.supprimerMembre = supprimerMembre;
    }

    public JButton getAjouterMembre() {
        return ajouterMembre;
    }

    public void setAjouterMembre(JButton ajouterMembre) {
        this.ajouterMembre = ajouterMembre;
    }

    public JLabel getRechercheLabel() {
        return rechercheLabel;
    }

    public void setRechercheLabel(JLabel rechercheLabel) {
        this.rechercheLabel = rechercheLabel;
    }

    public JTextField getRechercheTextField() {
        return rechercheTextField;
    }

    public void setRechercheTextField(JTextField rechercheTextField) {
        this.rechercheTextField = rechercheTextField;
    }

    public MembreFormulaire getMembreFormulaire() {
        return membreFormulaire;
    }

    public void setMembreFormulaire(MembreFormulaire membreFormulaire) {
        this.membreFormulaire = membreFormulaire;
    }
    public static void main(String[] args) {
		JFrame j  = new JFrame();
		j.add(new viewUsersMembres());
		j.setSize(1600, 1000);
        j.setLocationRelativeTo(null);;
		j.setVisible(true);
		
	}
    
}
