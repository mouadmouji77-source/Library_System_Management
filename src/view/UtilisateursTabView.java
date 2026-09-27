package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

public class UtilisateursTabView extends JPanel{
	
    private viewUsersMembres tabUtilisateursMembres = new viewUsersMembres();
    private viewUsersAdministrateur tabUtilisateursAdministrateurs = new viewUsersAdministrateur();
    private viewUsersBibliothecaires tabUtilisateursBibliothecaires = new viewUsersBibliothecaires();
    
    public UtilisateursTabView() {
    	setLayout(new BorderLayout(10, 10));
    	setBorder(new EmptyBorder(05, 05, 05, 05));
    	ajouterComposantes();
    }
    
    public void ajouterComposantes() {
    	JTabbedPane adherentsPanelInnerTab = new JTabbedPane();
        add(new JScrollPane(adherentsPanelInnerTab));
        
        JPanel membresTabPanel = new JPanel(new BorderLayout());
        membresTabPanel.add(new JScrollPane(tabUtilisateursMembres), BorderLayout.CENTER);
        
        JPanel bibliothecairesTabPanel = new JPanel(new BorderLayout());
        bibliothecairesTabPanel.add(new JScrollPane(tabUtilisateursBibliothecaires), BorderLayout.CENTER);
        
        JPanel administrateursTabPanel = new JPanel(new BorderLayout());
        administrateursTabPanel.add(new JScrollPane(tabUtilisateursAdministrateurs), BorderLayout.CENTER);

        adherentsPanelInnerTab.addTab("Membres", membresTabPanel);
        adherentsPanelInnerTab.addTab("Bibliothecaires",bibliothecairesTabPanel );
        adherentsPanelInnerTab.addTab("Administrateurs", administrateursTabPanel);

    }

	public viewUsersMembres getTabUtilisateursMembres() {
		return tabUtilisateursMembres;
	}

	public void setTabUtilisateursMembres(viewUsersMembres tabUtilisateursMembres) {
		this.tabUtilisateursMembres = tabUtilisateursMembres;
	}

	public viewUsersAdministrateur getTabUtilisateursAdministrateurs() {
		return tabUtilisateursAdministrateurs;
	}

	public void setTabUtilisateursAdministrateurs(viewUsersAdministrateur tabUtilisateursAdministrateurs) {
		this.tabUtilisateursAdministrateurs = tabUtilisateursAdministrateurs;
	}

	public viewUsersBibliothecaires getTabUtilisateursBibliothecaires() {
		return tabUtilisateursBibliothecaires;
	}

	public void setTabUtilisateursBibliothecaires(viewUsersBibliothecaires tabUtilisateursBibliothecaires) {
		this.tabUtilisateursBibliothecaires = tabUtilisateursBibliothecaires;
	}
	
	public static void main(String[] args) {
		JFrame j  = new JFrame();
		j.add(new UtilisateursTabView());
		j.setSize(1600, 1000);
        j.setLocationRelativeTo(null);;
		j.setVisible(true);
		
	}
}
