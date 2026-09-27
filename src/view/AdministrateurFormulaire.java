package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class AdministrateurFormulaire extends JPanel {

	private JTextField idTextField = new JTextField(20);
    private JTextField nomTextField = new JTextField(20);
    private JTextField prenomTextField = new JTextField(20);
    private JTextField emailTextField = new JTextField(20);
    private JTextField roleTextField = new JTextField("Administrateur", 20);

    private JLabel idLabel = new JLabel("ID");
    private JLabel nomLabel = new JLabel("Nom");
    private JLabel prenomLabel = new JLabel("Prénom");
    private JLabel emailLabel = new JLabel("Email");
    private JLabel roleLabel = new JLabel("Role");

    public AdministrateurFormulaire() {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(),
                "Formulaire Administrateur",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 16),
                Color.RED
        ));

        ajouterComposantes();
        
        idTextField.setEditable(false);
        idTextField.setBackground(Color.LIGHT_GRAY);

        roleTextField.setEditable(false);
        roleTextField.setBackground(Color.LIGHT_GRAY);
    }

    private void ajouterComposantes() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); 
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        addRow(gbc, 0, idLabel, idTextField);
        addRow(gbc, 1, nomLabel, nomTextField);
        addRow(gbc, 2, prenomLabel, prenomTextField);
        addRow(gbc, 3, emailLabel, emailTextField);
        addRow(gbc, 4, roleLabel, roleTextField);
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
    
    public JTextField getIdTextField() {
		return idTextField;
	}

	public void setIdTextField(JTextField idTextField) {
		this.idTextField = idTextField;
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

	public JLabel getEmailLabel() {
		return emailLabel;
	}

	public void setEmailLabel(JLabel emailLabel) {
		this.emailLabel = emailLabel;
	}

	public JLabel getRoleLabel() {
		return roleLabel;
	}

	public void setRoleLabel(JLabel roleLabel) {
		this.roleLabel = roleLabel;
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

    public JTextField getRoleTextField() {
        return roleTextField;
    }

    public void setRoleTextField(JTextField roleTextField) {
        this.roleTextField = roleTextField;
    }
    
    
}
