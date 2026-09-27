package model;

import java.util.List;

public abstract class Bibliothecaire extends Utilisateur {
	
    private String nom;
    private String prenom;
    private String adresse;
    private String email;
    private String telephone;
    private String motDePasse;
    
    protected List<BibliothecaireModel> bibliothecaires;
    protected List<AdministrateurModel> administrateurs;
	protected List<LivreModel> livres;
    protected List<MembreModel> membres;
    protected List<LivreEmprunte> livresEmpruntes;

    
    
    public Bibliothecaire(int id,String nom, String prenom, String adresse, String email, String telephone, String motDePasse) {
        super("Bibliothécaire",id);
    	this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.email = email;
        this.telephone = telephone;
        this.motDePasse = motDePasse;
        
    }

    public Bibliothecaire() {
    	super("Bibliothécaire",-1);
    }
    
    

    public List<AdministrateurModel> getAdministrateurs() {
		return administrateurs;
	}

	public void setAdministrateurs(List<AdministrateurModel> administrateurs) {
		this.administrateurs = administrateurs;
	}

	public String getNom() {
        return nom;
    }
    
    public List<BibliothecaireModel> getBibliothecaires() {
		return bibliothecaires;
	}

	public void setBibliothecaires(List<BibliothecaireModel> bibliothecaires) {
		this.bibliothecaires = bibliothecaires;
	}

    public String getPrenom() {
        return prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getEmail() {
        return email;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public List<LivreModel> getLivres() {
        return livres;
    }

    public void setLivres(List<LivreModel> livres) {
        this.livres = livres;
    }

    public List<MembreModel> getMembres() {
        return membres;
    }

    public void setMembres(List<MembreModel> membres) {
        this.membres = membres;
    }

    public List<LivreEmprunte> getLivresEmpruntes() {
        return livresEmpruntes;
    }

    public void setLivresEmpruntes(List<LivreEmprunte> livresEmpruntes) {
        this.livresEmpruntes = livresEmpruntes;
    }

    
    @Override
    public String toString() {
        return "Bibliothecaire{" + "nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse + ", email=" + email + ", telephone=" + telephone + ", motDePasse=" + motDePasse + '}';
    }

    public Object[] toRow() {
        return new Object[]{getId(), nom, prenom, adresse,email,telephone,motDePasse, getRole()};
    }
}
