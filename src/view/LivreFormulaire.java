package view;

import javax.swing.text.NumberFormatter;

import java.awt.*;
import java.text.NumberFormat;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class LivreFormulaire extends JPanel{

	
	private JTextField titreLivreTextField = new JTextField(20);
	private JTextField auteurLivreTextField = new JTextField(20);
	private JTextField editeurLivreTextField = new JTextField(20);
	private JTextField idLivreTextField = new JTextField(20);
	private JTextField quantiteLivreTextField = new JTextField(20);
    private JTextField datePublicationLivreTextField = new JTextField(20);
	private JTextField categorieLivreTextField = new JTextField(20);
	private JTextField langueLivreTextField = new JTextField(20);
	private JTextField prixLivreTextField = new JTextField(20);
	
	
	
	private JLabel titreLivreLabel = new JLabel("Titre");
	private JLabel auteurLivreLabel = new JLabel("Auteur");
	private JLabel editeurLivreLabel = new JLabel("Editeur");
	private JLabel idLivreLabel = new JLabel("ID Livre");
	private JLabel datePublicationLivreLabel = new JLabel("Année de publication");
	private JLabel categorieLivreLabel = new JLabel("Catégorie");
	private JLabel langueLivreLabel = new JLabel("Langue");
	private JLabel quantiteLivreLabel = new JLabel("Quantité");
	private JLabel prixLivreLabel = new JLabel("Prix d'emprunt");
	

	
	public LivreFormulaire() {
		setLayout(new GridBagLayout());
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Formulaire de Livre",
				TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.BOLD, 16), Color.BLUE));

		ajouterComposantes();
		idLivreTextField.setEditable(false);
		idLivreTextField.setBackground(Color.LIGHT_GRAY);
	}

	private void ajouterComposantes() {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5); 
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.WEST;
		addRow(gbc, 0, idLivreLabel, idLivreTextField);
		addRow(gbc, 1, titreLivreLabel, titreLivreTextField);
		addRow(gbc, 2, auteurLivreLabel, auteurLivreTextField);
		addRow(gbc, 3, editeurLivreLabel, editeurLivreTextField);
		addRow(gbc, 4, datePublicationLivreLabel, datePublicationLivreTextField);
		addRow(gbc, 5, categorieLivreLabel, categorieLivreTextField);
		addRow(gbc, 6, langueLivreLabel, langueLivreTextField);
		addRow(gbc, 7, quantiteLivreLabel, quantiteLivreTextField);
		addRow(gbc, 8, prixLivreLabel, prixLivreTextField);
	}

	public JTextField getPrixLivreTextField() {
		return prixLivreTextField;
	}

	public void setPrixLivreTextField(JTextField prixLivreTextField) {
		this.prixLivreTextField = prixLivreTextField;
	}

	public JLabel getPrixLivreLabel() {
		return prixLivreLabel;
	}

	public void setPrixLivreLabel(JLabel prixLivreLabel) {
		this.prixLivreLabel = prixLivreLabel;
	}

	private void addRow(GridBagConstraints gbc, int row, JLabel label, JTextField textField) {
		
		gbc.gridx = 0;
		gbc.gridy = row;
		gbc.weightx = 0.3;
		add(label, gbc);
		gbc.gridx = 1;
		gbc.weightx = 0.7;
		add(textField, gbc);
	}


	public JTextField getTitreLivreTextField() {
		return titreLivreTextField;
	}


	public void setTitreLivreTextField(JTextField titreLivreTextField) {
		this.titreLivreTextField = titreLivreTextField;
	}


	public JTextField getAuteurLivreTextField() {
		return auteurLivreTextField;
	}


	public void setAuteurLivreTextField(JTextField auteurLivreTextField) {
		this.auteurLivreTextField = auteurLivreTextField;
	}


	public JTextField getEditeurLivreTextField() {
		return editeurLivreTextField;
	}


	public void setEditeurLivreTextField(JTextField editeurLivreTextField) {
		this.editeurLivreTextField = editeurLivreTextField;
	}


	public JTextField getIdLivreTextField() {
		return idLivreTextField;
	}


	public void setIdLivreTextField(JTextField idLivreTextField) {
		this.idLivreTextField = idLivreTextField;
	}


	public JTextField getDatePublicationLivreTextField() {
		return datePublicationLivreTextField;
	}


	public void setDatePublicationLivreTextField(JTextField datePublicationLivreTextField) {
		this.datePublicationLivreTextField = datePublicationLivreTextField;
	}


	public JTextField getCategorieLivreTextField() {
		return categorieLivreTextField;
	}


	public void setCategorieLivreTextField(JTextField categorieLivreTextField) {
		this.categorieLivreTextField = categorieLivreTextField;
	}


	public JTextField getLangueLivreTextField() {
		return langueLivreTextField;
	}


	public void setLangueLivreTextField(JTextField langueLivreTextField) {
		this.langueLivreTextField = langueLivreTextField;
	}


	public JTextField getQuantiteLivreTextField() {
		return quantiteLivreTextField;
	}


	public void setQuantiteLivreTextField(JTextField quantiteLivreTextField) {
		this.quantiteLivreTextField = quantiteLivreTextField;
	}


	public JLabel getTitreLivreLabel() {
		return titreLivreLabel;
	}


	public void setTitreLivreLabel(JLabel titreLivreLabel) {
		this.titreLivreLabel = titreLivreLabel;
	}


	public JLabel getAuteurLivreLabel() {
		return auteurLivreLabel;
	}


	public void setAuteurLivreLabel(JLabel auteurLivreLabel) {
		this.auteurLivreLabel = auteurLivreLabel;
	}


	public JLabel getEditeurLivreLabel() {
		return editeurLivreLabel;
	}


	public void setEditeurLivreLabel(JLabel editeurLivreLabel) {
		this.editeurLivreLabel = editeurLivreLabel;
	}


	public JLabel getIdLivreLabel() {
		return idLivreLabel;
	}


	public void setIdLivreLabel(JLabel idLivreLabel) {
		this.idLivreLabel = idLivreLabel;
	}


	public JLabel getDatePublicationLivreLabel() {
		return datePublicationLivreLabel;
	}


	public void setDatePublicationLivreLabel(JLabel datePublicationLivreLabel) {
		this.datePublicationLivreLabel = datePublicationLivreLabel;
	}


	public JLabel getCategorieLivreLabel() {
		return categorieLivreLabel;
	}


	public void setCategorieLivreLabel(JLabel categorieLivreLabel) {
		this.categorieLivreLabel = categorieLivreLabel;
	}


	public JLabel getLangueLivreLabel() {
		return langueLivreLabel;
	}


	public void setLangueLivreLabel(JLabel langueLivreLabel) {
		this.langueLivreLabel = langueLivreLabel;
	}


	public JLabel getQuantiteLivreLabel() {
		return quantiteLivreLabel;
	}


	public void setQuantiteLivreLabel(JLabel quantiteLivreLabel) {
		this.quantiteLivreLabel = quantiteLivreLabel;
	}
	
	public static void main(String[] args) {
		JFrame j  = new JFrame();
		j.add(new LivreFormulaire());
		j.setSize(1600, 1000);
        j.setLocationRelativeTo(null);;
		j.setVisible(true);
		
	}
	
}
