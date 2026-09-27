package controller;
import model.*;
import view.BibliothecaireView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.*;
import javax.swing.event.*;
import exception.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.toedter.calendar.JCalendar;


public class BibliothecaireController {
	
	private BibliothecaireModel model = new BibliothecaireModel();
    private BibliothecaireView view = new BibliothecaireView();
    
   

    public BibliothecaireController() {
    	
    	// remplir les listes 
        try {
			model.lireData();
		} catch (DataNotFoundException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e1.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
		}  
        
        remplirModelLivres();
        remplirModelLivresEmpruntes();
        remplirModelLivresEmpruntesHistorique();
        remplirModelMembres();
        remplirModelBibliothecaires();
        remplirModelAdministrateurs();   
        setModelsForRapport();
        
        // sauvegarde avant de quitter
        view.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                model.saveData();
                view.dispose();
            }
        });   
        view.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        // Les actions pour tab de livre
        view.getLivresTab().getAjouterLivre().addActionListener(e -> ajouterLivre());			       
        view.getLivresTab().getListeLivresTable().getSelectionModel().addListSelectionListener(e->selectRowActionLivre());
        view.getLivresTab().getModifierLivre().addActionListener(e -> modifierLivre());
        view.getLivresTab().getSupprimerLivre().addActionListener(e -> supprimerLivre());
        view.getLivresTab().getRechercherLivre().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filterTable();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filterTable();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filterTable();
            }

            private void filterTable() {
                String searchText = view.getLivresTab().getRechercherLivre().getText().toLowerCase();
                DefaultTableModel filteredModel = new DefaultTableModel(columnHeadersLivre, 0);
                
                
                for (LivreModel livre : model.getLivres()) {
                    if (livre.toStringForRecherche().toLowerCase().contains(searchText)) {
                        filteredModel.addRow(livre.toRow());
                    }
                }

                view.setModelTableLivre(filteredModel);
            }
        });        
        
        //les actions de tab emprunts
        view.getEmpruntsTab().getFinEmprunt().addActionListener(e ->finEmprunt());
        view.getEmpruntsTab().getSupprimerEmprunt().addActionListener(e -> supprimerLivreEmprunte());
        view.getEmpruntsTab().getAjouterEmprunt().addActionListener(e -> emprunterLivre());
        view.getEmpruntsTab().getGenererPDf().addActionListener(e->genererPDF());
        view.getEmpruntsTab().getModifierDateRetourEmprunt().addActionListener(e -> {
        	
			try {
				prolongerDate();
			} catch (EmpruntNotFoundException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e1.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
			}
		});
        view.getEmpruntsTab().getRechercherEmprunt().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filterTable();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filterTable();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filterTable();
            }

            private void filterTable() {
                String searchText = view.getEmpruntsTab().getRechercherEmprunt().getText().toLowerCase();
                DefaultTableModel filteredModel = new DefaultTableModel(columnHeadersLivreEmprunt, 0);
                
                
                for (LivreEmprunte livre : model.getLivresEmpruntes()) {
                	Object nomComplet;
					try {
						nomComplet = model.rechercherMembreId(livre.getIdMembre()).getNom() +" "+model.rechercherMembreId(livre.getIdMembre()).getPrenom();
						Object[] rowOrdered = {livre.getIdLivreEmprunt(),livre.getIdLivre(),livre.getTitre(),livre.getIdMembre(), nomComplet, livre.getDateEmprunt(),livre.getDateRetour(),livre.getPenalité(),livre.getPrixTotale(),livre.getEtat()};
						String stringRow = livre.getIdLivreEmprunt()+" "+livre.getIdLivre()+" "+livre.getTitre()+" "+livre.getIdMembre()+" "+ nomComplet.toString()+" "+ livre.getDateEmprunt()+" "+livre.getDateRetour()+" "+livre.getPenalité()+" "+livre.getPrixTotale()+" "+livre.getEtat();
	                    
						if (stringRow.toString().toLowerCase().contains(searchText)) {
	                    	if(livre.getEtat().equalsIgnoreCase("En Cours")) {
	                    	
	                        filteredModel.addRow(rowOrdered);
	                    	}
	                    }
                    } catch (MemberNotFoundException e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
					}
               		
                }

                view.setModelTableLivreEmprunte(filteredModel);
            }
        });        
        view.getEmpruntsTab().getRechercherEmpruntHistorique().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filterTable();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filterTable();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filterTable();
            }

            private void filterTable() {
                String searchText = view.getEmpruntsTab().getRechercherEmpruntHistorique().getText().toLowerCase();
                DefaultTableModel filteredModel = new DefaultTableModel(columnHeadersLivreEmprunt, 0);
                
                
                for (LivreEmprunte livre : model.getLivresEmpruntes()) {
                	Object nomComplet;
					try {
						nomComplet = model.rechercherMembreId(livre.getIdMembre()).getNom() +" "+model.rechercherMembreId(livre.getIdMembre()).getPrenom();
						Object[] rowOrdered = {livre.getIdLivreEmprunt(),livre.getIdLivre(),livre.getTitre(),livre.getIdMembre(), nomComplet, livre.getDateEmprunt(),livre.getDateRetour(),livre.getPenalité(),livre.getPrixTotale(),livre.getEtat()};
	           		 	String stringRow = livre.getIdLivreEmprunt()+" "+livre.getIdLivre()+" "+livre.getTitre()+" "+livre.getIdMembre()+" "+ nomComplet.toString()+" "+ livre.getDateEmprunt()+" "+livre.getDateRetour()+" "+livre.getPenalité()+" "+livre.getPrixTotale()+" "+livre.getEtat();
	                    if (stringRow.toString().toLowerCase().contains(searchText)) {
	                    	if(livre.getEtat().equalsIgnoreCase("Retourné")) {                    	
	                    		filteredModel.addRow(rowOrdered);
	                    	}
	                    }
					} catch (MemberNotFoundException e) {
						JOptionPane.showMessageDialog(null, e.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
					}
           		 	
                }

                view.setModelTableLivreEmprunteHistorique(filteredModel);
            }
        });
        view.getEmpruntsTab().getEmprunterLivreFormulaire().setListeLivres(model.getLivres());
        view.getEmpruntsTab().getEmprunterLivreFormulaire().setListeMembres(model.getMembres());
        
        
        // les actions de tab utilisateurs
        view.getUtilisateursTab().getTabUtilisateursMembres().getAjouterMembre().addActionListener(e -> ajouterMembre());        
        view.getUtilisateursTab().getTabUtilisateursMembres().getRechercheTextField().getDocument().addDocumentListener(new DocumentListener() {
        	@Override
            public void insertUpdate(DocumentEvent e) {
                filterTable();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filterTable();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filterTable();
            }

            private void filterTable() {
                String searchText = view.getUtilisateursTab().getTabUtilisateursMembres().getRechercheTextField().getText().toLowerCase();
                DefaultTableModel filteredModel = new DefaultTableModel(columnHeadersMembresTable, 0);
                
                
                for (MembreModel membre : model.getMembres()) {
                    if (membre.toString().toLowerCase().contains(searchText)) {
                        filteredModel.addRow(membre.toRow());
                    }
                }

                view.setModelTableMembresTable(filteredModel);
            }
        });        
        view.getUtilisateursTab().getTabUtilisateursBibliothecaires().getRechercheTextField().getDocument().addDocumentListener(new DocumentListener() {
        	@Override
            public void insertUpdate(DocumentEvent e) {
                filterTable();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filterTable();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filterTable();
            }

            private void filterTable() {
                String searchText = view.getUtilisateursTab().getTabUtilisateursBibliothecaires().getRechercheTextField().getText().toLowerCase();
                DefaultTableModel filteredModel = new DefaultTableModel(columnHeadersBibliothecairesTable, 0);
                
                
                for (BibliothecaireModel bibliothecaire : model.getBibliothecaires()) {
                    if (bibliothecaire.toString().toLowerCase().contains(searchText)) {
                        filteredModel.addRow(bibliothecaire.toRow());
                    }
                }

                view.setModelTableBibliothecairesTable(filteredModel);
            }
        });    
        view.getUtilisateursTab().getTabUtilisateursAdministrateurs().getRechercheTextField().getDocument().addDocumentListener(new DocumentListener() {
        	@Override
            public void insertUpdate(DocumentEvent e) {
                filterTable();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filterTable();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filterTable();
            }

            private void filterTable() {
                String searchText = view.getUtilisateursTab().getTabUtilisateursAdministrateurs().getRechercheTextField().getText().toLowerCase();
                DefaultTableModel filteredModel = new DefaultTableModel(columnHeadersAdministrateursTable, 0);
                
                
                for (AdministrateurModel admin: model.getAdministrateurs()) {
                    if (admin.toString().toLowerCase().contains(searchText)) {
                        filteredModel.addRow(admin.toRow());
                    }
                }

                view.setModelTableAdministrateursTable(filteredModel);
            }
        });     
        view.getUtilisateursTab().getTabUtilisateursMembres().getModiferMembre().addActionListener(e -> modifierMembre());
        view.getUtilisateursTab().getTabUtilisateursMembres().getMembresTable().getSelectionModel().addListSelectionListener(e->selectRowActionMembre());
        view.getUtilisateursTab().getTabUtilisateursMembres().getSupprimerMembre().addActionListener(e->supprimerMembre());
        view.getUtilisateursTab().getTabUtilisateursBibliothecaires().getAjouterBibliothecaire().addActionListener(e->ajouterBibliothecaire());
        view.getUtilisateursTab().getTabUtilisateursBibliothecaires().getModiferBibliothecaire().addActionListener(e->modifierBibliothecaire());
        view.getUtilisateursTab().getTabUtilisateursBibliothecaires().getSupprimerBibliothecaire().addActionListener(e->supprimerBibliothecaire());
        view.getUtilisateursTab().getTabUtilisateursAdministrateurs().getAjouterAdministrateur().addActionListener(e->ajouterAdmin());
        view.getUtilisateursTab().getTabUtilisateursAdministrateurs().getModiferAdministrateur().addActionListener(e->modifierAdmin());
        view.getUtilisateursTab().getTabUtilisateursAdministrateurs().getSupprimerAdministrateur().addActionListener(e->supprimerAdmin());
        view.getUtilisateursTab().getTabUtilisateursAdministrateurs().getAdministrateursTable().getSelectionModel().addListSelectionListener(e->selectRowActionAdmin());
        view.getUtilisateursTab().getTabUtilisateursBibliothecaires().getBibliothecairesTable().getSelectionModel().addListSelectionListener(e->selectRowActionBibliothecaire());
    
    }
    
    private String[] columnHeadersMembresTable = {
            "ID Membre", "Nom", "Prenom", "Email", "Role","CIN"
    };
    
    private String[] columnHeadersAdministrateursTable = {
            "ID Administrateur", "Nom", "Prenom", "Email", "Role"
    };
    
    private String[] columnHeadersBibliothecairesTable = {
            "ID Bibliothecaire", "Nom", "Prenom","Adresse" ,"Email","Téléphone","Mot de Passe", "Role"
    };
    
	private String[] columnHeadersLivre = {
            "ID Livre", "Titre", "Auteur", "Editeur", "Année de Publication",
            "Catégorie", "Langue", "Quantité","Prix d'Emprunt"
    };
	
	private String[] columnHeadersLivreEmprunt = {
            "ID", "ID Livre","Titre Livre", "ID Membre","Nom Complet de Membre", "Date d'Emprunte","Date de Retour","Penalité(%)","Prix","Etat"
    };
	
	private  DefaultTableModel tableModelLivre = new DefaultTableModel() {
		
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
	};
	
	private  DefaultTableModel tableModelLivreEmprunt = new DefaultTableModel() {
		
	    public boolean isCellEditable(int row, int column) {
	       return false;
	    }
	};
	
	private  DefaultTableModel tableModelLivreEmpruntHistorique = new DefaultTableModel() {
		
	    public boolean isCellEditable(int row, int column) {
	       return false;
	    }
	};

	private List<String> getTopFiveMembres(){
    	
    	List<String> currentData = extractIdMembres((DefaultTableModel)view.getEmpruntsTab().getListeEmpruntesTable().getModel());
        List<String> previousData = extractIdMembres((DefaultTableModel)view.getEmpruntsTab().getListeEmpruntesHistoriqueTable().getModel());

        
        List<String> combinedData = new ArrayList<>();
        combinedData.addAll(currentData);
        combinedData.addAll(previousData);

        
        Map<String, Long> frequencyMap = combinedData.stream()
            .collect(Collectors.groupingBy(idMembre -> idMembre, Collectors.counting()));

        
        return frequencyMap.entrySet().stream()
            .sorted(Map.Entry.<String, Long>comparingByValue(Comparator.reverseOrder()))
            .map(Map.Entry::getKey)
            .limit(5)
            .collect(Collectors.toList());
    }

	private List<String> getTopFiveLivres(){
    	
    	List<String> currentData = extractIdLivres((DefaultTableModel)view.getEmpruntsTab().getListeEmpruntesTable().getModel());
        List<String> previousData = extractIdLivres((DefaultTableModel)view.getEmpruntsTab().getListeEmpruntesHistoriqueTable().getModel());

        List<String> combinedData = new ArrayList<>();
        combinedData.addAll(currentData);
        combinedData.addAll(previousData);

        Map<String, Long> frequencyMap = combinedData.stream()
            .collect(Collectors.groupingBy(idMembre -> idMembre, Collectors.counting()));

        return frequencyMap.entrySet().stream()
            .sorted(Map.Entry.<String, Long>comparingByValue(Comparator.reverseOrder()))
            .map(Map.Entry::getKey)
            .limit(5)
            .collect(Collectors.toList());
    }

	private void setModelsForRapport() {
    	
    	List<String> listeTop5Livres = getTopFiveLivres();
    	List<String> listeTop5Membres = getTopFiveMembres();
    	
    	int nombreMembres = model.getMembres().size();
    	int nombreLivres = model.getLivres().size();
    	int nombreAdministrateurs = model.getAdministrateurs().size();
    	int nombreBibliothecaires = model.getBibliothecaires().size();
    	
    	List<String> listeTop5LivresFinales = new ArrayList<>();
    	List<String> listeTop5MembresFinales = new ArrayList<>();
    	
    	for(String idLivre : listeTop5Livres) {
    		int id = Integer.parseInt(idLivre);
    		Livre l = model.rechercherLivreId(id);
    		listeTop5LivresFinales.add(l.getTitre() + " (ID: "+ l.getIdLivre()+")");
    		
    	}
    	
    	for(String idMembre : listeTop5Membres) {
    		int id = Integer.parseInt(idMembre);
    		Membre m;
			try {
				m = model.rechercherMembreId(id);
				listeTop5MembresFinales.add(m.getNom()+" "+m.getPrenom()+ " (ID: "+ m.getId()+")");
			} catch (MemberNotFoundException e) {
				JOptionPane.showMessageDialog(null,e.getMessage(),"Erreur", JOptionPane.ERROR_MESSAGE);			
			}
    		  		
    	}
    	
    	view.getRapportsTab().setDataForLabels(nombreMembres, nombreBibliothecaires, nombreAdministrateurs, nombreLivres, listeTop5MembresFinales, listeTop5LivresFinales);
    	
    	
    }
	
	private List<String> extractIdMembres(DefaultTableModel model) {
        List<String> idMembres = new ArrayList<>();
        int rowCount = model.getRowCount();
        int idMembreColumnIndex = 3;
        for (int i = 0; i < rowCount; i++) {
            Object value = model.getValueAt(i, idMembreColumnIndex);
            if (value != null) {
                idMembres.add(value.toString());
            }
        }
        return idMembres;
    }
   
	private List<String> extractIdLivres(DefaultTableModel model) {
        List<String> idLivres = new ArrayList<>();
        int rowCount = model.getRowCount();
        int idLivreColumnIndex = 1; 

        for (int i = 0; i < rowCount; i++) {
            Object value = model.getValueAt(i, idLivreColumnIndex);
            if (value != null) {
            	idLivres.add(value.toString());
            }
        }
        return idLivres;
    }
	
	private void supprimerMembre() {
        int selectedRow = view.getUtilisateursTab().getTabUtilisateursMembres().getMembresTable().getSelectedRow();
        if (selectedRow != -1) {
            int id = model.getMembres().get(selectedRow).getId();
            int option = JOptionPane.showConfirmDialog(null, 
                "Supprimer Membre? (tous les emprunts liés à ce membre vont être supprimés)", 
                "Êtes-vous sûr?", JOptionPane.OK_CANCEL_OPTION);

            if (option == JOptionPane.OK_OPTION) {
                try {
                    
                    model.getLivresEmpruntes().removeIf(l -> {
                        if (l.getIdMembre() == id) {
                            
                            if ("en cours".equalsIgnoreCase(l.getEtat())) {
                                model.getLivres().forEach(livre -> {
                                    if (livre.getIdLivre() == l.getIdLivre()) {
                                        livre.setQuantite(livre.getQuantite() + 1);
                                    }
                                });
                            }
                            return true;
                        }
                        return false;
                    });

                    model.getMembres().removeIf(m -> m.getId() == id);
                    model.saveData();
                    view.getEmpruntsTab().getEmprunterLivreFormulaire().setListeMembres(model.getMembres());
                    remplirModelLivresEmpruntes();
                    remplirModelMembres();
                    remplirModelLivres();
                    remplirModelLivresEmpruntesHistorique();
                    setModelsForRapport();
                    emptyFormMembre();
                    JOptionPane.showMessageDialog(null, 
                        "Membre a été supprimé avec succès.", 
                        "Information", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, 
                        "Une erreur s'est produite lors de la suppression du membre.", 
                        "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, 
                "Veuillez sélectionner un membre depuis la table.", 
                "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

	private void selectRowActionMembre() {
    	if(view.getUtilisateursTab().getTabUtilisateursMembres().getMembresTable().getSelectedRow() != -1) {
        	
    		int i = view.getUtilisateursTab().getTabUtilisateursMembres().getMembresTable().getSelectedRow();
    		
    		
    		int id= (int)view.getUtilisateursTab().getTabUtilisateursMembres().getMembresModel().getValueAt(i, 0);
    		String nom =  (String)view.getUtilisateursTab().getTabUtilisateursMembres().getMembresModel().getValueAt(i, 1);
    		String prenom = (String) view.getUtilisateursTab().getTabUtilisateursMembres().getMembresModel().getValueAt(i, 2);
    		String email = (String) view.getUtilisateursTab().getTabUtilisateursMembres().getMembresModel().getValueAt(i, 3);
    		String cin = (String) view.getUtilisateursTab().getTabUtilisateursMembres().getMembresModel().getValueAt(i, 5);
    		String role = (String) view.getUtilisateursTab().getTabUtilisateursMembres().getMembresModel().getValueAt(i, 4);
    		
    		
    		
    		view.getUtilisateursTab().getTabUtilisateursMembres().getMembreFormulaire().getNomTextField().setText(nom);
    		view.getUtilisateursTab().getTabUtilisateursMembres().getMembreFormulaire().getPrenomTextField().setText(prenom);
    		view.getUtilisateursTab().getTabUtilisateursMembres().getMembreFormulaire().getEmailTextField().setText(email);
    		view.getUtilisateursTab().getTabUtilisateursMembres().getMembreFormulaire().getRoleTextField().setText(role);
    		view.getUtilisateursTab().getTabUtilisateursMembres().getMembreFormulaire().getIdMembreTextField().setText(Integer.toString(id));
    		view.getUtilisateursTab().getTabUtilisateursMembres().getMembreFormulaire().getCinTextField().setText(cin);
    	}
    }

	public void finEmprunt() {
        if (view.getEmpruntsTab().getListeEmpruntesTable().getSelectedRow() != -1) {
            
	            List<LivreEmprunte> empruntesEnCours = model.getLivresEmpruntes().stream()
	                    .filter(livre -> "En Cours".equalsIgnoreCase(livre.getEtat()))
	                    .collect(Collectors.toList());
	
	            
	            int selectedRow = view.getEmpruntsTab().getListeEmpruntesTable().getSelectedRow();
	            int idEmprunt = empruntesEnCours.get(selectedRow).getIdLivreEmprunt();
	            LivreEmprunte emprunt;
				try {
					emprunt = model.rechercherLivreEmprunteId(idEmprunt);
					
		            model.retournerLivre(emprunt);
		            int g = model.getLivres().indexOf(model.rechercherLivreId(emprunt.getIdLivre()));
		            model.getLivres().get(g).setQuantite(model.getLivres().get(g).getQuantite()+1);
		            remplirModelLivres();
		            remplirModelLivresEmpruntes();
		            remplirModelLivresEmpruntesHistorique();
	            JOptionPane.showMessageDialog(null, "Fin d'emprunt. Pour télécharger le reçu consulter l'historique. ","Fin d'Emprunt", JOptionPane.INFORMATION_MESSAGE);
	
				} catch (EmpruntNotFoundException e) {
					JOptionPane.showMessageDialog(null,e.getMessage(),"Erreur", JOptionPane.ERROR_MESSAGE);
				}
         
        } else {
            JOptionPane.showMessageDialog(null,
                    "Veuillez sélectionner un emprunt à terminer.",
                    "Erreur",
                    JOptionPane.WARNING_MESSAGE);
        }
    }
	
	public void prolongerDate() throws EmpruntNotFoundException {
        if (view.getEmpruntsTab().getListeEmpruntesTable().getSelectedRow() != -1) {
            
            List<LivreEmprunte> empruntesEnCours = model.getLivresEmpruntes().stream()
                    .filter(livre -> "En Cours".equalsIgnoreCase(livre.getEtat()))
                    .collect(Collectors.toList());

            
            int idEmprunt = empruntesEnCours.get(view.getEmpruntsTab().getListeEmpruntesTable().getSelectedRow()).getIdLivreEmprunt();
            int i = model.getLivresEmpruntes().indexOf(model.rechercherLivreEmprunteId(idEmprunt));

            
            String dateRetourString = model.getLivresEmpruntes().get(i).getDateRetour();

            SimpleDateFormat fomattage = new SimpleDateFormat("yyyy-MM-dd");

            Date dateRetour = null;
            try {
                dateRetour = fomattage.parse(dateRetourString);
            } catch (Exception e) {
                e.printStackTrace();
            }

            
            JCalendar dateRetourNew = new JCalendar();

            int option = JOptionPane.showConfirmDialog(
                    null,
                    dateRetourNew,
                    "Prolonger la date de retour",
                    JOptionPane.OK_CANCEL_OPTION
            );

            if (option == JOptionPane.OK_OPTION) {
                Date nouvelleDateRetour = dateRetourNew.getDate();

                
                if (nouvelleDateRetour.after(dateRetour)) {
                    String nouvelleDateRetourString = fomattage.format(nouvelleDateRetour);

                    
                    model.getLivresEmpruntes().get(i).setDateRetour(nouvelleDateRetourString);
                    model.saveData();

                    
                    view.getEmpruntsTab().getListeEmpruntesTable().setValueAt(nouvelleDateRetourString, 
                    view.getEmpruntsTab().getListeEmpruntesTable().getSelectedRow(), 6); 
                    remplirModelLivresEmpruntes();
                    JOptionPane.showMessageDialog(null, 
                            "La date de retour a été prolongée avec succès.", 
                            "Succès", 
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null,
                            "La nouvelle date de retour doit être après la date de retour actuelle.",
                            "Erreur",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null,
                    "Veuillez sélectionner un emprunt à prolonger.",
                    "Erreur",
                    JOptionPane.WARNING_MESSAGE);
        }
	}
	
	private void remplirModelMembres() {
    	DefaultTableModel tableMembresModel = new DefaultTableModel(columnHeadersMembresTable, 0);
    	for (Membre mem : model.getMembres()) {
    		tableMembresModel.addRow(mem.toRow());
        }
    	 view.setModelTableMembresTable(tableMembresModel);
    }
	
	private void remplirModelBibliothecaires() {
		DefaultTableModel tableBibliothecairesModel = new DefaultTableModel(columnHeadersBibliothecairesTable, 0);
    	for (Bibliothecaire bib : model.getBibliothecaires()) {
    		tableBibliothecairesModel.addRow(bib.toRow());
        }
    	 view.setModelTableBibliothecairesTable(tableBibliothecairesModel);
		
	}
	
	private void remplirModelAdministrateurs() {
		DefaultTableModel tableAdministrateursModel = new DefaultTableModel(columnHeadersAdministrateursTable, 0);
    	for (Administrateur adm : model.getAdministrateurs()) {
    		tableAdministrateursModel.addRow(adm.toRow());
        }
    	 view.setModelTableAdministrateursTable(tableAdministrateursModel);
		
	}
	
    private void remplirModelLivres() {
    	tableModelLivre = new DefaultTableModel(columnHeadersLivre, 0);
    	 for (Livre livre : model.getLivres()) {
            tableModelLivre.addRow(livre.toRow());
        }
    	 view.setModelTableLivre(tableModelLivre);
    }
    
    private void remplirModelLivresEmpruntes() {
    	tableModelLivreEmprunt = new DefaultTableModel(columnHeadersLivreEmprunt, 0);
   	 	for (LivreEmprunte livre : model.getLivresEmpruntes()) {
   		 if(livre.getEtat().equalsIgnoreCase("En Cours")) {
	   			try {
	   		 	Object nomComplet = model.rechercherMembreId(livre.getIdMembre()).getNom() +" "+model.rechercherMembreId(livre.getIdMembre()).getPrenom();
	   		 	Object[] rowOrdered = {livre.getIdLivreEmprunt(),livre.getIdLivre(),livre.getTitre(),livre.getIdMembre(), nomComplet, livre.getDateEmprunt(),livre.getDateRetour(),livre.getPenalité(),livre.getPrixTotale(),livre.getEtat()};
	   		 	tableModelLivreEmprunt.addRow(rowOrdered);
	   			}catch(MemberNotFoundException e) {
			 		JOptionPane.showMessageDialog(null,e.getMessage(),"Erreur", JOptionPane.ERROR_MESSAGE);
			 	}
   		 }
   	 	}
   	 	view.setModelTableLivreEmprunte(tableModelLivreEmprunt);
   }
    
    private void remplirModelLivresEmpruntesHistorique() {
	   	 tableModelLivreEmpruntHistorique = new DefaultTableModel(columnHeadersLivreEmprunt, 0);
      	 for (LivreEmprunte livre : model.getLivresEmpruntes()) {
      		 if(livre.getEtat().equalsIgnoreCase("Retourné")) {
      		 	try {
      			 Object nomComplet = model.rechercherMembreId(livre.getIdMembre()).getNom() +" "+model.rechercherMembreId(livre.getIdMembre()).getPrenom();
      		 	Object[] rowOrdered = {livre.getIdLivreEmprunt(),livre.getIdLivre(),livre.getTitre(),livre.getIdMembre(), nomComplet, livre.getDateEmprunt(),livre.getDateRetour(),livre.getPenalité(),livre.getPrixTotale(),livre.getEtat()};
      		 	tableModelLivreEmpruntHistorique.addRow(rowOrdered);
      		 	}catch(MemberNotFoundException e) {
      		 		JOptionPane.showMessageDialog(null,e.getMessage(),"Erreur", JOptionPane.ERROR_MESSAGE);
      		 	}
      		 }
      	 }
      	 	view.setModelTableLivreEmprunteHistorique(tableModelLivreEmpruntHistorique);
   }

	private void modifierMembre() {
	    	
	    	if(!(view.getUtilisateursTab().getTabUtilisateursMembres().getMembreFormulaire().getNomTextField().getText().isEmpty() 
	        		&& view.getUtilisateursTab().getTabUtilisateursMembres().getMembreFormulaire().getPrenomTextField().getText().isEmpty() 
	        		&& view.getUtilisateursTab().getTabUtilisateursMembres().getMembreFormulaire().getEmailTextField().getText().isEmpty()
	        		&& view.getUtilisateursTab().getTabUtilisateursMembres().getMembreFormulaire().getIdMembreTextField().getText().isEmpty()
	        		&& view.getUtilisateursTab().getTabUtilisateursMembres().getMembreFormulaire().getCinTextField().getText().isEmpty())
	    			&& view.getUtilisateursTab().getTabUtilisateursMembres().getMembresTable().getSelectedRow() != -1
	        	) {
	    			int cinDejaDansLaBaseDonnee = 1;
	    			
	        		int id = Integer.parseInt(view.getUtilisateursTab().getTabUtilisateursMembres().getMembreFormulaire().getIdMembreTextField().getText());
	        		try {
	        			
						MembreModel mm = model.rechercherMembreId(id);
						for(MembreModel cinCheck : model.getMembres()) {
							if(cinCheck.getCIN().equals(view.getUtilisateursTab().getTabUtilisateursMembres().getMembreFormulaire().getCinTextField().getText()) && cinCheck.getId() != mm.getId() )
							{
								cinDejaDansLaBaseDonnee=-1;
								
							}
						}
					} catch (MemberNotFoundException e) {
						System.out.println("not found");
					}
	        		
	        		if(cinDejaDansLaBaseDonnee == 1) {
		        		for(MembreModel m : model.getMembres()) {
		        			if(m.getId() == id) {
		        				m.setEmail(view.getUtilisateursTab().getTabUtilisateursMembres().getMembreFormulaire().getEmailTextField().getText());
		        				m.setNom(view.getUtilisateursTab().getTabUtilisateursMembres().getMembreFormulaire().getNomTextField().getText());
		        				m.setPrenom(view.getUtilisateursTab().getTabUtilisateursMembres().getMembreFormulaire().getPrenomTextField().getText());
		        				m.setCIN(view.getUtilisateursTab().getTabUtilisateursMembres().getMembreFormulaire().getCinTextField().getText());
		        			}
		        		}
		        		model.saveData();
		        		remplirModelMembres();
		        		setModelsForRapport();
		        		emptyFormMembre();
		        		JOptionPane.showMessageDialog(null, "Membre a été modifié avec succès","Succès",JOptionPane.INFORMATION_MESSAGE);
	        		}else {
	        			JOptionPane.showMessageDialog(null, "CIN doit être unique","Erreur",JOptionPane.ERROR_MESSAGE);
	        		}
	        	
	    	}else {
	        		JOptionPane.showMessageDialog(null, "Séléctionner un membre pour le modifier","Erreur",JOptionPane.ERROR_MESSAGE);
	        }
	    	
	    }

	private void ajouterMembre() {
	    	
	    	
	    		int id = getMaxIdUsers()+1;
	    		MembreModel membre = new MembreModel(
	    				
	    				view.getUtilisateursTab().getTabUtilisateursMembres().getMembreFormulaire().getNomTextField().getText(),
	    				view.getUtilisateursTab().getTabUtilisateursMembres().getMembreFormulaire().getPrenomTextField().getText(),
	    				view.getUtilisateursTab().getTabUtilisateursMembres().getMembreFormulaire().getEmailTextField().getText(),
	    				id,
	    				view.getUtilisateursTab().getTabUtilisateursMembres().getMembreFormulaire().getCinTextField().getText()    				
	    				);
	    		int cinDejaDansLaBaseDonnee = 1;
	    		
	    		for(MembreModel cinCheck : model.getMembres()) {
					if(cinCheck.getCIN().equals(view.getUtilisateursTab().getTabUtilisateursMembres().getMembreFormulaire().getCinTextField().getText()) )
					{
						cinDejaDansLaBaseDonnee=-1;
						
					}
				}
	    		
	    		if(cinDejaDansLaBaseDonnee ==1) {
		    		try {
						model.ajouterMembre(membre);
						model.saveData();
						remplirModelMembres();
						view.getEmpruntsTab().getEmprunterLivreFormulaire().setListeMembres(model.getMembres());
						setModelsForRapport();
						emptyFormMembre();
	    				
						JOptionPane.showMessageDialog(null, "Membre a été ajouté avec succès","Succès",JOptionPane.INFORMATION_MESSAGE);
			    		
					} catch (AddMemberException e) {
						JOptionPane.showMessageDialog(null, e.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
						
					}
	    		}else {
	    			JOptionPane.showMessageDialog(null, "Un Membre déja existe avec cette CIN!","Erreur",JOptionPane.ERROR_MESSAGE);
	    		}
	    		
	    		
	    	
	    	
	    }

	private void emptyFormMembre() {
		view.getUtilisateursTab().getTabUtilisateursMembres().getMembreFormulaire().getNomTextField().setText("");
		view.getUtilisateursTab().getTabUtilisateursMembres().getMembreFormulaire().getPrenomTextField().setText("");
		view.getUtilisateursTab().getTabUtilisateursMembres().getMembreFormulaire().getEmailTextField().setText("");
		view.getUtilisateursTab().getTabUtilisateursMembres().getMembreFormulaire().getIdMembreTextField().setText("");
		view.getUtilisateursTab().getTabUtilisateursMembres().getMembreFormulaire().getCinTextField().setText("");
	}
	
	private void ajouterAdmin() {
		
		int id = getMaxIdUsers()+1;
		AdministrateurModel admin = new AdministrateurModel(
				
				view.getUtilisateursTab().getTabUtilisateursAdministrateurs().getAdministrateurFormulaire().getNomTextField().getText(),
				view.getUtilisateursTab().getTabUtilisateursAdministrateurs().getAdministrateurFormulaire().getPrenomTextField().getText(),
				view.getUtilisateursTab().getTabUtilisateursAdministrateurs().getAdministrateurFormulaire().getEmailTextField().getText(),
				id   				
				);
		
    		try {
				model.ajouterAdministrateur(admin);
				model.saveData();
				remplirModelAdministrateurs();
				setModelsForRapport();
				emptyFormAdmin();
				
				
				JOptionPane.showMessageDialog(null, "Membre a été ajouté avec succès","Succès",JOptionPane.INFORMATION_MESSAGE);
	    		
			} catch (AddAdministratorException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
				
			}
		
		
	}
	
	private void emptyFormAdmin() {
		view.getUtilisateursTab().getTabUtilisateursAdministrateurs().getAdministrateurFormulaire().getNomTextField().setText("");
		view.getUtilisateursTab().getTabUtilisateursAdministrateurs().getAdministrateurFormulaire().getPrenomTextField().setText("");
		view.getUtilisateursTab().getTabUtilisateursAdministrateurs().getAdministrateurFormulaire().getEmailTextField().setText("");
		view.getUtilisateursTab().getTabUtilisateursAdministrateurs().getAdministrateurFormulaire().getIdTextField().setText("");
	}

	private void supprimerAdmin() {
		if (view.getUtilisateursTab().getTabUtilisateursAdministrateurs().getAdministrateursTable().getSelectedRow() != -1) {
		    int id = model.getAdministrateurs()
		                  .get(view.getUtilisateursTab().getTabUtilisateursAdministrateurs().getAdministrateursTable().getSelectedRow())
		                  .getId();

		    int option = JOptionPane.showConfirmDialog(null, "Supprimer Administrateur", "Êtes-Vous sûr?", JOptionPane.OK_CANCEL_OPTION);

		    if (option == JOptionPane.OK_OPTION) {
		        try {
		            AdministrateurModel adminToRemove = model.rechercherAdministrateurId(id);

		            if (adminToRemove != null) {
		                model.supprimerAdministrateur(adminToRemove);
		                model.saveData();
		                remplirModelAdministrateurs();
		                setModelsForRapport();
		                emptyFormAdmin();
		                JOptionPane.showMessageDialog(null, "Admin a été supprimé avec succès.", "Information", JOptionPane.INFORMATION_MESSAGE);
		            } else {
		                JOptionPane.showMessageDialog(null, "Administrateur non trouvé.", "Erreur", JOptionPane.ERROR_MESSAGE);
		            }
		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
		        } catch (AdministrateurNotFoundException ex) {
		            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		} else {
		    JOptionPane.showMessageDialog(null, "Veuillez sélectionner un administrateur depuis la table.", "Erreur", JOptionPane.ERROR_MESSAGE);
		}

	}

	private void modifierAdmin() {
		if(!(view.getUtilisateursTab().getTabUtilisateursAdministrateurs().getAdministrateurFormulaire().getNomTextField().getText().isEmpty() 
        		&& view.getUtilisateursTab().getTabUtilisateursAdministrateurs().getAdministrateurFormulaire().getPrenomTextField().getText().isEmpty() 
        		&& view.getUtilisateursTab().getTabUtilisateursAdministrateurs().getAdministrateurFormulaire().getEmailTextField().getText().isEmpty())
    			&& view.getUtilisateursTab().getTabUtilisateursAdministrateurs().getAdministrateursTable().getSelectedRow() != -1
        	) {
    			
    			
        		int id = Integer.parseInt(view.getUtilisateursTab().getTabUtilisateursAdministrateurs().getAdministrateurFormulaire().getIdTextField().getText());
        		
        		
	        		for(AdministrateurModel m : model.getAdministrateurs()) {
	        			if(m.getId() == id) {
	        				m.setEmail(view.getUtilisateursTab().getTabUtilisateursAdministrateurs().getAdministrateurFormulaire().getEmailTextField().getText());
	        				m.setNom(view.getUtilisateursTab().getTabUtilisateursAdministrateurs().getAdministrateurFormulaire().getNomTextField().getText());
	        				m.setPrenom(view.getUtilisateursTab().getTabUtilisateursAdministrateurs().getAdministrateurFormulaire().getPrenomTextField().getText());
	        				
	        			}
	        		}
	        		model.saveData();
	        		remplirModelAdministrateurs();
	        		setModelsForRapport();
	        		emptyFormAdmin();
	        		JOptionPane.showMessageDialog(null, "Admin a été modifié avec succès","Succès",JOptionPane.INFORMATION_MESSAGE);
        		
        	
    	}else {
        		JOptionPane.showMessageDialog(null, "Séléctionner un admin pour le modifier","Erreur",JOptionPane.ERROR_MESSAGE);
        	}
    
	}

	private void ajouterBibliothecaire() {
		String password = new String(view.getUtilisateursTab().getTabUtilisateursBibliothecaires().getBibliothecaireFormulaire().getMotDePasseField().getPassword());
		
		int id = getMaxIdUsers()+1;
		//id, nom,  prenom,  adresse,  email,  telephone,  motDePasse
		BibliothecaireModel bib = new BibliothecaireModel(
				id,
				view.getUtilisateursTab().getTabUtilisateursBibliothecaires().getBibliothecaireFormulaire().getNomTextField().getText(),
				view.getUtilisateursTab().getTabUtilisateursBibliothecaires().getBibliothecaireFormulaire().getPrenomTextField().getText(),
				view.getUtilisateursTab().getTabUtilisateursBibliothecaires().getBibliothecaireFormulaire().getAdresseTextField().getText(),
				view.getUtilisateursTab().getTabUtilisateursBibliothecaires().getBibliothecaireFormulaire().getEmailTextField().getText(),
				view.getUtilisateursTab().getTabUtilisateursBibliothecaires().getBibliothecaireFormulaire().getTelephoneTextField().getText(),
				password
				);
		
    		try {
				model.ajouterBibliothecaire(bib);
				model.saveData();
				remplirModelBibliothecaires();
				setModelsForRapport();
				emptyFormBibliothecaire();
				JOptionPane.showMessageDialog(null, "Bibliothécaire a été ajouté avec succès","Succès",JOptionPane.INFORMATION_MESSAGE);
	    		
			} catch (AddLibrarianException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
				
			}
	}
	
	private void modifierBibliothecaire() {
		if(!(view.getUtilisateursTab().getTabUtilisateursBibliothecaires().getBibliothecaireFormulaire().getNomTextField().getText().isEmpty()&&
				view.getUtilisateursTab().getTabUtilisateursBibliothecaires().getBibliothecaireFormulaire().getPrenomTextField().getText().isEmpty()&&
				view.getUtilisateursTab().getTabUtilisateursBibliothecaires().getBibliothecaireFormulaire().getAdresseTextField().getText().isEmpty()&&
				view.getUtilisateursTab().getTabUtilisateursBibliothecaires().getBibliothecaireFormulaire().getEmailTextField().getText().isEmpty()&&
				view.getUtilisateursTab().getTabUtilisateursBibliothecaires().getBibliothecaireFormulaire().getTelephoneTextField().getText().isEmpty()&&
				view.getUtilisateursTab().getTabUtilisateursBibliothecaires().getBibliothecaireFormulaire().getMotDePasseField().getPassword().length == 0)&&
    			view.getUtilisateursTab().getTabUtilisateursBibliothecaires().getBibliothecairesTable().getSelectedRow() != -1
        	) {
    			
    			
        		int id = Integer.parseInt(view.getUtilisateursTab().getTabUtilisateursBibliothecaires().getBibliothecaireFormulaire().getIdTextField().getText());
        		String password = new String(view.getUtilisateursTab().getTabUtilisateursBibliothecaires().getBibliothecaireFormulaire().getMotDePasseField().getPassword());
        		
	        		for(BibliothecaireModel m : model.getBibliothecaires()) {
	        			if(m.getId() == id) {
	        				m.setEmail(view.getUtilisateursTab().getTabUtilisateursBibliothecaires().getBibliothecaireFormulaire().getEmailTextField().getText());
	        				m.setNom(view.getUtilisateursTab().getTabUtilisateursBibliothecaires().getBibliothecaireFormulaire().getNomTextField().getText());
	        				m.setPrenom(view.getUtilisateursTab().getTabUtilisateursBibliothecaires().getBibliothecaireFormulaire().getPrenomTextField().getText());
	        				m.setAdresse(view.getUtilisateursTab().getTabUtilisateursBibliothecaires().getBibliothecaireFormulaire().getAdresseTextField().getText());
	        				m.setMotDePasse(password);
	        				m.setTelephone(view.getUtilisateursTab().getTabUtilisateursBibliothecaires().getBibliothecaireFormulaire().getTelephoneTextField().getText());
	        				
	        			}
	        		}
	        		model.saveData();
	        		remplirModelBibliothecaires();
	        		setModelsForRapport();
	        		
	        		emptyFormBibliothecaire();
	        		JOptionPane.showMessageDialog(null, "Bibliothécaire a été modifié avec succès","Succès",JOptionPane.INFORMATION_MESSAGE);
        		
        	
    	}else {
        		JOptionPane.showMessageDialog(null, "Séléctionner un admin pour le modifier","Erreur",JOptionPane.ERROR_MESSAGE);
        	}
    
	}
	
	public void emptyFormBibliothecaire() {
		view.getUtilisateursTab().getTabUtilisateursBibliothecaires().getBibliothecaireFormulaire().getNomTextField().setText("");
		view.getUtilisateursTab().getTabUtilisateursBibliothecaires().getBibliothecaireFormulaire().getPrenomTextField().setText("");
		view.getUtilisateursTab().getTabUtilisateursBibliothecaires().getBibliothecaireFormulaire().getEmailTextField().setText("");
		view.getUtilisateursTab().getTabUtilisateursBibliothecaires().getBibliothecaireFormulaire().getIdTextField().setText("");
		view.getUtilisateursTab().getTabUtilisateursBibliothecaires().getBibliothecaireFormulaire().getTelephoneTextField().setText("");
		view.getUtilisateursTab().getTabUtilisateursBibliothecaires().getBibliothecaireFormulaire().getMotDePasseField().setText("");
		view.getUtilisateursTab().getTabUtilisateursBibliothecaires().getBibliothecaireFormulaire().getAdresseTextField().setText("");
	}
	
	private void supprimerBibliothecaire() {
	    if (view.getUtilisateursTab().getTabUtilisateursBibliothecaires().getBibliothecairesTable().getSelectedRow() != -1) {
	        int id = model.getBibliothecaires().get(view.getUtilisateursTab().getTabUtilisateursBibliothecaires().getBibliothecairesTable().getSelectedRow()).getId();
	       
	        int option = JOptionPane.showConfirmDialog(null, "Supprimer Bibliothécaires", "Êtes-Vous sûr?", JOptionPane.OK_CANCEL_OPTION);

	        if (option == JOptionPane.OK_OPTION) {
	            try {
	                BibliothecaireModel bibliothecaireToRemove = model.rechercherBibliothecaireId(id);
	                if (bibliothecaireToRemove != null) {
	                    model.supprimerBibliothecaire(bibliothecaireToRemove);

	                    model.saveData();
	                    remplirModelBibliothecaires();
	                    setModelsForRapport();
	                    emptyFormBibliothecaire();
	                    JOptionPane.showMessageDialog(null, "Bibliothécaire a été supprimé avec succès.", "Information", JOptionPane.INFORMATION_MESSAGE);
	                } else {
	                    JOptionPane.showMessageDialog(null, "Bibliothécaire non trouvé.", "Erreur", JOptionPane.ERROR_MESSAGE);
	                }
	            } catch (NumberFormatException ex) {
	                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
	            } catch (BibliothecaireNotFoundException ex) {
	                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
	            }
	        }
	    } else {
	        JOptionPane.showMessageDialog(null, "Veuillez sélectionner un bibliothécaire depuis la table.", "Erreur", JOptionPane.ERROR_MESSAGE);
	    }
	}
	
	private void ajouterLivre(){
    	try {

		    	int id = getMaxIdLivres()+1;
		    	LivreModel livre = new LivreModel(view.getLivresTab().getLivreFormulaire().getTitreLivreTextField().getText(),
    					view.getLivresTab().getLivreFormulaire().getAuteurLivreTextField().getText(),
    					view.getLivresTab().getLivreFormulaire().getEditeurLivreTextField().getText(),
    					id,
    					Integer.parseInt(view.getLivresTab().getLivreFormulaire().getDatePublicationLivreTextField().getText().isEmpty()? "0":view.getLivresTab().getLivreFormulaire().getDatePublicationLivreTextField().getText()),
    					view.getLivresTab().getLivreFormulaire().getCategorieLivreTextField().getText(),
    					view.getLivresTab().getLivreFormulaire().getLangueLivreTextField().getText(),
    					Integer.parseInt(view.getLivresTab().getLivreFormulaire().getQuantiteLivreTextField().getText().isEmpty()? "0": view.getLivresTab().getLivreFormulaire().getQuantiteLivreTextField().getText()),
    					Float.parseFloat(view.getLivresTab().getLivreFormulaire().getPrixLivreTextField().getText().isEmpty()? "0":view.getLivresTab().getLivreFormulaire().getPrixLivreTextField().getText() )
    					);
		    	
		    	model.ajouterLivre(livre);
		    	model.saveData();		    	
		    	remplirModelLivres();
		    	setModelsForRapport();
		    	emptyFormLivre();
		    	JOptionPane.showMessageDialog(null, "Livre a été ajouté avec succès","Succès",JOptionPane.INFORMATION_MESSAGE);

    	}catch(AddLivreException  | NumberFormatException e) {
    		
    		JOptionPane.showMessageDialog(null,e.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
    	
    	}

    }
	
	private void emptyFormLivre() {
		view.getEmpruntsTab().getEmprunterLivreFormulaire().setListeLivres(model.getLivres());
    	
    	view.getLivresTab().getLivreFormulaire().getTitreLivreTextField().setText("");
		
		view.getLivresTab().getLivreFormulaire().getEditeurLivreTextField().setText("");
		
		view.getLivresTab().getLivreFormulaire().getDatePublicationLivreTextField().setText("");
		
		view.getLivresTab().getLivreFormulaire().getCategorieLivreTextField().setText("");
		
		view.getLivresTab().getLivreFormulaire().getLangueLivreTextField().setText("");
		
		view.getLivresTab().getLivreFormulaire().getQuantiteLivreTextField().setText(""); 			 
		
		view.getLivresTab().getLivreFormulaire().getPrixLivreTextField().setText("");
		
		view.getLivresTab().getLivreFormulaire().getAuteurLivreTextField().setText(""); 
		
		view.getLivresTab().getLivreFormulaire().getIdLivreTextField().setText("");
	}

    private void supprimerLivre() {
    	
    	if(view.getLivresTab().getListeLivresTable().getSelectedRow()!= -1 ) {
    	int id = model.getLivres().get(view.getLivresTab().getListeLivresTable().getSelectedRow()).getIdLivre();
    	
    	int option = JOptionPane.showConfirmDialog(null, "Supprimer Livre", "Êtes-Vous sûr?",JOptionPane.OK_CANCEL_OPTION);
    	
    	        if (option == JOptionPane.OK_OPTION) {
    	            try {
    	            	LivreModel l = model.rechercherLivreId(id);
    	            	for(LivreEmprunte le : model.getLivresEmpruntes()) {
    	            		if(le.getIdLivre() == id) {
    	            			model.supprimerLivreEmprunte(le);
    	            		}
    	            	}
    	                model.supprimerLivre(l);
    	                tableModelLivre.removeRow(view.getLivresTab().getListeLivresTable().getSelectedRow());
    	                remplirModelLivresEmpruntes();
    	                remplirModelLivresEmpruntesHistorique();
    	                remplirModelLivres();
    	                view.getEmpruntsTab().getEmprunterLivreFormulaire().setListeLivres(model.getLivres());
    	                setModelsForRapport();
    	                emptyFormLivre();
    	                JOptionPane.showMessageDialog(null, "Livre a été supprimé avec succès.", "Information", JOptionPane.INFORMATION_MESSAGE);
    	            } catch (NumberFormatException ex) {
    	                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
    	            } catch (BookNotFoundException ex) {
    	                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
    	            }
    	        }
    	}
    	else {
    		JOptionPane.showMessageDialog(null, "Veuillez séléctionner un livre depuis la table.", "Erreur", JOptionPane.ERROR_MESSAGE);
    	}
    
    }

    public void supprimerLivreEmprunte() {
    	if(view.getEmpruntsTab().getListeEmpruntesTable().getSelectedRow()!= -1 ) {
    		
    		List<LivreEmprunte> empruntesEnCours = model.getLivresEmpruntes().stream()
    			    .filter(livre -> "En Cours".equalsIgnoreCase(livre.getEtat()))
    			    .collect(Collectors.toList());
    		
        	int idEmprunt = empruntesEnCours.get(view.getEmpruntsTab().getListeEmpruntesTable().getSelectedRow()).getIdLivreEmprunt();
        	int idLivre = empruntesEnCours.get(view.getEmpruntsTab().getListeEmpruntesTable().getSelectedRow()).getIdLivre();
        	
        	int option = JOptionPane.showConfirmDialog(null, "Supprimer Livre", "Êtes-Vous sûr?",JOptionPane.OK_CANCEL_OPTION);
        	
        	        if (option == JOptionPane.OK_OPTION) {
        	            try {
        	            	Livre l = model.rechercherLivreId(idLivre);
        	            	LivreEmprunte le = model.rechercherLivreEmprunteId(idEmprunt);        	            	
        	                model.supprimerLivreEmprunte(le);
        	                remplirModelLivresEmpruntes();
        	                System.out.println(idEmprunt+" "+idLivre);
        	                System.out.println("here2");
        	                setModelsForRapport();
        	                JOptionPane.showMessageDialog(null, "Livre emprunté a été supprimé avec succès.", "Information", JOptionPane.INFORMATION_MESSAGE);
        	            } catch (NumberFormatException ex) {
        	                JOptionPane.showMessageDialog(null, "Veuillez séléctionner un livre emprunté depuis la table.", "Erreur", JOptionPane.ERROR_MESSAGE);
        	            } catch (Exception ex) {
        	                JOptionPane.showMessageDialog(null, "Une erreur s'est produite lors de la suppression du livre.", "Erreur", JOptionPane.ERROR_MESSAGE);
        	            }
        	        }
        	}
        	else {
        		JOptionPane.showMessageDialog(null, "Veuillez séléctionner un livre emprunté depuis la table.", "Erreur", JOptionPane.ERROR_MESSAGE);
        	}
    }

    private void modifierLivre() {
    	
    	if(!(view.getLivresTab().getLivreFormulaire().getTitreLivreTextField().getText().isEmpty()  
    			&&
    			view.getLivresTab().getLivreFormulaire().getEditeurLivreTextField().getText().isEmpty() 
    			&&
    			view.getLivresTab().getLivreFormulaire().getDatePublicationLivreTextField().getText().isEmpty() 
    			&&
    			view.getLivresTab().getLivreFormulaire().getCategorieLivreTextField().getText().isEmpty() 
    			&&
    			view.getLivresTab().getLivreFormulaire().getLangueLivreTextField().getText().isEmpty() 
    			&&
    			view.getLivresTab().getLivreFormulaire().getQuantiteLivreTextField().getText().isEmpty()     			 
	    		&&
				view.getLivresTab().getLivreFormulaire().getPrixLivreTextField().getText().isEmpty() 
				&&
				view.getLivresTab().getLivreFormulaire().getAuteurLivreTextField().getText().isEmpty() 
				
				
				)
    			&& view.getLivresTab().getListeLivresTable().getSelectedRow() != -1)
    	{
		    	int id = Integer.parseInt(view.getLivresTab().getLivreFormulaire().getIdLivreTextField().getText());
		    	int date = Integer.parseInt(view.getLivresTab().getLivreFormulaire().getDatePublicationLivreTextField().getText());
		    	int quantite=  Integer.parseInt(view.getLivresTab().getLivreFormulaire().getQuantiteLivreTextField().getText());
		    	float prix=Float.parseFloat(view.getLivresTab().getLivreFormulaire().getPrixLivreTextField().getText()) ;
		    	for(Livre l : model.getLivres()) {
		    		if(l.getIdLivre() == id) {
		    			l.setAuteur(view.getLivresTab().getLivreFormulaire().getAuteurLivreTextField().getText());
		    			l.setCategorie(view.getLivresTab().getLivreFormulaire().getCategorieLivreTextField().getText());
		    			
		    			if(date <2026 && date >1900) {
		    				l.setDatePublication(Integer.parseInt(view.getLivresTab().getLivreFormulaire().getDatePublicationLivreTextField().getText()));
		    			}
		    			l.setEditeur(view.getLivresTab().getLivreFormulaire().getEditeurLivreTextField().getText());
		    			
		    			l.setLangue(view.getLivresTab().getLivreFormulaire().getLangueLivreTextField().getText());
		    			
		    			if(prix > 0.0f) {
		    				l.setPrix(Float.parseFloat(view.getLivresTab().getLivreFormulaire().getPrixLivreTextField().getText()));
		    			}
		    			if(quantite > 0) {
		    				l.setQuantite(Integer.parseInt(view.getLivresTab().getLivreFormulaire().getQuantiteLivreTextField().getText()));
		    			}
		    			
		    			l.setTitre(view.getLivresTab().getLivreFormulaire().getTitreLivreTextField().getText());
		    		}
		    	}
		    	
		    	
		    	model.saveData();
		    	remplirModelLivres();
		    	setModelsForRapport();
		    	emptyFormLivre();
				
				
		    	JOptionPane.showMessageDialog(null, "Livre a été modifié avec succès. les valeurs invalides ne sont pas prend en compte","Succès",JOptionPane.INFORMATION_MESSAGE);
    	
    	}else {
    		JOptionPane.showMessageDialog(null, "Séléctionner un livre pour le modifier et entrer des valeurs valides.\nN.B la date doit être entre 1901 et 2025","Erreur",JOptionPane.ERROR_MESSAGE);
    	}
    }

    private void emprunterLivre() {
    	if(view.getEmpruntsTab().getEmprunterLivreFormulaire().getDateEmprunt() !=null
    		&&
    		view.getEmpruntsTab().getEmprunterLivreFormulaire().getDateRetour() !=null
    		&&
    		view.getEmpruntsTab().getEmprunterLivreFormulaire().getListeLivres().getSelectedIndex() != -1
    		&&
    		view.getEmpruntsTab().getEmprunterLivreFormulaire().getListeMembres().getSelectedIndex() != -1
    	   ) {
    		
		
		        Date dateEmprunt = new Date(System.currentTimeMillis());
		        Date dateRetour = view.getEmpruntsTab().getEmprunterLivreFormulaire().getDateRetour().getDate();
		        System.out.println(dateEmprunt.compareTo(dateRetour));
		        
		        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		        String dateEmpruntString = sdf.format(dateEmprunt);
		        String dateRetourString = sdf.format(dateRetour);

		        if (dateEmpruntString.equals(dateRetourString)) {
		            JOptionPane.showMessageDialog(null, "La date d'emprunte ne peut pas être identique à la date de retour.", "Erreur", JOptionPane.ERROR_MESSAGE);
		        } else if (dateEmprunt.after(dateRetour)) {
		            JOptionPane.showMessageDialog(null, "La date d'emprunte doit être avant la date de retour.", "Erreur", JOptionPane.ERROR_MESSAGE);
		        } else {
		            // Valid dates: proceed with adding the borrowed book
		            int idEmprunt = getMaxIdLivresEmpruntes() + 1;
		            int indiceDansListeLivresCombo = view.getEmpruntsTab().getEmprunterLivreFormulaire().getListeLivres().getSelectedIndex();
		            int indiceDansListeMembresCombo = view.getEmpruntsTab().getEmprunterLivreFormulaire().getListeMembres().getSelectedIndex();
		            LivreModel l = model.getLivres().get(indiceDansListeLivresCombo);
		            MembreModel m = model.getMembres().get(indiceDansListeMembresCombo);
		            
		            float prix = l.getPrix() * (float)((dateRetour.getTime() - dateEmprunt.getTime()) / (1000 * 60 * 60 * 24));
		            System.out.println(prix);
		            if(l.getQuantite()>0) {
		            	l.setQuantite(l.getQuantite() - 1);
		            	LivreEmprunte nouveau = new LivreEmprunte(
			                l,
			                idEmprunt,
			                m.getIdMembre(),
			                dateEmpruntString,
			                dateRetourString,
			                0.0f,
			                prix,
			                "En Cours"
			            );
	
			            model.ajouterLivreEmprunte(nouveau);
			            model.saveData();
			            Object nomComplet;
						try {
							nomComplet = model.rechercherMembreId(nouveau.getIdMembre()).getNom() +" "+model.rechercherMembreId(nouveau.getIdMembre()).getPrenom();
							Object[] nouveauOrdered = {nouveau.getIdLivreEmprunt(),nouveau.getIdLivre(),nouveau.getTitre(),nouveau.getIdMembre(), nomComplet, nouveau.getDateEmprunt(),nouveau.getDateRetour(),nouveau.getPenalité(),nouveau.getPrixTotale(),nouveau.getEtat()};
 				            tableModelLivreEmprunt.addRow(nouveauOrdered);
				            setModelsForRapport();
				            JOptionPane.showMessageDialog(null, "Livre emprunté avec succès.", "Information", JOptionPane.INFORMATION_MESSAGE);
				            
						} catch (MemberNotFoundException e) {
							JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
						}
			   		 	
		            }
		            else {
		            	JOptionPane.showMessageDialog(null, "Oops! Vous ne pouvez pas emprunter ce livre maintenant. Rupture du stock.", "Erreur", JOptionPane.ERROR_MESSAGE);
			            
		            }
		        }

		        
		        
    	}
    	  
    }

    public int getMaxIdLivres() {
    
	   	 List<LivreModel> livres = model.getLivres();
	   	 if(livres.size() == 0) {
	 		 return 0;
	 	 }else if(livres.size()== 1) {
	 		 return livres.get(0).getIdLivre();
	 	 }else {
	   	 int id = livres.get(0).getIdLivre();
	   	 
	   	 for(int i = 1; i < livres.size(); i++) {
	   		 if(id < livres.get(i).getIdLivre()) {
	   			 id = livres.get(i).getIdLivre();
	   		 }
	   	 }
	   	 
	   	 return id;
	 	 }
   }
   
    public int getMaxIdUsers() {
   	
	  	 List<Integer> ids = model.getIdsUtilisateurs();
	  	 if(ids.size() == 0) {
	  		 return 0;
	  	 }else if(ids.size() == 0) {
	  		 return ids.get(0);
	  	 }else {
	  	 
	  	 int id = ids.get(0);
	  	 
	  	 for(int i = 1; i < ids.size(); i++) {
	  		 if(id < ids.get(i)) {
	  			 id = ids.get(i);
	  		 }
	  	 }
  	
  	 return id;
  	 }
  }
   
    public int getMaxIdLivresEmpruntes() {
   	
    	
	  	 List<LivreEmprunte> livres = model.getLivresEmpruntes();
	  	
	  	 if(livres.size() == 0) {
	  		 return 0;
	  	 }else if(livres.size()== 1) {
	  		 return livres.get(0).getIdLivre();
	  	 }else {
	  	 
	  	 int id = livres.get(0).getIdLivreEmprunt();
	  	 
	  	 for(int i = 1; i < livres.size(); i++) {
	  		 if(id < livres.get(i).getIdLivreEmprunt()) {
	  			 id = livres.get(i).getIdLivreEmprunt();
	  		 }
	  	 }
	  	 
	  	 return id;
	  	 }
  	
  }

    private void selectRowActionLivre() {
    	if(view.getLivresTab().getListeLivresTable().getSelectedRow() != -1) {
    	
    		int i = view.getLivresTab().getListeLivresTable().getSelectedRow();
    		
    		
    		int id= (int)(tableModelLivre.getValueAt(i, 0));
    		String titre =  (String)tableModelLivre.getValueAt(i, 1);
    		String auteur = (String) tableModelLivre.getValueAt(i, 2);
    		String editeur = (String) tableModelLivre.getValueAt(i, 3);
    		int annee = (int) tableModelLivre.getValueAt(i, 4);
    		String categorie = (String) tableModelLivre.getValueAt(i, 5);
    		String langue = (String) tableModelLivre.getValueAt(i, 6);
    		int q = (int) tableModelLivre.getValueAt(i, 7);
    		float prix = (float) tableModelLivre.getValueAt(i, 8);
    		
    		
    		view.getLivresTab().getLivreFormulaire().getIdLivreTextField().setText(Integer.toString(id));
    		view.getLivresTab().getLivreFormulaire().getTitreLivreTextField().setText(titre);
    		view.getLivresTab().getLivreFormulaire().getAuteurLivreTextField().setText(auteur);
    		view.getLivresTab().getLivreFormulaire().getEditeurLivreTextField().setText(editeur);
    		view.getLivresTab().getLivreFormulaire().getDatePublicationLivreTextField().setText(Integer.toString(annee));
    		view.getLivresTab().getLivreFormulaire().getCategorieLivreTextField().setText(categorie);
    		view.getLivresTab().getLivreFormulaire().getLangueLivreTextField().setText(langue);
    		view.getLivresTab().getLivreFormulaire().getQuantiteLivreTextField().setText(Integer.toString(q));
    		view.getLivresTab().getLivreFormulaire().getPrixLivreTextField().setText(Float.toString(prix));
    		
    	}
    	
    }
    
    private void selectRowActionAdmin() {
    	if(view.getUtilisateursTab().getTabUtilisateursAdministrateurs().getAdministrateursTable().getSelectedRow() != -1) {
    	
    		int i = view.getUtilisateursTab().getTabUtilisateursAdministrateurs().getAdministrateursTable().getSelectedRow();
    		
    		
    		int id= (int)(view.getUtilisateursTab().getTabUtilisateursAdministrateurs().getAdministrateursTable().getModel().getValueAt(i, 0));
    		String nom =  (String)view.getUtilisateursTab().getTabUtilisateursAdministrateurs().getAdministrateursTable().getModel().getValueAt(i, 1);
    		String prenom = (String) view.getUtilisateursTab().getTabUtilisateursAdministrateurs().getAdministrateursTable().getModel().getValueAt(i, 2);
    		String email = (String) view.getUtilisateursTab().getTabUtilisateursAdministrateurs().getAdministrateursTable().getModel().getValueAt(i, 3);
    		String role = (String) view.getUtilisateursTab().getTabUtilisateursAdministrateurs().getAdministrateursTable().getModel().getValueAt(i, 4);
    		
    		
    		
    		view.getUtilisateursTab().getTabUtilisateursAdministrateurs().getAdministrateurFormulaire().getIdTextField().setText(Integer.toString(id));
    		view.getUtilisateursTab().getTabUtilisateursAdministrateurs().getAdministrateurFormulaire().getNomTextField().setText(nom);
    		view.getUtilisateursTab().getTabUtilisateursAdministrateurs().getAdministrateurFormulaire().getPrenomTextField().setText(prenom);
    		view.getUtilisateursTab().getTabUtilisateursAdministrateurs().getAdministrateurFormulaire().getEmailTextField().setText(email);
    		view.getUtilisateursTab().getTabUtilisateursAdministrateurs().getAdministrateurFormulaire().getRoleTextField().setText(role);
    	}
    	
    }
    
    private void selectRowActionBibliothecaire() {
    	if(view.getUtilisateursTab().getTabUtilisateursBibliothecaires().getBibliothecairesTable().getSelectedRow() != -1) {
    	
    		int i = view.getUtilisateursTab().getTabUtilisateursBibliothecaires().getBibliothecairesTable().getSelectedRow();
    		
    		
    		int id= (int)(view.getUtilisateursTab().getTabUtilisateursBibliothecaires().getBibliothecairesTable().getModel().getValueAt(i, 0));
    		String nom =  (String)view.getUtilisateursTab().getTabUtilisateursBibliothecaires().getBibliothecairesTable().getModel().getValueAt(i, 1);
    		String prenom = (String) view.getUtilisateursTab().getTabUtilisateursBibliothecaires().getBibliothecairesTable().getModel().getValueAt(i, 2);
    		String adresse = (String) view.getUtilisateursTab().getTabUtilisateursBibliothecaires().getBibliothecairesTable().getModel().getValueAt(i, 3);
    		String email = (String) view.getUtilisateursTab().getTabUtilisateursBibliothecaires().getBibliothecairesTable().getModel().getValueAt(i, 4);
    		String role = (String) view.getUtilisateursTab().getTabUtilisateursBibliothecaires().getBibliothecairesTable().getModel().getValueAt(i, 7);
    		String tele = (String) view.getUtilisateursTab().getTabUtilisateursBibliothecaires().getBibliothecairesTable().getModel().getValueAt(i, 5);
    		String mdp = (String) view.getUtilisateursTab().getTabUtilisateursBibliothecaires().getBibliothecairesTable().getModel().getValueAt(i, 6);
    		
    		
    		view.getUtilisateursTab().getTabUtilisateursBibliothecaires().getBibliothecaireFormulaire().getNomTextField().setText(nom);
    		view.getUtilisateursTab().getTabUtilisateursBibliothecaires().getBibliothecaireFormulaire().getPrenomTextField().setText(prenom);
    		view.getUtilisateursTab().getTabUtilisateursBibliothecaires().getBibliothecaireFormulaire().getEmailTextField().setText(email);
    		view.getUtilisateursTab().getTabUtilisateursBibliothecaires().getBibliothecaireFormulaire().getIdTextField().setText(Integer.toString(id));
    		view.getUtilisateursTab().getTabUtilisateursBibliothecaires().getBibliothecaireFormulaire().getTelephoneTextField().setText(tele);
    		view.getUtilisateursTab().getTabUtilisateursBibliothecaires().getBibliothecaireFormulaire().getMotDePasseField().setText(mdp);
    		view.getUtilisateursTab().getTabUtilisateursBibliothecaires().getBibliothecaireFormulaire().getAdresseTextField().setText(adresse);
    	}
    	
    }

    private void selectRowActionEmprunt() {
    	if(view.getEmpruntsTab().getListeEmpruntesTable().getSelectedRow() != -1) {
    	
    		int i = view.getEmpruntsTab().getListeEmpruntesTable().getSelectedRow();
    		
    		
    		int id= (int)(tableModelLivreEmprunt.getValueAt(i, 0));
    		int idLivre= (int)(tableModelLivreEmprunt.getValueAt(i, 1));
    		int idMembre =  (int)tableModelLivreEmprunt.getValueAt(i, 2);
    		float penalite = (float)(tableModelLivreEmprunt.getValueAt(i, 5));
    		float prix = (float)(tableModelLivreEmprunt.getValueAt(i, 6));
    		
    		String dateEmpruntString = (String)(tableModelLivreEmprunt.getValueAt(i, 3));
    		String dateRetourString = (String)(tableModelLivreEmprunt.getValueAt(i, 4));
            
            SimpleDateFormat fomattage = new SimpleDateFormat("yyyy-MM-dd"); 

            Date dateEmprunt = null;
            Date dateRetour = null;
            try {
                
                dateEmprunt = fomattage.parse(dateEmpruntString);
                dateRetour = fomattage.parse(dateRetourString);
            } catch (Exception e) {
                e.printStackTrace(); 
            }
            
            Livre l = model.rechercherLivreId(idLivre);
            
            String textComboLivre = l.getIdLivre()+": "+l.getTitre();
            
            MembreModel m;
			try {
				m = model.rechercherMembreId(idMembre);
				String textComboMembre = m.getIdMembre() + ": " + m.getNom()+" "+m.getPrenom();
		           
	            for (int k = 0; k < view.getEmpruntsTab().getEmprunterLivreFormulaire().getListeLivres().getItemCount(); k++) {
	                String item = (String) view.getEmpruntsTab().getEmprunterLivreFormulaire().getListeLivres().getItemAt(k);
	                if(item.equals(textComboLivre)) {
	                	view.getEmpruntsTab().getEmprunterLivreFormulaire().getListeLivres().setSelectedIndex(k);
	                }
	            }
	            
	            for (int k = 0; k < view.getEmpruntsTab().getEmprunterLivreFormulaire().getListeMembres().getItemCount(); k++) {
	                String item = (String) view.getEmpruntsTab().getEmprunterLivreFormulaire().getListeMembres().getItemAt(k);
	                if(item.equals(textComboMembre)) {
	                	view.getEmpruntsTab().getEmprunterLivreFormulaire().getListeMembres().setSelectedIndex(k);
	                }
	            }
	                       
	    		view.getEmpruntsTab().getEmprunterLivreFormulaire().getIdEmpruntTextField().setText(Integer.toString(id));
	    		view.getEmpruntsTab().getEmprunterLivreFormulaire().getPrixTextField().setText(Float.toString(prix));
	    		view.getEmpruntsTab().getEmprunterLivreFormulaire().getPenaliteTextField().setText(Float.toString(penalite));
	    		view.getEmpruntsTab().getEmprunterLivreFormulaire().getDateEmprunt().setDate(dateEmprunt);
	    		view.getEmpruntsTab().getEmprunterLivreFormulaire().getDateRetour().setDate(dateRetour);
			} catch (MemberNotFoundException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
			}
   		
    	}
    	
    }

    private void genererPDF() {
        if (view.getEmpruntsTab().getListeEmpruntesHistoriqueTable().getSelectedRow() != -1) {
        	try {
                List<LivreEmprunte> empruntesRetourné = model.getLivresEmpruntes().stream()
                        .filter(livre -> "Retourné".equalsIgnoreCase(livre.getEtat()))
                        .collect(Collectors.toList());

                int selectedRow = view.getEmpruntsTab().getListeEmpruntesHistoriqueTable().getSelectedRow();
                int idEmprunt = empruntesRetourné.get(selectedRow).getIdLivreEmprunt();
                LivreEmprunte emprunt = model.rechercherLivreEmprunteId(idEmprunt);

                


                String filePath = System.getProperty("user.dir") + "/Data/Reçues/";
                File directory = new File(filePath);
                if (!directory.exists()) {
                    directory.mkdirs(); 
                }
                String fileName = filePath + "Recu_" + emprunt.getIdLivreEmprunt() + ".pdf";

                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(fileName));
                document.open();

                Paragraph title = new Paragraph("Reçu de Retour", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18));
                title.setAlignment(Element.ALIGN_CENTER);
                document.add(title);

                document.add(new Paragraph("\n"));

                PdfPTable table = new PdfPTable(2); 
                table.setWidthPercentage(100);

                String nomComplet = model.rechercherMembreId(emprunt.getIdMembre()).getNom() + " "
	                    + model.rechercherMembreId(emprunt.getIdMembre()).getPrenom();
                
                table.addCell("Détails");
                table.addCell("Informations");

                table.addCell("ID Emprunt");
                table.addCell(String.valueOf(emprunt.getIdLivreEmprunt()));
                table.addCell("Titre du Livre");
                table.addCell(emprunt.getTitre());
                table.addCell("ID Livre");
                table.addCell(String.valueOf(emprunt.getIdLivre()));
                table.addCell("Nom du Membre");
                table.addCell(nomComplet);
                table.addCell("Date d'Emprunt");
                table.addCell(emprunt.getDateEmprunt());
                table.addCell("Date de Retour");
                table.addCell(emprunt.getDateRetour());
                table.addCell("Pénalité");
                table.addCell(String.format("%.2f%%", emprunt.getPenalité()));
                table.addCell("Prix Total");
                table.addCell(String.format("%.2f", emprunt.getPrixTotale()));
                table.addCell("État");
                table.addCell(emprunt.getEtat());

                document.add(table);

                document.close();

                JOptionPane.showMessageDialog(null,
                        "Reçu généré avec succès :\n" + fileName,
                        "Génération Reçu",
                        JOptionPane.INFORMATION_MESSAGE);

            } catch (EmpruntNotFoundException | MemberNotFoundException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (DocumentException | IOException e) {
                JOptionPane.showMessageDialog(null,
                        "Erreur lors de la génération du reçu : " + e.getMessage(),
                        "Erreur",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null,
                    "Veuillez sélectionner un emprunt pour générer un reçu.",
                    "Erreur",
                    JOptionPane.WARNING_MESSAGE);
        }
    }
    
   


}
