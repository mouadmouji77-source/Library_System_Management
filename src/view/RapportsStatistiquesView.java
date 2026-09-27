package view;

import java.awt.*;
import java.util.EventObject;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class RapportsStatistiquesView extends  JPanel {
	
	private JTable topMembresTable = new JTable(new DefaultTableModel(new Object[]{"Top 5 des Membres Actifs"}, 0)){
	    @Override
	    public boolean editCellAt(int row, int column, EventObject e) {
	        return false; // Prevent cell editing
	    }
	};
	private JTable topLivresTable = new JTable(new DefaultTableModel(new Object[]{"Top 5 des Livres Empruntés"}, 0)){
	    @Override
	    public boolean editCellAt(int row, int column, EventObject e) {
	        return false; // Prevent cell editing
	    }
	};
	private JLabel nombreMembres = new JLabel("Nombre des Membres");
	private JLabel nombreBibliothecaires = new JLabel("Nombre des Bibliothécaires");
	private JLabel nombreAdministrateurs = new JLabel("Nombre des Administrateurs");
	private JLabel nombreLivres = new JLabel("Nombre des Livres");
	private JLabel topMembres = new JLabel("Top 5 des membres actifs");
	private JLabel topEmpruntes = new JLabel("Top 5 des Livres Empruntés");
	
	
	public RapportsStatistiquesView() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		ajouterComposantes();
		
	}
	
	public void ajouterComposantes() {
		

		JPanel statsPanel = new JPanel();
		statsPanel.setLayout(new GridLayout(0, 4, 10, 10)); 
		statsPanel.setBorder(BorderFactory.createTitledBorder("Statistiques"));

		JPanel PnombreMembres = new JPanel();
		PnombreMembres.setLayout(new FlowLayout(FlowLayout.LEFT));
		PnombreMembres.setBackground(Color.DARK_GRAY); 
		nombreMembres.setFont(new Font("Arial", Font.BOLD, 18));
		nombreMembres.setForeground(Color.WHITE);
		nombreMembres.setMaximumSize(new Dimension(40, 40));
		PnombreMembres.setPreferredSize(new Dimension(100, 100));  
		PnombreMembres.add(nombreMembres);
		PnombreMembres.add(new JSeparator(SwingConstants.HORIZONTAL)); 

		JPanel PnombreBibliothecaires = new JPanel();
		PnombreBibliothecaires.setLayout(new FlowLayout(FlowLayout.LEFT));
		nombreBibliothecaires.setFont(new Font("Arial", Font.BOLD, 18));
		nombreBibliothecaires.setMaximumSize(new Dimension(80, 80));
		PnombreBibliothecaires.setBackground(Color.LIGHT_GRAY);  
		PnombreBibliothecaires.setPreferredSize(new Dimension(100, 100));  
		PnombreBibliothecaires.add(nombreBibliothecaires);
		PnombreBibliothecaires.add(new JSeparator(SwingConstants.HORIZONTAL)); 

		JPanel PnombreAdministrateurs = new JPanel();
		PnombreAdministrateurs.setLayout(new FlowLayout(FlowLayout.LEFT));
		nombreAdministrateurs.setFont(new Font("Arial", Font.BOLD, 18));
		PnombreAdministrateurs.setBackground(Color.ORANGE);  
		PnombreAdministrateurs.setPreferredSize(new Dimension(100, 100)); 
		PnombreAdministrateurs.add(nombreAdministrateurs);
		PnombreAdministrateurs.add(new JSeparator(SwingConstants.HORIZONTAL)); 

		JPanel PnombreLivres = new JPanel();
		PnombreLivres.setLayout(new FlowLayout(FlowLayout.LEFT));
		nombreLivres.setFont(new Font("Arial", Font.BOLD, 18));
		PnombreLivres.setBackground(Color.YELLOW);  
		PnombreLivres.setPreferredSize(new Dimension(100, 100));  
		PnombreLivres.add(nombreLivres);
		PnombreLivres.add(new JSeparator(SwingConstants.HORIZONTAL)); 

		statsPanel.add(PnombreMembres);
		statsPanel.add(PnombreBibliothecaires);
		statsPanel.add(PnombreAdministrateurs);
		statsPanel.add(PnombreLivres);
		JPanel tablesPanel = new JPanel();
		tablesPanel.setLayout(new BoxLayout(tablesPanel, BoxLayout.Y_AXIS));
		tablesPanel.setBorder(BorderFactory.createTitledBorder("Top 5"));
		JPanel topMembresPanel = new JPanel();
		topMembresPanel.setLayout(new BorderLayout());
		topMembresPanel.setBorder(BorderFactory.createTitledBorder("Top 5 Membres Empruntés"));
		topMembresPanel.add(new JScrollPane(topMembresTable), BorderLayout.CENTER);
		tablesPanel.add(topMembresPanel);

		JPanel topLivresPanel = new JPanel();
		topLivresPanel.setLayout(new BorderLayout());
		topLivresPanel.setBorder(BorderFactory.createTitledBorder("Top 5 Livres Empruntés"));
		topLivresPanel.add(new JScrollPane(topLivresTable), BorderLayout.CENTER);
		tablesPanel.add(topLivresPanel);
		add(statsPanel, BorderLayout.NORTH);
		add(tablesPanel, BorderLayout.CENTER);

    }
	
	public void setDataForLabels(int nombreMembres,int nombreBibliothecaires,int nombreAdministrateurs,int nombreLivres,List<String> topMembres,List<String> topLivres) {
		    this.nombreMembres.setText("Nombre des Membres: " + nombreMembres);
		    this.nombreBibliothecaires.setText("Nombre des Bibliothécaires: " + nombreBibliothecaires);
		    this.nombreAdministrateurs.setText("Nombre des Administrateurs: " + nombreAdministrateurs);
		    this.nombreLivres.setText("Nombre des Livres: " + nombreLivres);
		    
		    DefaultTableModel membresModel = (DefaultTableModel) topMembresTable.getModel();
		    membresModel.setRowCount(0); 
		    for (String membre : topMembres) {
		        membresModel.addRow(new Object[]{membre});
		    }
		    
		    DefaultTableModel livresModel = (DefaultTableModel) topLivresTable.getModel();
		    livresModel.setRowCount(0);
		    for (String livre : topLivres) {
		        livresModel.addRow(new Object[]{livre});
		    }
		}

	public void setModelTopMembres(DefaultTableModel t) {
		topMembresTable.setModel(t);
	}
	
	public void setModelTopLivres(DefaultTableModel t) {
		topLivresTable.setModel(t);
	}

	public JLabel getNombreMembres() {
		return nombreMembres;
	}

	public void setNombreMembres(JLabel nombreMembres) {
		this.nombreMembres = nombreMembres;
	}

	public JLabel getNombreBibliothecaires() {
		return nombreBibliothecaires;
	}

	public void setNombreBibliothecaires(JLabel nombreBibliothecaires) {
		this.nombreBibliothecaires = nombreBibliothecaires;
	}

	public JLabel getNombreAdministrateurs() {
		return nombreAdministrateurs;
	}

	public void setNombreAdministrateurs(JLabel nombreAdministrateurs) {
		this.nombreAdministrateurs = nombreAdministrateurs;
	}

	public JLabel getNombreLivres() {
		return nombreLivres;
	}

	public void setNombreLivres(JLabel nombreLivres) {
		this.nombreLivres = nombreLivres;
	}

	public JLabel getTopMembres() {
		return topMembres;
	}

	public void setTopMembres(JLabel topMembres) {
		this.topMembres = topMembres;
	}

	public JLabel getTopEmpruntes() {
		return topEmpruntes;
	}

	public void setTopEmpruntes(JLabel topEmpruntes) {
		this.topEmpruntes = topEmpruntes;
	}
	
	public static void main(String[] args) {
		JFrame j  = new JFrame();
		j.add(new RapportsStatistiquesView());
		j.setSize(1600, 1000);
        j.setLocationRelativeTo(null);;
		j.setVisible(true);
		
	}
}
