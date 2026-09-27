package model;

public abstract class Livre {

    private String titre;
    private String auteur;
    private String editeur;
    private int idLivre;
    private int datePublication;
    private String categorie;
    private String langue;
    private int quantite;
    private float prix;
    

    public Livre(String titre, String auteur, String editeur, int idLivre, int datePublication, String categorie, String langue, int quantite, float prix) {
        this.titre = titre;
        this.auteur = auteur;
        this.editeur = editeur;
        this.idLivre = idLivre;
        this.datePublication = datePublication;
        this.categorie = categorie;
        this.langue = langue;
        this.quantite = quantite;
        this.prix = prix;
    }
    
    

    public float getPrix() {
		return prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}

	public String getTitre() {
        return titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public String getEditeur() {
        return editeur;
    }

    public int getIdLivre() {
        return idLivre;
    }

    public int getDatePublication() {
        return datePublication;
    }
    public String getDatePublicationInput() {
        return Integer.toString(datePublication);
    }

    public String getCategorie() {
        return categorie;
    }

    public String getLangue() {
        return langue;
    }

    public int getQuantite() {
        return quantite;
    }
    public String getQuantiteInput() {
        return Integer.toString(quantite);
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public void setEditeur(String editeur) {
        this.editeur = editeur;
    }

    public void setIdLivre(int idLivre) {
        this.idLivre = idLivre;
    }

    public void setDatePublication(int datePublication) {
        this.datePublication = datePublication;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

	public String toStringForRecherche() {
		return "Livre [titre=" + titre + ", auteur=" + auteur + ", editeur=" + editeur + ", idLivre=" + idLivre
				+ ", datePublication=" + datePublication + ", categorie=" + categorie + ", langue=" + langue
				+ ", quantite=" + quantite + ", prix=" + prix + "]";
	}

    @Override
    public String toString() {
        return  idLivre + " - "+titre;
    }
    public Livre() {
    }
    
    public Object[] toRow() {
        return new Object[]{idLivre, titre, auteur, editeur, datePublication, categorie, langue, quantite,prix};
    }

    
    
}
