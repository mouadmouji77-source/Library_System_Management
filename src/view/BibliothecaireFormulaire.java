package view;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class BibliothecaireFormulaire extends JPanel {

	private JTextField idTextField = new JTextField(20);
    private JTextField nomTextField = new JTextField(20);
    private JTextField prenomTextField = new JTextField(20);
    private JTextField adresseTextField = new JTextField(20);
    private JTextField emailTextField = new JTextField(20);
    private JTextField telephoneTextField = new JTextField(20);
    
    private JPasswordField motDePasseField = new JPasswordField(20);
    private JTextField roleTextField = new JTextField("Bibliothécaire", 20);

    private JLabel idLabel = new JLabel("ID");
    private JLabel nomLabel = new JLabel("Nom");
    private JLabel prenomLabel = new JLabel("Prénom");
    private JLabel adresseLabel = new JLabel("Adresse");
    private JLabel emailLabel = new JLabel("Email");
    private JLabel telephoneLabel = new JLabel("Téléphone");
    private JLabel motDePasseLabel = new JLabel("Mot de passe");
    private JLabel roleLabel = new JLabel("Role");

    public BibliothecaireFormulaire() {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(),
                "Formulaire de Bibliothécaire",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 16),
                Color.ORANGE
        ));

        ajouterComposantes();

        telephoneTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE) {
                    e.consume(); 
                }
            }
        });
        roleTextField.setEditable(false);
        roleTextField.setBackground(Color.LIGHT_GRAY);
        idTextField.setEditable(false);
        idTextField.setBackground(Color.LIGHT_GRAY);
    }

    private void ajouterComposantes() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); 
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        addRow(gbc, 0, idLabel, idTextField);
        addRow(gbc, 1, nomLabel, nomTextField);
        addRow(gbc, 2, prenomLabel, prenomTextField);
        addRow(gbc, 3, adresseLabel, adresseTextField);
        addRow(gbc, 4, emailLabel, emailTextField);
        addRow(gbc, 5, telephoneLabel, telephoneTextField);
        addRow(gbc, 6, motDePasseLabel, motDePasseField);
        addRow(gbc, 7, roleLabel, roleTextField);
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

	public JTextField getIdTextField() {
		return idTextField;
	}

	public void setIdTextField(JTextField idTextField) {
		this.idTextField = idTextField;
	}

	public JTextField getNomTextField() {
		return nomTextField;
	}

	public void setNomTextField(JTextField nomTextField) {
		this.nomTextField = nomTextField;
	}

	public JTextField getPrenomTextField() {
		return prenomTextField;
	}

	public void setPrenomTextField(JTextField prenomTextField) {
		this.prenomTextField = prenomTextField;
	}

	public JTextField getAdresseTextField() {
		return adresseTextField;
	}

	public void setAdresseTextField(JTextField adresseTextField) {
		this.adresseTextField = adresseTextField;
	}

	public JTextField getEmailTextField() {
		return emailTextField;
	}

	public void setEmailTextField(JTextField emailTextField) {
		this.emailTextField = emailTextField;
	}

	public JTextField getTelephoneTextField() {
		return telephoneTextField;
	}

	public void setTelephoneTextField(JTextField telephoneTextField) {
		this.telephoneTextField = telephoneTextField;
	}

	public JPasswordField getMotDePasseField() {
		return motDePasseField;
	}

	public void setMotDePasseField(JPasswordField motDePasseField) {
		this.motDePasseField = motDePasseField;
	}

	public JTextField getRoleTextField() {
		return roleTextField;
	}

	public void setRoleTextField(JTextField roleTextField) {
		this.roleTextField = roleTextField;
	}

	public JLabel getIdLabel() {
		return idLabel;
	}

	public void setIdLabel(JLabel idLabel) {
		this.idLabel = idLabel;
	}

	public JLabel getNomLabel() {
		return nomLabel;
	}

	public void setNomLabel(JLabel nomLabel) {
		this.nomLabel = nomLabel;
	}

	public JLabel getPrenomLabel() {
		return prenomLabel;
	}

	public void setPrenomLabel(JLabel prenomLabel) {
		this.prenomLabel = prenomLabel;
	}

	public JLabel getAdresseLabel() {
		return adresseLabel;
	}

	public void setAdresseLabel(JLabel adresseLabel) {
		this.adresseLabel = adresseLabel;
	}

	public JLabel getEmailLabel() {
		return emailLabel;
	}

	public void setEmailLabel(JLabel emailLabel) {
		this.emailLabel = emailLabel;
	}

	public JLabel getTelephoneLabel() {
		return telephoneLabel;
	}

	public void setTelephoneLabel(JLabel telephoneLabel) {
		this.telephoneLabel = telephoneLabel;
	}

	public JLabel getMotDePasseLabel() {
		return motDePasseLabel;
	}

	public void setMotDePasseLabel(JLabel motDePasseLabel) {
		this.motDePasseLabel = motDePasseLabel;
	}

	public JLabel getRoleLabel() {
		return roleLabel;
	}

	public void setRoleLabel(JLabel roleLabel) {
		this.roleLabel = roleLabel;
	}

	
	
    
}
