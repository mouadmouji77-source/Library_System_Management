package view;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.table.DefaultTableModel;

public class BibliothecaireView extends JFrame{
	
	private JTabbedPane tabbedPane = new JTabbedPane();
	private UtilisateursTabView utilisateursTab = new UtilisateursTabView();
	private LivresTabView livresTab = new LivresTabView(); 
	private EmpruntsTabView empruntsTab = new EmpruntsTabView();
	private RapportsStatistiquesView rapportsTab = new RapportsStatistiquesView();
	private ImageIcon logo = new ImageIcon(getClass().getClassLoader().getResource("logo.png"));
	public BibliothecaireView() {
		this.setIconImage(logo.getImage());
        this.setTitle("Bibliothecaire");
        this.setSize(1600, 1000);
        this.setLocationRelativeTo(null);
        ajouterComposantes();
        this.setVisible(true);
        this.setMinimumSize(new Dimension(1600,1000));
    }
	
	public void ajouterComposantes() {
        this.add(tabbedPane);
        tabbedPane.addTab("Livres", livresTab);
        tabbedPane.addTab("Emprunts", empruntsTab);
        tabbedPane.addTab("Utilisateurs", utilisateursTab);
        tabbedPane.addTab("Rapports et Statistiques", rapportsTab);
        
        
        
	}
	
	

	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}

	public void setTabbedPane(JTabbedPane tabbedPane) {
		this.tabbedPane = tabbedPane;
	}

	public UtilisateursTabView getUtilisateursTab() {
		return utilisateursTab;
	}

	public void setUtilisateursTab(UtilisateursTabView utilisateursTab) {
		this.utilisateursTab = utilisateursTab;
	}

	public LivresTabView getLivresTab() {
		return livresTab;
	}

	public void setLivresTab(LivresTabView livresTab) {
		this.livresTab = livresTab;
	}

	public EmpruntsTabView getEmpruntsTab() {
		return empruntsTab;
	}

	public void setEmpruntsTab(EmpruntsTabView empruntsTab) {
		this.empruntsTab = empruntsTab;
	}

	public RapportsStatistiquesView getRapportsTab() {
		return rapportsTab;
	}

	public void setRapportsTab(RapportsStatistiquesView rapportsTab) {
		this.rapportsTab = rapportsTab;
	}
	

	public void setModelTableLivre(DefaultTableModel tableModelLivre) {
		livresTab.getListeLivresTable().setModel(tableModelLivre);
	}
	
	public void setModelTableLivreEmprunte(DefaultTableModel tableModelLivre) {
		empruntsTab.getListeEmpruntesTable().setModel(tableModelLivre);
	}
	
	public void setModelTableLivreEmprunteHistorique(DefaultTableModel tableModelLivre) {
		empruntsTab.getListeEmpruntesHistoriqueTable().setModel(tableModelLivre);
	}
	
	public void setModelTableMembresTable(DefaultTableModel tableModelLivre) {
		utilisateursTab.getTabUtilisateursMembres().setModelMembresTable(tableModelLivre);
	}
	public void setModelTableBibliothecairesTable(DefaultTableModel tableModelLivre) {
		utilisateursTab.getTabUtilisateursBibliothecaires().setModelBibliothecairesTable(tableModelLivre);
	}
	public void setModelTableAdministrateursTable(DefaultTableModel tableModelLivre) {
		utilisateursTab.getTabUtilisateursAdministrateurs().setModelAdministrateursTable(tableModelLivre);
	}
	
	
	
}


