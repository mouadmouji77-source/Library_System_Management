package model;

public class LivreEmprunteModel extends LivreEmprunte implements LivreEmprunteInterface{
	
	//on peut fonctionner sans cette classe ( c'est une sorte de Livre , pas besoin d'avoir un model 
	
	public LivreEmprunteModel(LivreModel livre, int idEmp, int idMembre2, String dateEmprunt2, String dateRetour2) {
		super(livre, idEmp, idMembre2, dateEmprunt2, dateRetour2);
		// TODO Auto-generated constructor stub
	}
	
}
