package model;

public class AdministrateurModel extends Administrateur implements AdministrateurInterface {
	
	 public AdministrateurModel(String nom, String prenom, String email,int id) {
	    	super(nom,prenom,email,id);
	       
	    }

	    public AdministrateurModel() {
	    	super();
	    }
	
	@Override
	public void modifierAdministrateur(Administrateur a) {
		// TODO Auto-generated method stub
		this.setEmail(a.getEmail());
		this.setNom(a.getNom());
		this.setPrenom(a.getPrenom());
		
	}
	

}
