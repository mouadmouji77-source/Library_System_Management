package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.EventObject;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.LivreEmprunte;

public class EmpruntsTabView extends JPanel{
	private EmprunterLivreFormulaire emprunterLivreFormulaire = new EmprunterLivreFormulaire();
	private JTable listeEmpruntesTable = new JTable(){
        @Override
        public boolean editCellAt(int row, int column, EventObject e) {
            return false; // Prevent cell editing
        }
    };
    private JTable listeEmpruntesHistoriqueTable = new JTable(){
        @Override
        public boolean editCellAt(int row, int column, EventObject e) {
            return false; // Prevent cell editing
        }
    };
    private JTextField rechercherEmprunt = new JTextField(30);
    private JTextField rechercherEmpruntHistorique = new JTextField(30);
    private JButton ajouterEmprunt = new JButton("Emprunter");
    private JButton supprimerEmprunt = new JButton("Supprimer ");
    private JButton modifierEmprunt = new JButton("Modifier ");
    private JButton modifierDateRetourEmprunt = new JButton("Prolonger");
    private JButton finEmprunt = new JButton("Fin d'emprunt");
    private JButton genererPDf = new JButton("Générer Reçu");
    
    private JButton emprunterLivre = new JButton("Emprunter Livre");
    
    public EmpruntsTabView() {
    	setLayout(new BorderLayout(10, 10));
    	setBorder(new EmptyBorder(05, 05, 05, 05));
    	ajouterComposantes();
    }
    
    public void ajouterComposantes() {
    	JPanel rechercherBar2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        rechercherBar2.add(rechercherEmprunt);
        rechercherBar2.setBorder(BorderFactory.createTitledBorder("Recherche un Livre déja emprunté"));
        
        JPanel btnsEmprunt = new JPanel(new GridLayout(1, 4, 20, 20));
        btnsEmprunt.add(supprimerEmprunt);
        btnsEmprunt.add(modifierDateRetourEmprunt);
        btnsEmprunt.add(finEmprunt);
        btnsEmprunt.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
    
        JPanel pTableAndRechercheEmprunt = new JPanel(new BorderLayout()); 
        JScrollPane tableEmprunteScrollPane = new JScrollPane(listeEmpruntesTable);
        tableEmprunteScrollPane.setBorder(BorderFactory.createTitledBorder("Les Livres Empruntés"));
        
        pTableAndRechercheEmprunt.add(rechercherBar2, BorderLayout.NORTH); 
        pTableAndRechercheEmprunt.add(tableEmprunteScrollPane, BorderLayout.CENTER); 
        pTableAndRechercheEmprunt.add(btnsEmprunt, BorderLayout.SOUTH); 
        pTableAndRechercheEmprunt.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 10));
        JTabbedPane paneEmprunte = new JTabbedPane();
        
        add(new JScrollPane(paneEmprunte));
        
        JPanel pEmprunterFormulaire = new JPanel();
        pEmprunterFormulaire.setLayout(new BorderLayout());
        pEmprunterFormulaire.add(new JScrollPane(emprunterLivreFormulaire), BorderLayout.CENTER);
        pEmprunterFormulaire.add(ajouterEmprunt, BorderLayout.SOUTH);
        pEmprunterFormulaire.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 10));

        JPanel pHistoriqueEmprunte = new JPanel(new BorderLayout());
        JPanel rechercherBar3 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        rechercherBar3.add(rechercherEmpruntHistorique);
        rechercherBar3.setBorder(BorderFactory.createTitledBorder("Recherche dans l'historique des emprunts"));

        pHistoriqueEmprunte.add(rechercherBar3, BorderLayout.NORTH); 
        pHistoriqueEmprunte.add(new JScrollPane(listeEmpruntesHistoriqueTable), BorderLayout.CENTER); 
        pHistoriqueEmprunte.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 10));
        
        JPanel btnsPDF = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnsPDF.add(genererPDf);
        pHistoriqueEmprunte.add(btnsPDF, BorderLayout.SOUTH);
        
        
        paneEmprunte.addTab("Emprunter Livre", pEmprunterFormulaire);
        paneEmprunte.addTab("Details", pTableAndRechercheEmprunt);
        paneEmprunte.addTab("Historique", pHistoriqueEmprunte);
        
    }

	public EmprunterLivreFormulaire getEmprunterLivreFormulaire() {
		return emprunterLivreFormulaire;
	}

	public void setEmprunterLivreFormulaire(EmprunterLivreFormulaire emprunterLivreFormulaire) {
		this.emprunterLivreFormulaire = emprunterLivreFormulaire;
	}

	public JTable getListeEmpruntesTable() {
		return listeEmpruntesTable;
	}

	public void setListeEmpruntesTable(JTable listeEmpruntesTable) {
		this.listeEmpruntesTable = listeEmpruntesTable;
	}

	public JTable getListeEmpruntesHistoriqueTable() {
		return listeEmpruntesHistoriqueTable;
	}

	public void setListeEmpruntesHistoriqueTable(JTable listeEmpruntesHistoriqueTable) {
		this.listeEmpruntesHistoriqueTable = listeEmpruntesHistoriqueTable;
	}

	public JTextField getRechercherEmprunt() {
		return rechercherEmprunt;
	}

	public void setRechercherEmprunt(JTextField rechercherEmprunt) {
		this.rechercherEmprunt = rechercherEmprunt;
	}

	public JTextField getRechercherEmpruntHistorique() {
		return rechercherEmpruntHistorique;
	}

	public void setRechercherEmpruntHistorique(JTextField rechercherEmpruntHistorique) {
		this.rechercherEmpruntHistorique = rechercherEmpruntHistorique;
	}

	public JButton getAjouterEmprunt() {
		return ajouterEmprunt;
	}

	public void setAjouterEmprunt(JButton ajouterEmprunt) {
		this.ajouterEmprunt = ajouterEmprunt;
	}

	public JButton getSupprimerEmprunt() {
		return supprimerEmprunt;
	}

	public void setSupprimerEmprunt(JButton supprimerEmprunt) {
		this.supprimerEmprunt = supprimerEmprunt;
	}

	public JButton getModifierEmprunt() {
		return modifierEmprunt;
	}

	public void setModifierEmprunt(JButton modifierEmprunt) {
		this.modifierEmprunt = modifierEmprunt;
	}

	public JButton getModifierDateRetourEmprunt() {
		return modifierDateRetourEmprunt;
	}

	public void setModifierDateRetourEmprunt(JButton modifierDateRetourEmprunt) {
		this.modifierDateRetourEmprunt = modifierDateRetourEmprunt;
	}

	public JButton getFinEmprunt() {
		return finEmprunt;
	}

	public void setFinEmprunt(JButton finEmprunt) {
		this.finEmprunt = finEmprunt;
	}

	public JButton getEmprunterLivre() {
		return emprunterLivre;
	}

	public void setEmprunterLivre(JButton emprunterLivre) {
		this.emprunterLivre = emprunterLivre;
	}

	public JButton getGenererPDf() {
		return genererPDf;
	}

	public void setGenererPDf(JButton genererPDf) {
		this.genererPDf = genererPDf;
	}
	
	public static void main(String[] args) {
		JFrame j  = new JFrame();
		j.add(new EmpruntsTabView());
		j.setSize(1600, 1000);
        j.setLocationRelativeTo(null);;
		j.setVisible(true);
		
	}
}
