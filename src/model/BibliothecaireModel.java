package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.*;
import exception.*;

/**
 * BibliothécaireModel est une classe qui hérite de la classe Bibliothecaire et implémente l'interface BibliothecaireInterface.
 * Cette classe permet de gérer les ressource d'une bibliothéque : les membres, les admins, les bibliothécairse ils mêmes, les livres, les emprunts.
 * 
 *@author EL HIZABRI MAROUANE
 *@author BEZZAZ ABDELFATTAH
 *@author YAMMOURI AYOUB
 *@author MOUJI MOUAD
 */

public class BibliothecaireModel extends Bibliothecaire implements BibliothecaireInterface{
	/**
     * Constructeur qui initialise un bibliothécaire avec les informations fournies.
     *
     * @param id L'ID du bibliothécaire.
     * @param nom Le nom du bibliothécaire.
     * @param prenom Le prénom du bibliothécaire.
     * @param adresse L'adresse du bibliothécaire.
     * @param email L'email du bibliothécaire.
     * @param telephone Le numéro de téléphone du bibliothécaire.
     * @param motDePasse Le mot de passe du bibliothécaire.
     */
	public BibliothecaireModel(int id,String nom, String prenom, String adresse, String email, String telephone, String motDePasse) {
        super(id, nom,  prenom,  adresse,  email,  telephone,  motDePasse);
        
    }
	
	/**
     * Constructeur sans paramètres.
     */
    public BibliothecaireModel() {
    	super();
    }
    /**
     * Lit les données des fichiers CSV et les charge dans les listes correspondantes (livres, membres, administrateurs, livresEmpruntes , bibliothécaires)
     * Lance `DataNotFoundException` si les fichiers nécessaires ne sont pas trouvés
     *
     * @throws DataNotFoundException Si l'un des fichiers de données nécessaires est manquant
     */
	@Override	
	public void lireData() throws DataNotFoundException {
	    
	    String basePath = System.getProperty("user.dir") + "//Data//";

	    fichierExiste(basePath + "Livres.csv");
	    fichierExiste(basePath + "LivresEmpruntes.csv");
	    fichierExiste(basePath + "Membres.csv");
	    fichierExiste(basePath + "Bibliothecaires.csv");
	    fichierExiste(basePath + "Administrateurs.csv");

	    String path = basePath + "Livres.csv";
	    Pattern pattern = Pattern.compile(",");
	    try (Stream<String> lines = Files.lines(Path.of(path))) {
	        List<LivreModel> livres = lines.skip(1).map(line -> {
	            String[] items = pattern.split(line);
	            return new LivreModel(
	                items[0],
	                items[1],
	                items[2],
	                Integer.parseInt(items[3]),
	                Integer.parseInt(items[4]),
	                items[5],
	                items[6],
	                Integer.parseInt(items[7]),
	                Float.parseFloat(items[8])
	            );
	        }).collect(Collectors.toList());

	        if (livres.isEmpty()) {
	            livres = new ArrayList<>();
	        }

	        setLivres(livres);
	    } catch (IOException e) {
	        throw new DataNotFoundException("Fichier Livres.csv not found");
	    }

	    String path2 = basePath + "LivresEmpruntes.csv";
	    Pattern pattern2 = Pattern.compile(",");
	    try (Stream<String> lines = Files.lines(Path.of(path2))) {
	        List<LivreEmprunte> livres = lines.skip(1).map(line -> {
	            String[] items = pattern2.split(line);
	            LivreModel l = rechercherLivreId(Integer.parseInt(items[1].trim()));

	            return new LivreEmprunte(
	                l,
	                Integer.parseInt(items[0].trim()),
	                Integer.parseInt(items[2].trim()),
	                items[3].trim(),
	                items[4].trim(),
	                Float.parseFloat(items[5].trim()),
	                Float.parseFloat(items[6].trim()),
	                items[7].trim()
	            );
	        }).filter(Objects::nonNull).collect(Collectors.toList());

	        if (livres.isEmpty()) {
	            livres = new ArrayList<>();
	        }

	        setLivresEmpruntes(livres);
	    } catch (IOException e) {
	        throw new DataNotFoundException("Fichier LivresEmpruntes.csv not found");
	    }

	    String path3 = basePath + "Membres.csv";
	    Pattern pattern3 = Pattern.compile(",");
	    try (Stream<String> lines = Files.lines(Path.of(path3))) {
	        List<MembreModel> membres = lines.skip(1).map(line -> {
	            String[] items = pattern3.split(line);
	            if (items.length < 4) {
	                System.err.println("Invalid line format: " + line);
	                return null;
	            }

	            return new MembreModel(
	                items[1].trim(),
	                items[2].trim(),
	                items[3].trim(),
	                Integer.parseInt(items[0].trim()),
	                items[4].trim()
	            );
	        }).filter(Objects::nonNull).collect(Collectors.toList());

	        if (membres.isEmpty()) {
	            membres = new ArrayList<>();
	        }

	        setMembres(membres);
	    } catch (IOException e) {
	        throw new DataNotFoundException("Fichier Membres.csv not found");
	    }

	    String path4 = basePath + "Bibliothecaires.csv";
	    Pattern pattern4 = Pattern.compile(",");
	    try (Stream<String> lines = Files.lines(Path.of(path4))) {
	        List<BibliothecaireModel> bibliothecaires = lines.skip(1).map(line -> {
	            String[] items = pattern4.split(line);
	            if (items.length < 8) {
	                System.err.println("Invalid line format: " + line);
	                return null;
	            }

	            return new BibliothecaireModel(
	                Integer.parseInt(items[0].trim()),
	                items[1].trim(),
	                items[2].trim(),
	                items[3].trim(),
	                items[4].trim(),
	                items[5].trim(),
	                items[6].trim()
	            );
	        }).filter(Objects::nonNull).collect(Collectors.toList());

	        if (bibliothecaires.isEmpty()) {
	            bibliothecaires = new ArrayList<>();
	        }

	        setBibliothecaires(bibliothecaires);
	    } catch (IOException e) {
	        throw new DataNotFoundException("Fichier Bibliothecaires.csv not found");
	    }

	    String path5 = basePath + "Administrateurs.csv";
	    Pattern pattern5 = Pattern.compile(",");
	    try (Stream<String> lines = Files.lines(Path.of(path5))) {
	        List<AdministrateurModel> administrateurs = lines.skip(1).map(line -> {
	            String[] items = pattern5.split(line);
	            if (items.length < 5) {
	                System.err.println("Invalid line format: " + line);
	                return null;
	            }

	            return new AdministrateurModel(
	                items[1].trim(),
	                items[2].trim(),
	                items[4].trim(),
	                Integer.parseInt(items[0].trim())
	            );
	        }).filter(Objects::nonNull).collect(Collectors.toList());

	        if (administrateurs.isEmpty()) {
	            administrateurs = new ArrayList<>();
	        }

	        setAdministrateurs(administrateurs);
	    } catch (IOException e) {
	        throw new DataNotFoundException("Fichier Administrateurs.csv not found");
	    }
	}

	
	/**
     * Assure que le fichier spécifié existe ou non. Dans le cas qu'il n'existe pas, cette fonction va le crée
     * @param chemin Le chemin du fichier à vérifier ou créer
     */
	private void fichierExiste(String chemin) {
	    File file = new File(chemin);
	    try {
	        if (!file.exists()) {
	            file.getParentFile().mkdirs(); 
	            file.createNewFile(); 
	        }
	    } catch (IOException e) {
	        throw new RuntimeException("impossible de créer le fichier: " + chemin, e);
	    }
	}

	/**
     * Retourne la liste des livres de la bibliothèque
     * @return une liste des livres
     */
	@Override
	public List<LivreModel> listerLivres() {
		// TODO Auto-generated method stub
		return livres;
	}
	
	/**
     * Retourne la liste des membres de la bibliothèque
     * @return liste des membres
     */

	@Override
	public List<MembreModel> listerAdherents() {
		// TODO Auto-generated method stub
		return membres;
	}

	 /**
     * Retourne la liste des livres empruntés de la bibliothèque
     * @return liste des livres empruntés
     */
	@Override
	public List<LivreEmprunte> listerEmprunts() {
		// TODO Auto-generated method stub
		return livresEmpruntes;
	}
	
	/**
     * Ajoute un livre à la bibliothèque après avoir validé ses informations
     * @param livre Le livre à ajouter
     * @throws AddLivreException Si des champs obligatoires sont manquants ou invalides
     * @throws NumberFormatException Si des valeurs numériques sont invalides( la date doit être entre 1901 et 2025, les prix et quantités doivent être positives et supérieur à 0)
     */

	@Override
	public void ajouterLivre(LivreModel livre) throws AddLivreException, NumberFormatException {
		System.out.println(livre.getDatePublicationInput());
		// TODO Auto-generated method stub
		if(livre.getAuteur() == null 
			|| livre.getCategorie() == null
			|| livre.getDatePublication() == 0
			|| livre.getEditeur() == null
			|| livre.getLangue() == null
			|| livre.getDatePublicationInput() == null 
			|| livre.getQuantite() == 0
			|| livre.getTitre() == null
			|| Float.toString(livre.getPrix()) == null
			) {
			throw new AddLivreException("Remplir tous les champs avant d'ajouter le livre, entrer des valeurs valides!");
		}else {
			if(livre.getDatePublication()<1901 || livre.getDatePublication()>2025  || livre.getQuantite() <= 0 || livre.getPrix() <= 0) {
				throw new NumberFormatException("La qunatité et la date doivent être des entiers. Le prix, la date (entre 1901 et 2025), et la quantité doivent être des nombres positives");
			}
			
		}
		this.livres.add(livre);
	}

	/**
     * Supprime un livre de la bibliothèque en utilisant LivreModel
     * @param livre Le livre à supprimer
     * @throws BookNotFoundException Si le livre n'est pas trouvé dans la liste
     */
	@Override
	public void supprimerLivre(LivreModel livre) throws BookNotFoundException {
		// TODO Auto-generated method stub
		int i = -1;
		for(Livre l : livres) {
			if(l.getIdLivre() == livre.getIdLivre()) {
				i += 1;				
			}
		}
		if(i == -1) {
			throw new BookNotFoundException("Erreur dans la suppression de Livre");
		}else {
			this.livres.remove(livre);
		}		
	}

	/**
     * Supprime un livre de la bibliothèque en utilisant ID
     * @param id L'ID du livre à supprimer
     * @throws BookNotFoundException Si le livre avec cet ID n'est pas trouvé
     */
	
	@Override
    public void supprimerLivre(int id) throws BookNotFoundException{
		int i = -1;
        for (Livre livre : this.livres) {
            if (livre.getIdLivre() == id) {
                i=1;
                this.livres.remove(livre);
                //saveData();
                break;
            }
        }
        
        if(i == -1) {
        	throw new BookNotFoundException("Livre introuvable");
        }
    }

	/**
     * Modifier un livre existant dans la bibliothèque en passant LivreModel a modifié
     * @param livre Le livre à modifier
     */
	@Override
	public void modifierLivre(LivreModel livre) {
		// TODO Auto-generated method stub
		for(LivreModel l : livres) {
			if(l.getIdLivre() == livre.getIdLivre()) {
				l.modifierLivre(livre);
			}
		}
		
	}

	 /**
     * Recherche un livre par ID
     * @param id L'ID du livre à rechercher
     * @return Le livre correspondant à l'ID, ou `null` si non trouvé
     */
	@Override
	public LivreModel rechercherLivreId(int id) {
		// TODO Auto-generated method stub
		for (LivreModel livre : this.livres) {
            if (livre.getIdLivre() == id) {
                return livre;
            }
        }
		return null;
	}

	 /**
     * Permet de retourner un livre emprunté en mettant à jour son état et en réajustant la quantité de livre
     * @param livre Le livre emprunté à retourner
     */
	@Override
	public void retournerLivre(LivreEmprunte livre) {
		for (LivreEmprunte l : this.livresEmpruntes) {
            if (l.getIdLivreEmprunt() == livre.getIdLivreEmprunt()) {
                l.setEtat("Retourné"); 
                for(LivreModel ll : livres) {
                	if(ll.getIdLivre() == l.getIdLivre()) {
                		ll.retournerLivre();
                	}
                }
            }
        }
		
	}

	/**
     * Ajoute un membre à la bibliothèque après la validation des informations entrées ( CIN doit être unique)
     * @param membre Le membre à ajouter
     * @throws AddMemberException Si des champs obligatoires sont manquants
     */
	@Override
	public void ajouterMembre(MembreModel membre) throws AddMemberException {
		
		if(membre.getCIN().isEmpty()
		   || membre.getEmail().isEmpty()
		   || membre.getNom().isEmpty()
		   || membre.getPrenom().isEmpty()
		  ) {
			throw new AddMemberException("Remplir tous les champs avant d'ajouter le membre");
		}else {
			this.membres.add(membre);
		}
		
	}

	/**
     * Supprime un membre de la bibliothèque en utilisant MembreModel
     * @param membre Le membre à supprimer
     * @throws MemberNotFoundException Si le membre n'est pas trouvé
     */
	@Override
	public void supprimerMembre(MembreModel membre) throws MemberNotFoundException {
		int i = -1;
		for(MembreModel l : membres) {
			if(l.getId() == membre.getId()) {
				this.membres.remove(membre);				
				i = 1;
				break;
			}
		}
		if(i == -1) {
			throw new MemberNotFoundException("Membre introuvable");
		}	
		
	}

	/**
     * Modifier un membre de la bibliothèque
     * @param membre Le membre à modifier
     */
	@Override
	public void modifierMembre(MembreModel membre) {
		// TODO Auto-generated method stub
		
		for (MembreModel m : this.membres) {
            if (m.getIdMembre() == membre.getIdMembre()) {
                m.modifierMembre(membre);
                break;
            }
        }
		
		
	}

	/**
     * Recherche un membre par  ID.
     * @param id L'ID du membre à rechercher
     * @return Le membre correspondant à l'ID
     * @throws MemberNotFoundException Si aucun membre n'est trouvé avec cet ID
     */
	@Override
	public MembreModel rechercherMembreId(int id) throws MemberNotFoundException {
		// TODO Auto-generated method stub
		for (MembreModel membre : this.membres) {
            if (membre.getIdMembre() == id) {
                return membre;
            }
        }
        throw new MemberNotFoundException("Aucun membre avec cet ID");
	}

	/**
	 * Ajoute un livre emprunté
	 * @param le Le livre emprunté à ajouter
	 */
	@Override
	public void ajouterLivreEmprunte(LivreEmprunte le) {
		// TODO Auto-generated method stub
		this.livresEmpruntes.add(le);
	}

	/**
	 * Ajoute un bibliothécaire à la bibliothèque après la validation des informations entrées
	 * @param bibliothecaire Le bibliothécaire à ajouter.
	 * @throws AddLibrarianException Si l'un des champs obligatoires est manquant (adresse, email, mot de passe, nom, prénom, téléphone).
	 */
	@Override
	public void ajouterBibliothecaire(BibliothecaireModel bibliothecaire) throws AddLibrarianException {
		// TODO Auto-generated method stub
		if(bibliothecaire.getAdresse() == null
		   || bibliothecaire.getEmail() == null
		   || bibliothecaire.getMotDePasse()== null
		   || bibliothecaire.getNom() == null
		   || bibliothecaire.getPrenom() == null
		   || bibliothecaire.getTelephone() == null
		   ) {
			throw new AddLibrarianException("Remplir tous les champs avant d'ajouter un bibliothécaire");
		}
		
		bibliothecaires.add(bibliothecaire);
	}

	/**
	 * Supprime un bibliothécaire de la bibliothèque en fonction de BibliothecaireModel
	 * @param bibliothecaire Le bibliothécaire à supprimer
	 * @throws BibliothecaireNotFoundException Si le bibliothécaire n'est pas trouvé dans la liste
	 */
	@Override
	public void supprimerBibliothecaire(BibliothecaireModel bibliothecaire) throws BibliothecaireNotFoundException {
		// TODO Auto-generated method stub
		int i = -1;
		for(Bibliothecaire b : bibliothecaires) {
			if(b.getId() == bibliothecaire.getId()) {
				bibliothecaires.remove(bibliothecaire);
				i = 1;
				break;
			}
		}
		
		if(i == -1) {
			throw new BibliothecaireNotFoundException("Bibliothécaire introuvable");
		}
	}

	/**
	 * Modifier bibliothécaire
	 * Met à jour les détails du bibliothécaire tels que adresse, nom, prenom, telephone, ...etc. Excepte l'ID
	 * @param bibliothecaire Le bibliothécaire avec les nouvelles informations à mettre à jour.
	 */
	@Override
	public void modifierBibliothecaire(BibliothecaireModel bibliothecaire) {
		// TODO Auto-generated method stub
		for(Bibliothecaire b : bibliothecaires) {
			if(b.getId() == bibliothecaire.getId()) {
				
				b.setAdministrateurs(bibliothecaire.getAdministrateurs());
				b.setMembres(bibliothecaire.getMembres());
				b.setLivres(bibliothecaire.getLivres());
				b.setLivresEmpruntes(bibliothecaire.getLivresEmpruntes());
				
				b.setAdresse(bibliothecaire.getAdresse());
				b.setNom(bibliothecaire.getNom());
				b.setPrenom(bibliothecaire.getPrenom());
				b.setEmail(bibliothecaire.getEmail());
				b.setTelephone(bibliothecaire.getTelephone());
				b.setMotDePasse(bibliothecaire.getMotDePasse());
				
				
				break;
			}
		}
	}

	/**
	 * Recherche un bibliothécaire par ID
	 * @param id L'ID du bibliothécaire à rechercher
	 * @return Le bibliothécaire correspondant à l'ID
	 * @throws BibliothecaireNotFoundException Si aucun bibliothécaire n'est trouvé avec cet ID
	 */
	@Override
	public BibliothecaireModel rechercherBibliothecaireId(int id) throws BibliothecaireNotFoundException {
		
		for (BibliothecaireModel b : this.bibliothecaires) {
            if (b.getId() == id) {
                return b;
            }
        }
		
		throw new BibliothecaireNotFoundException("Aucun Bibliothécaire avec cet ID");
	}

	/**
     * Sauvegarde les données actuelles dans des fichiers CSV
     */
	@Override
	public void saveData() {
		// TODO Auto-generated method stub
		if (this.membres!=null) {
        	String path = System.getProperty("user.dir") + "//Data//Membres.csv";
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
                writer.write("ID,Nom,Prenom,Email\n");
                for (MembreModel membre : membres) {
                    writer.write(membre.getIdMembre() + "," + membre.getNom() + "," + membre.getPrenom() + "," + membre.getEmail() + "," + membre.getCIN() + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
           }
	    }
	    
	    if(this.livres!=null) {
	    
	        String path2 = System.getProperty("user.dir")+"//Data//Livres.csv";
	        try {
	            BufferedWriter writer = new BufferedWriter(new FileWriter(path2));
	            writer.write("Titre,Auteur,Editeur,ID Livre,Date de Publication,Categorie,Langue,Quantite,Prix\n");
	            for (LivreModel livre : this.livres) {
	                writer.write(livre.getTitre()+","+livre.getAuteur()+","+livre.getEditeur()+","+livre.getIdLivre()+","+livre.getDatePublication()+","+livre.getCategorie()+","+livre.getLangue()+","+livre.getQuantite()+","+livre.getPrix()+"\n");
	            }
	            writer.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	    	}
	    }
	
	
	    if (this.livresEmpruntes != null) {
	        String path3 = System.getProperty("user.dir") + "//Data//LivresEmpruntes.csv";
	
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path3))) {
	            // Write the header
	            writer.write("ID Livre,ID Membre,Date Emprunt,Date Retour,Penalite,Prix,Etat\n");
	
	            // Write data rows
	            for (LivreEmprunte livreEmprunte : this.livresEmpruntes) {
	                writer.write(
	                	livreEmprunte.getIdLivreEmprunt() + "," +
	                    livreEmprunte.getIdLivre() + "," +
	                    livreEmprunte.getIdMembre() + "," +
	                    livreEmprunte.getDateEmprunt() + "," +
	                    livreEmprunte.getDateRetour() + "," +
	                    livreEmprunte.getPenalité() + "," +
	                    livreEmprunte.getPrixTotale() + ","+
	                    livreEmprunte.getEtat() + "\n"
	                );
	            }
	        } catch (IOException e) {
	            System.err.println("Error saving livresEmpruntes to file: " + e.getMessage());
	            e.printStackTrace();
	        }
	    }
	    
	    if (bibliothecaires != null) {
	        String path = System.getProperty("user.dir") + "//Data//Bibliothecaires.csv";
	
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
	            // Write the header
	            writer.write("ID,Nom,Prenom,Adresse,Email,Telephone,MotDePasse,Role\n");
	
	            // Write data rows
	            for (BibliothecaireModel bibliothecaire : bibliothecaires) {
	                writer.write(
	                    bibliothecaire.getId() + "," +
	                    bibliothecaire.getNom() + "," +
	                    bibliothecaire.getPrenom() + "," +
	                    bibliothecaire.getAdresse() + "," +
	                    bibliothecaire.getEmail() + "," +
	                    bibliothecaire.getTelephone() + "," +
	                    bibliothecaire.getMotDePasse() + "," +
	                    bibliothecaire.getRole() + "\n"
	                );
	            }
	        } catch (IOException e) {
	            System.err.println("Error saving bibliothecaires to file: " + e.getMessage());
	            e.printStackTrace();
	        }
	    }
	    
	    if (administrateurs != null) {
	        String path = System.getProperty("user.dir") + "//Data//Administrateurs.csv";
	
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
	            // Write the header
	            writer.write("ID,Nom,Prenom,Adresse,Email,Telephone,MotDePasse,Role\n");
	
	            // Write data rows
	            for (AdministrateurModel administrateur: administrateurs) {
	                writer.write(
	                				administrateur.getId() + "," +
	                				administrateur.getNom() + "," +
	                				administrateur.getPrenom() + "," +
	                				administrateur.getEmail() + "," +
	                				administrateur.getRole() + "\n"
	                );
	            }
	        } catch (IOException e) {
	            System.err.println("Error saving administrateurs to file: " + e.getMessage());
	            e.printStackTrace();
	        }
	    }
	}
	
	/**
     * Retourne la liste des IDs de tous les utilisateurs (membres, bibliothécaires et administrateurs)
     * Cette fonction est utilisée pour récupérer l'ID max pour continuer l'attribution de ID d'une manière croissante 
     * @return Une liste des IDs d'utilisateurs
     */
	@Override
	public List<Integer> getIdsUtilisateurs(){
    	return Stream.concat(
                Stream.concat(
                    membres.stream().map(Membre::getId), 
                    bibliothecaires.stream().map(Bibliothecaire::getId)
                ), 
                administrateurs.stream().map(Administrateur::getId)
            )
            .collect(Collectors.toList());
    }

	/**
     * Recherche un livre emprunté par ID
     * @param id L'ID de l'emprunt à rechercher
     * @return Le livre emprunté correspondant à l'ID
     * @throws EmpruntNotFoundException Si aucun emprunt n'est trouvé avec cet ID
     */
	@Override
	public LivreEmprunte rechercherLivreEmprunteId(int id) throws EmpruntNotFoundException {
		
	        for (LivreEmprunte livre : this.livresEmpruntes) {
	            if (livre.getIdLivreEmprunt() == id) {
	                return livre;
	            }
	        }
	        throw new EmpruntNotFoundException("Aucun Emprunt avec cet ID");
	    
		
	}

	 /**
     * Ajoute un administrateur
     * @param a L'administrateur à ajouter
     * @throws AddAdministratorException Si des champs obligatoires sont manquants
     */
	@Override
	public void ajouterAdministrateur(AdministrateurModel a) throws AddAdministratorException {
		// TODO Auto-generated method stub
		if(a.getEmail().isEmpty() || a.getNom().isEmpty() || a.getPrenom().isEmpty()) {
			throw new AddAdministratorException("Remplir tous les champs avant d'ajouter l'administrateur");
		}
		administrateurs.add(a);
	}

	/**
     * Modifier un administrateur
     * @param a L'administrateur à modifier
     */
	@Override
	public void modifierAdministrateur(AdministrateurModel a) {
		// TODO Auto-generated method stub
		for(AdministrateurModel adm : administrateurs) {
			if(adm.getId() == a.getId()) {
				adm.modifierAdministrateur(a);
				break;
			}
		}
		
	}

	/**
     * Supprime un administrateur
     * @param a L'administrateur à supprimer
     * @throws AdministrateurNotFoundException Si l'administrateur n'est pas trouvé
     */
	@Override
	public void supprimerAdministrateur(AdministrateurModel a) throws AdministrateurNotFoundException {
		// TODO Auto-generated method stub
		int i = -1;
		for(AdministrateurModel adm : administrateurs) {
			if(adm.getId() == a.getId()) {
				administrateurs.remove(a);
				i = 1;
				break;
			}
		}
		
		if(i == -1) {
			throw new AdministrateurNotFoundException("Administrateur introuvable");
		}
	
	}

	 /**
     * Recherche un administrateur par ID
     * @param id L'ID de l'administrateur à rechercher
     * @return L'administrateur correspondant à l'ID
     * @throws AdministrateurNotFoundException Si aucun administrateur n'est trouvé avec cet ID
     */
	@Override
	public AdministrateurModel rechercherAdministrateurId(int id) throws AdministrateurNotFoundException {
		for(AdministrateurModel adm : administrateurs) {
			if(adm.getId() == id) {
				return adm;
			}
		}
		
		throw new AdministrateurNotFoundException("Aucun administrateur avec cet ID");
	}
	
	/**
     * Supprime un livre emprunté
     * @param l'emprunt à supprimer
     */
	@Override
	public void supprimerLivreEmprunte(LivreEmprunte livre) {
		for (LivreEmprunte l : this.livresEmpruntes) {
            if (l.getIdLivreEmprunt() == livre.getIdLivreEmprunt()) {
            	this.livresEmpruntes.remove(l);
                for(LivreModel ll : livres) {
                	if(ll.getIdLivre() == l.getIdLivre()) {
                		ll.retournerLivre();
                	}
                }
            }
        }
	    	   	    	
	}
}
