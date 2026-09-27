package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.EventObject;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class viewUsersAdministrateur extends JPanel{
		private JTable administrateursTable = new JTable(){
		    @Override
		    public boolean editCellAt(int row, int column, EventObject e) {
		        return false; 
		    }
		};
	    private DefaultTableModel administrateursModel = new DefaultTableModel() {
	        public boolean isCellEditable(int row, int column) {
	            return false;
	        }
	    };

	    private JButton modiferAdministrateur = new JButton("Modifier");
	    private JButton supprimerAdministrateur = new JButton("Supprimer");
	    private JButton ajouterAdministrateur = new JButton("Ajouter");

	    private JLabel rechercheLabel = new JLabel("Rechercher");
	    private JTextField rechercheTextField = new JTextField(30);

	    private AdministrateurFormulaire administrateurFormulaire = new AdministrateurFormulaire();

	    public viewUsersAdministrateur() {
	        ajouterComposantes();
	    }

	    public void ajouterComposantes() {
	       
	        setLayout(new BorderLayout());
	        setBorder(new TitledBorder("Gestion des Administrateurs"));

	        JPanel tablePanel = new JPanel(new BorderLayout());
	        JScrollPane tableScrollPane = new JScrollPane(administrateursTable);
	        administrateursTable.setModel(administrateursModel);
	        tablePanel.setBorder(new TitledBorder("Liste des Administrateurs"));
	        tablePanel.add(tableScrollPane, BorderLayout.CENTER);

	        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	        searchPanel.add(rechercheLabel);
	        searchPanel.add(rechercheTextField);

	        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	        buttonsPanel.add(ajouterAdministrateur);
	        buttonsPanel.add(modiferAdministrateur);
	        buttonsPanel.add(supprimerAdministrateur);

	        JPanel formPanel = new JPanel(new BorderLayout());
	        formPanel.setBorder(new TitledBorder("Formulaire"));
	        formPanel.add(administrateurFormulaire, BorderLayout.CENTER);

	        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, tablePanel, formPanel);
	        splitPane.setDividerLocation(1000);

	        add(searchPanel, BorderLayout.NORTH);
	        add(splitPane, BorderLayout.CENTER);
	        add(buttonsPanel, BorderLayout.SOUTH);
	    }

	    public void setModelAdministrateursTable(DefaultTableModel model) {
	    	administrateursModel = model;
	        this.administrateursTable.setModel(model);
	    }

		public JTable getAdministrateursTable() {
			return administrateursTable;
		}

		public void setAdministrateursTable(JTable administrateursTable) {
			this.administrateursTable = administrateursTable;
		}

		public DefaultTableModel getAdministrateursModel() {
			return administrateursModel;
		}

		public void setAdministrateursModel(DefaultTableModel administrateursModel) {
			this.administrateursModel = administrateursModel;
		}

		public JButton getModiferAdministrateur() {
			return modiferAdministrateur;
		}

		public void setModiferAdministrateur(JButton modiferAdministrateur) {
			this.modiferAdministrateur = modiferAdministrateur;
		}

		public JButton getSupprimerAdministrateur() {
			return supprimerAdministrateur;
		}

		public void setSupprimerAdministrateur(JButton supprimerAdministrateur) {
			this.supprimerAdministrateur = supprimerAdministrateur;
		}

		public JButton getAjouterAdministrateur() {
			return ajouterAdministrateur;
		}

		public void setAjouterAdministrateur(JButton ajouterAdministrateur) {
			this.ajouterAdministrateur = ajouterAdministrateur;
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

		public AdministrateurFormulaire getAdministrateurFormulaire() {
			return administrateurFormulaire;
		}

		public void setAdministrateurFormulaire(AdministrateurFormulaire administrateurFormulaire) {
			this.administrateurFormulaire = administrateurFormulaire;
		}
	    
		public static void main(String[] args) {
			JFrame j  = new JFrame();
			j.add(new viewUsersAdministrateur());
			j.setSize(1600, 1000);
	        j.setLocationRelativeTo(null);;
			j.setVisible(true);
			
		}
	    

}
