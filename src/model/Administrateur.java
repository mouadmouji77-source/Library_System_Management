package model;

public abstract class Administrateur extends Utilisateur {

    private String nom;
    private String prenom;
    private String email;
    

    public Administrateur(String nom, String prenom, String email,int id) {
    	super("Administrateur",id);
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }

    public Administrateur() {
    	super("Administrateur",-1);
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

    

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public Object[] toRow() {
        return new Object[]{getId(), nom, prenom, email, getRole()};
    }

    @Override
    public String toString() {
        return "Administrateur{ ID="+getId()+ "nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", role=" + getRole()+'}';
    }




}
