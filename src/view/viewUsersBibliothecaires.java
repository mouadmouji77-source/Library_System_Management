package view;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.util.EventObject;

public class viewUsersBibliothecaires extends JPanel {

	private JTable bibliothecairesTable = new JTable(){
	    @Override
	    public boolean editCellAt(int row, int column, EventObject e) {
	        return false; 
	    }
	};
    private DefaultTableModel bibliothecairesModel = new DefaultTableModel() {
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    private JButton modiferBibliothecaire = new JButton("Modifier");
    private JButton supprimerBibliothecaire = new JButton("Supprimer");
    private JButton ajouterBibliothecaire = new JButton("Ajouter");

    private JLabel rechercheLabel = new JLabel("Rechercher");
    private JTextField rechercheTextField = new JTextField(30);

    private BibliothecaireFormulaire bibliothecaireFormulaire = new BibliothecaireFormulaire();
    
    public viewUsersBibliothecaires() {
    	ajouterComposantes();
    }
    
    public void ajouterComposantes() {
       
        setLayout(new BorderLayout());
        setBorder(new TitledBorder("Gestion des Membres"));
        JPanel tablePanel = new JPanel(new BorderLayout());
        JScrollPane tableScrollPane = new JScrollPane(bibliothecairesTable);
        bibliothecairesTable.setModel(bibliothecairesModel);
        tablePanel.setBorder(new TitledBorder("Liste des Bibliothecaires"));
        tablePanel.add(tableScrollPane, BorderLayout.CENTER);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.add(rechercheLabel);
        searchPanel.add(rechercheTextField);

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonsPanel.add(ajouterBibliothecaire);
        buttonsPanel.add(modiferBibliothecaire);
        buttonsPanel.add(supprimerBibliothecaire);

        JPanel formPanel = new JPanel(new BorderLayout());
        formPanel.setBorder(new TitledBorder("Formulaire"));
        formPanel.add(bibliothecaireFormulaire, BorderLayout.CENTER);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, tablePanel, formPanel);
        splitPane.setDividerLocation(1000); 

        add(searchPanel, BorderLayout.NORTH);
        add(splitPane, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);
    }

    
    public void setModelBibliothecairesTable(DefaultTableModel model) {
    	bibliothecairesModel = model;
        this.bibliothecairesTable.setModel(model);
    }
    
	public JTable getBibliothecairesTable() {
		return bibliothecairesTable;
	}

	public void setBibliothecairesTable(JTable bibliothecairesTable) {
		this.bibliothecairesTable = bibliothecairesTable;
	}

	public DefaultTableModel getBibliothecairesModel() {
		return bibliothecairesModel;
	}

	public void setBibliothecairesModel(DefaultTableModel bibliothecairesModel) {
		this.bibliothecairesModel = bibliothecairesModel;
	}

	public JButton getModiferBibliothecaire() {
		return modiferBibliothecaire;
	}

	public void setModiferBibliothecaire(JButton modiferBibliothecaire) {
		this.modiferBibliothecaire = modiferBibliothecaire;
	}

	public JButton getSupprimerBibliothecaire() {
		return supprimerBibliothecaire;
	}

	public void setSupprimerBibliothecaire(JButton supprimerBibliothecaire) {
		this.supprimerBibliothecaire = supprimerBibliothecaire;
	}

	public JButton getAjouterBibliothecaire() {
		return ajouterBibliothecaire;
	}

	public void setAjouterBibliothecaire(JButton ajouterBibliothecaire) {
		this.ajouterBibliothecaire = ajouterBibliothecaire;
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

	public BibliothecaireFormulaire getBibliothecaireFormulaire() {
		return bibliothecaireFormulaire;
	}

	public void setBibliothecaireFormulaire(BibliothecaireFormulaire bibliothecaireFormulaire) {
		this.bibliothecaireFormulaire = bibliothecaireFormulaire;
	}
    
	public static void main(String[] args) {
		JFrame j  = new JFrame();
		j.add(new viewUsersBibliothecaires());
		j.setSize(1600, 1000);
        j.setLocationRelativeTo(null);;
		j.setVisible(true);
		
	}
    

}
