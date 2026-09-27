package model;
import java.util.List;


import exception.*;

public interface BibliothecaireModelInterface {

    public void lireData() throws DataNotFoundException;
    public List<LivreModel> listerLivres();
    public List<MembreModel> listerAdherents();
    public List<LivreEmprunte> listerEmprunts();

    /*=================Livres============================ */
    
    public void ajouterLivre(LivreModel livre) throws AddLivreException;
    public void supprimerLivre(LivreModel livre) throws BookNotFoundException;
    public void modifierLivre(LivreModel livre);
    public LivreModel rechercherLivreId(int id);
    
    public void retournerLivre(LivreEmprunte livre);
    public void supprimerLivre(int id) throws BookNotFoundException;

    /*=================Adherents============================ */
    
    public void ajouterMembre(MembreModel membre) throws AddMemberException;
    public void supprimerMembre(MembreModel membre)throws MemberNotFoundException;
    public void modifierMembre(MembreModel membre);
    public MembreModel rechercherMembreId(int id)throws MemberNotFoundException;

    /*=================Emprunts============================ */
    
    public void ajouterLivreEmprunte(LivreEmprunte le);
    public LivreEmprunte rechercherLivreEmprunteId(int id) throws EmpruntNotFoundException;

    
    /*=================Bibliothecaire============================ */
    
    public void ajouterBibliothecaire(BibliothecaireModel bibliothecaire) throws AddLibrarianException;
    public void supprimerBibliothecaire(BibliothecaireModel bibliothecaire)throws BibliothecaireNotFoundException;
    public void modifierBibliothecaire(BibliothecaireModel bibliothecaire);
    public BibliothecaireModel rechercherBibliothecaireId(int id)throws BibliothecaireNotFoundException;
    public void saveData();
    
    /*======================== UTILISATEURS ======================*/
    public List<Integer> getIdsUtilisateurs();
    
    /*======================== Administrateurs ===================*/
    
    public void ajouterAdministrateur(AdministrateurModel a)throws AddAdministratorException;
    public void modifierAdministrateur(AdministrateurModel a);
    public void supprimerAdministrateur(AdministrateurModel a) throws AdministrateurNotFoundException;
    public AdministrateurModel rechercherAdministrateurId(int id)throws AdministrateurNotFoundException;
}
