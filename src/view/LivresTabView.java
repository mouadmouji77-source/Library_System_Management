package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.EventObject;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class LivresTabView extends JPanel{
	private JTable listeLivresTable = new JTable() {
        @Override
        public boolean editCellAt(int row, int column, EventObject e) {
            return false; 
        }
    };
	
	private JPanel rechercherBar1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
	private JPanel btnsLivre = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	
	private JButton ajouterLivre = new JButton("Ajouter Livre");
    private JButton supprimerLivre = new JButton("Supprimer Livre");
    private JButton modifierLivre = new JButton("Modifier Livre");
    
	private JTextField rechercherLivre = new JTextField(20);
	
	private LivreFormulaire livreFormulaire = new LivreFormulaire();
	
	public LivresTabView() {
		setLayout(new BorderLayout(10, 10));
    	setBorder(new EmptyBorder(05, 05, 05, 05));
    	ajouterComposantes();
	}
	 public void ajouterComposantes() {
		 
		 rechercherBar1.add(rechercherLivre);
		 rechercherBar1.setBorder(BorderFactory.createTitledBorder("Recherche Livre"));
		
		 btnsLivre.add(ajouterLivre);
	     btnsLivre.add(modifierLivre);
	     btnsLivre.add(supprimerLivre);
	     btnsLivre.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	     
	     
	     JPanel pTableAndRecherche = new JPanel(new BorderLayout(10, 10)); 
	     pTableAndRecherche.add(rechercherBar1, BorderLayout.NORTH); 
	     pTableAndRecherche.add(new JScrollPane(listeLivresTable), BorderLayout.CENTER); 
	
	     
	     JPanel formPanel = new JPanel(new BorderLayout());
	     formPanel.setBorder(new TitledBorder("Formulaire"));
	     formPanel.add(livreFormulaire, BorderLayout.CENTER);
	     
	     JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pTableAndRecherche, formPanel);
	     splitPane.setDividerLocation(1000);
	     
	     add(splitPane, BorderLayout.CENTER);
	     add(btnsLivre, BorderLayout.SOUTH);

		 
	 }
	public JTable getListeLivresTable() {
		return listeLivresTable;
	}
	public void setListeLivresTable(JTable listeLivresTable) {
		this.listeLivresTable = listeLivresTable;
	}
	public JPanel getRechercherBar1() {
		return rechercherBar1;
	}
	public void setRechercherBar1(JPanel rechercherBar1) {
		this.rechercherBar1 = rechercherBar1;
	}
	public JPanel getBtnsLivre() {
		return btnsLivre;
	}
	public void setBtnsLivre(JPanel btnsLivre) {
		this.btnsLivre = btnsLivre;
	}
	public JButton getAjouterLivre() {
		return ajouterLivre;
	}
	public void setAjouterLivre(JButton ajouterLivre) {
		this.ajouterLivre = ajouterLivre;
	}
	public JButton getSupprimerLivre() {
		return supprimerLivre;
	}
	public void setSupprimerLivre(JButton supprimerLivre) {
		this.supprimerLivre = supprimerLivre;
	}
	public JButton getModifierLivre() {
		return modifierLivre;
	}
	public void setModifierLivre(JButton modifierLivre) {
		this.modifierLivre = modifierLivre;
	}
	public JTextField getRechercherLivre() {
		return rechercherLivre;
	}
	public void setRechercherLivre(JTextField rechercherLivre) {
		this.rechercherLivre = rechercherLivre;
	}
	public LivreFormulaire getLivreFormulaire() {
		return livreFormulaire;
	}
	public void setLivreFormulaire(LivreFormulaire livreFormulaire) {
		this.livreFormulaire = livreFormulaire;
	}
	 
	public static void main(String[] args) {
		JFrame j  = new JFrame();
		j.add(new LivresTabView());
		j.setSize(1600, 1000);
        j.setLocationRelativeTo(null);;
		j.setVisible(true);
		
	}
	
}
