package view;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import com.toedter.calendar.JCalendar;
import model.*;

import java.awt.*;
import java.util.List; 

public class EmprunterLivreFormulaire extends JPanel {

    private JTextField idEmpruntTextField = new JTextField(20);
    private JLabel idEmpruntLabel = new JLabel("ID");
    
    private JTextField prixTextField = new JTextField(20);
    private JTextField penaliteTextField = new JTextField(20);
    private JLabel prixLabel = new JLabel("prix");
    private JLabel penaliteLabel = new JLabel("penalité");

    private JComboBox<String> listeMembres = new JComboBox<>();
    private JComboBox<String> listeLivres = new JComboBox<>();
    private JCalendar dateEmprunt = new JCalendar();
    
    private JCalendar dateRetour = new JCalendar();

    public EmprunterLivreFormulaire() {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(),
                "Formulaire Pour Emprunter un Livre",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 16),
                Color.DARK_GRAY
        ));
        ajouterComposantes();

        idEmpruntTextField.setEditable(false);
        idEmpruntTextField.setBackground(Color.LIGHT_GRAY);
        prixTextField.setEditable(false);
        prixTextField.setBackground(Color.LIGHT_GRAY);
        penaliteTextField.setEditable(false);
        penaliteTextField.setBackground(Color.LIGHT_GRAY);
        AutoCompleteDecorator.decorate(listeMembres);
        AutoCompleteDecorator.decorate(listeLivres);
    }

    private void ajouterComposantes() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL; 
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2; 
        listeMembres.setBorder(BorderFactory.createTitledBorder("Liste des Membres"));
        add(listeMembres, gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        listeLivres.setBorder(BorderFactory.createTitledBorder("Liste des Livres"));
        add(listeLivres, gbc);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.weightx = 1.0; 
        //add(dateEmprunt, gbc);
        dateRetour.setBorder(BorderFactory.createTitledBorder("Date de Retour"));
        gbc.gridx = 0;
        gbc.gridy = 6;
        add(dateRetour, gbc);
    }

    public JTextField getPrixTextField() {
		return prixTextField;
	}

	public void setPrixTextField(JTextField prixTextField) {
		this.prixTextField = prixTextField;
	}

	public JTextField getPenaliteTextField() {
		return penaliteTextField;
	}

	public void setPenaliteTextField(JTextField penaliteTextField) {
		this.penaliteTextField = penaliteTextField;
	}

	public JLabel getPrixLabel() {
		return prixLabel;
	}

	public void setPrixLabel(JLabel prixLabel) {
		this.prixLabel = prixLabel;
	}

	public JLabel getPenaliteLabel() {
		return penaliteLabel;
	}

	public void setPenaliteLabel(JLabel penaliteLabel) {
		this.penaliteLabel = penaliteLabel;
	}

    public JTextField getIdEmpruntTextField() {
        return idEmpruntTextField;
    }

    public void setIdEmpruntTextField(JTextField idEmpruntTextField) {
        this.idEmpruntTextField = idEmpruntTextField;
    }

    public JLabel getIdEmpruntLabel() {
        return idEmpruntLabel;
    }

    public void setIdEmpruntLabel(JLabel idEmpruntLabel) {
        this.idEmpruntLabel = idEmpruntLabel;
    }

    public JComboBox<String> getListeMembres() {
        return listeMembres;
    }

    public void setListeMembres(List<MembreModel> list) {
    	this.listeMembres.removeAllItems();

        
        for (MembreModel membre : list) {
            String displayText =  membre.getNom()+" "+membre.getPrenom()+ " ("+membre.getIdMembre()+ ")";  
            this.listeMembres.addItem(displayText);  
      }
    }

    public JComboBox<String> getListeLivres() {
        return listeLivres;
    }

    public void setListeLivres(List<LivreModel> listeLivres) {
        
        this.listeLivres.removeAllItems();

        
        for (LivreModel livre : listeLivres) {
            String displayText = livre.getTitre()+" ("+livre.getIdLivre() +") ";  
            this.listeLivres.addItem(displayText);  
      }
    }

    public JCalendar getDateEmprunt() {
        return dateEmprunt;
    }

    public void setDateEmprunt(JCalendar dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    public JCalendar getDateRetour() {
        return dateRetour;
    }

    public void setDateRetour(JCalendar dateRetour) {
        this.dateRetour = dateRetour;
    }
    
    public static void main(String[] args) {
		JFrame j  = new JFrame();
		j.add(new EmprunterLivreFormulaire());
		j.setSize(1600, 1000);
        j.setLocationRelativeTo(null);;
		j.setVisible(true);
		
	}
}
