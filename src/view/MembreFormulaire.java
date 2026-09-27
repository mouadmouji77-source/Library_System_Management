package view;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class MembreFormulaire extends JPanel {

    private JTextField nomTextField = new JTextField(20);
    private JTextField prenomTextField = new JTextField(20);
    private JTextField emailTextField = new JTextField(20);
    private JTextField idMembreTextField = new JTextField(20);
    private JTextField roleTextField = new JTextField(20);
    private JTextField cinTextField = new JTextField(20);
    
    private JLabel nomLabel = new JLabel("Nom");
    private JLabel prenomLabel = new JLabel("Prénom");
    private JLabel emailLabel = new JLabel("Email");
    private JLabel idMembreLabel = new JLabel("ID Membre");
    private JLabel roleLabel = new JLabel("Role");
    private JLabel cinLabel = new JLabel("CIN");
    
    public MembreFormulaire() {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Formulaire de Membre",
                TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.BOLD, 16), Color.LIGHT_GRAY));

        ajouterComposantes();

        idMembreTextField.setEditable(false);
        idMembreTextField.setBackground(Color.LIGHT_GRAY);

        roleTextField.setEditable(false);
        roleTextField.setBackground(Color.LIGHT_GRAY);
        roleTextField.setText("Membre");
    }

    private void ajouterComposantes() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        addRow(gbc, 0, idMembreLabel, idMembreTextField);
        addRow(gbc, 1, nomLabel, nomTextField);
        addRow(gbc, 2, prenomLabel, prenomTextField);
        addRow(gbc, 3, emailLabel, emailTextField);
        addRow(gbc, 4, cinLabel, cinTextField);
        addRow(gbc, 5, roleLabel, roleTextField);
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

    public JTextField getEmailTextField() {
        return emailTextField;
    }

    public void setEmailTextField(JTextField emailTextField) {
        this.emailTextField = emailTextField;
    }

    public JTextField getIdMembreTextField() {
        return idMembreTextField;
    }

    public void setIdMembreTextField(JTextField idMembreTextField) {
        this.idMembreTextField = idMembreTextField;
    }

    public JTextField getRoleTextField() {
        return roleTextField;
    }

    public void setRoleTextField(JTextField roleTextField) {
        this.roleTextField = roleTextField;
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

    public JLabel getEmailLabel() {
        return emailLabel;
    }

    public void setEmailLabel(JLabel emailLabel) {
        this.emailLabel = emailLabel;
    }

    public JLabel getIdMembreLabel() {
        return idMembreLabel;
    }

    public void setIdMembreLabel(JLabel idMembreLabel) {
        this.idMembreLabel = idMembreLabel;
    }

    public JLabel getRoleLabel() {
        return roleLabel;
    }

    public void setRoleLabel(JLabel roleLabel) {
        this.roleLabel = roleLabel;
    }

	public JTextField getCinTextField() {
		return cinTextField;
	}

	public void setCinTextField(JTextField cinTextField) {
		this.cinTextField = cinTextField;
	}

	public JLabel getCinLabel() {
		return cinLabel;
	}

	public void setCinLabel(JLabel cinLabel) {
		this.cinLabel = cinLabel;
	}
	
	public static void main(String[] args) {
		JFrame j  = new JFrame();
		j.add(new MembreFormulaire());
		j.setSize(1600, 1000);
        j.setLocationRelativeTo(null);;
		j.setVisible(true);
		
	}
}
