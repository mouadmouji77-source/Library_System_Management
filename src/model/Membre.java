package model;

public abstract class Membre extends Utilisateur {
    private String nom;
    private String prenom;
    private String email;
    private String CIN;

    public Membre(String nom, String prenom, String email, int idMembre, String cin) {
    	super("Membre",idMembre);
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.CIN = cin;
    }

    public Membre() {
    	super("Membre",-1);
    	
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }


    public int getIdMembre() {
        return getId();
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIdMembre(int idMembre) {
        this.setId(idMembre);
    }

    public String getCIN() {
		return CIN;
	}

	public void setCIN(String cIN) {
		CIN = cIN;
	}

	@Override
    public String toString() {
        return "Membre{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", role='" + getRole() + '\'' +
                ", idMembre=" + getId() +
                ", CIN=" + getCIN() +
                '}';
    }
    
    public Object[] toRow() {
        return new Object[]{getId(), nom, prenom, email, getRole(),CIN};
    }

}
