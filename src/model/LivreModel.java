package model;

public class LivreModel extends Livre implements LivreInterface {

	public LivreModel(String titre, String auteur, String editeur, int idLivre, int datePublication, String categorie, String langue, int quantite, float prix) {
        super(titre,auteur,editeur, idLivre, datePublication,categorie, langue,quantite, prix);
    }
	
    public LivreModel() {
    	super();
    }
    
	
	@Override
	public void modifierLivre(Livre livre) {
		this.setAuteur(livre.getAuteur());
		this.setCategorie(livre.getCategorie());
		this.setDatePublication(livre.getDatePublication());
		this.setEditeur(livre.getEditeur());
		this.setLangue(livre.getLangue());
		this.setPrix(livre.getPrix());
		this.setQuantite(livre.getQuantite());
		this.setTitre(livre.getTitre());
		
	}

	@Override
	public void retournerLivre() {
		this.setQuantite(1+ getQuantite());
		
	}
	
	

}
