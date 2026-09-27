package model;

public class MembreModel extends Membre implements MembreInterface {

	 public MembreModel(String nom, String prenom, String email, int idMembre, String cin) {
	    	super(nom,prenom,email,idMembre, cin);
	       
	    }

	    public MembreModel() {
	    	super();
	    	
	    }
	@Override
	public void modifierMembre(Membre membre) {
		this.setCIN(membre.getCIN());
		this.setEmail(membre.getEmail());
		this.setNom(membre.getNom());
		this.setPrenom(membre.getPrenom());
		
	}
	
}
