package model;

import java.text.SimpleDateFormat;
import java.util.Date;

import view.EmprunterLivreFormulaire;

public class LivreEmprunte extends LivreModel implements LivreEmprunteInterface{
    private String dateEmprunt;
    private String dateRetour;
    private int idMembre;
    private float penalité;
    private float prixTotale;
    private int idLivreEmprunt;
    private String etat;

    public LivreEmprunte(String titre, String auteur, String editeur, int idLivre, int datePublication, String categorie, String langue, int quantite,float prix, String dateEmprunt, String dateRetour, int idMembre) {
       super( titre, auteur, editeur, idLivre, datePublication, categorie, langue, quantite,prix);
        this.dateEmprunt = dateEmprunt;
        this.dateRetour = dateRetour;
        this.idMembre = idMembre;
        etat = "En cours";
        updatePenalite();
    }


	public LivreEmprunte(LivreModel livre, int idEmp, int idMembre2, String dateEmprunt2, String dateRetour2) {
        super(livre.getTitre(), livre.getAuteur(), livre.getEditeur(), livre.getIdLivre(), livre.getDatePublication(), livre.getCategorie(), livre.getLangue(), livre.getQuantite(),livre.getPrix());
        this.dateEmprunt = dateEmprunt2;
        this.dateRetour = dateRetour2;
        this.idMembre = idMembre2;
        this.idLivreEmprunt = idEmp;
        etat = "En cours";
        updatePenalite();
    }
	
	public LivreEmprunte(LivreModel livre,  int idEmp,int idMembre2, String dateEmprunt2, String dateRetour2, float penalite, float prix,String etat) {
        super(livre.getTitre(), livre.getAuteur(), livre.getEditeur(), livre.getIdLivre(), livre.getDatePublication(), livre.getCategorie(), livre.getLangue(), livre.getQuantite(),livre.getPrix());
        this.dateEmprunt = dateEmprunt2;
        this.dateRetour = dateRetour2;
        this.idMembre = idMembre2;
        this.penalité = penalite;
        this.prixTotale = prix;
        this.idLivreEmprunt = idEmp;
        this.etat = etat;
        updatePenalite();
    }
	
	
	
	public String getEtat() {
		return etat;
	}


	public void setEtat(String etat) {
		this.etat = etat;
	}


	public int getIdLivreEmprunt() {
		return idLivreEmprunt;
	}


	public void setIdLivreEmprunt(int idLivreEmprunt) {
		this.idLivreEmprunt = idLivreEmprunt;
	}


	public float getPrixTotale() {
		return prixTotale;
	}


	public void setPrixTotale(float prix) {
		
		this.prixTotale = prix;
	}

    public float getPenalité() {
		return penalité;
	}


	public void setPenalité(float penalité) {
		this.penalité = penalité;
	}

    public String getDateEmprunt() {
        return dateEmprunt;
    }

    public String getDateRetour() {
        return dateRetour;
    }

    public int getIdMembre() {
        return idMembre;
    }

    public void setDateEmprunt(String dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
        updatePenalite();
    }

    public void setDateRetour(String dateRetour) {
        this.dateRetour = dateRetour;
        updatePenalite();
    }

    public void setIdMembre(int idMembre) {
        this.idMembre = idMembre;
    }

    @Override
    public String toString() {

        return super.toStringForRecherche() + "LivreEmprunte{" +
                "dateEmprunt='" + dateEmprunt + '\'' +
                ", dateRetour='" + dateRetour + '\'' +
                ", idMembre=" + idMembre +
                ", penalité=" + penalité +
                '}';
    }

    public Object[] toRow() {
    	
        return new Object[]{idLivreEmprunt, this.getIdLivre(),this.getTitre(), idMembre, dateEmprunt, dateRetour, penalité,prixTotale,etat};
    }
    @Override
    public void calculerPrix(int jours) {
        if (jours <= 0) { // Minimum 1 day
            jours = 1;
        }
        prixTotale = prixTotale+ (super.getPrix() * (penalité / 100) * jours) + super.getPrix();
    }

    @Override
    public void updatePenalite() {
        if (etat.equalsIgnoreCase("En cours")) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            try {
                Date currentDate = new Date();
                Date retourDate = sdf.parse(dateRetour);
                Date emprunteDate = sdf.parse(dateEmprunt);

                long diffInMillis;
                int joursUtilises;

                if (currentDate.after(retourDate)) {
                    // User is overdue
                    diffInMillis = currentDate.getTime() - retourDate.getTime();
                    long diffInMillisEmrpunt = retourDate.getTime() - emprunteDate.getTime();
                    long overdueDays = diffInMillis / (1000 * 60 * 60 * 24);
                    long joursHorsDelay = diffInMillisEmrpunt / (1000 * 60 * 60 * 24);
                    
                    prixTotale = this.getPrix()*(int)joursHorsDelay;
                    
                    penalité =7.00f;
                    joursUtilises = (int) overdueDays;
                    
                    calculerPrix(joursUtilises);
                } else {
                    diffInMillis = retourDate.getTime() - emprunteDate.getTime();
                    joursUtilises = (int) (diffInMillis / (1000 * 60 * 60 * 24));
                    // on va aller de "ms" jusqu'a "jour" 
					//  1min = 1000ms*60
					//  1h = 1min * 60
					//  1 j = 1h * 24 
					// ==> 1j = 24 * (60 *(1000 * 60)) 
                    penalité = 0.0f; 
                    calculerPrix(joursUtilises);
                }

            } catch (Exception e) {
                System.out.println("Erreur dans updatePenalite: " + e.getMessage());
            }
        }
    }
		
    
   
}
