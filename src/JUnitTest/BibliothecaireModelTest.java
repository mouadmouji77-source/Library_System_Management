package JUnitTest;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import model.BibliothecaireModel;

public class BibliothecaireModelTest {

    private BibliothecaireModel bibliothecaire;

    @Before
    public void setUp() {
        bibliothecaire = new BibliothecaireModel(1, "Marouane", "Mouadd", "adresses", "marouane@mouad.com", "08761223234", "mdp");
    }

    @Test
    public void testGetId() {
        assertEquals(1, bibliothecaire.getId());
    }

    @Test
    public void testSetId() {
        bibliothecaire.setId(2);
        assertEquals(2, bibliothecaire.getId());
    }

    @Test
    public void testGetPrenom() {
        assertEquals("Marouane", bibliothecaire.getPrenom());
    }

    @Test
    public void testSetPrenom() {
        bibliothecaire.setPrenom("Amine");
        assertEquals("Amine", bibliothecaire.getPrenom());
    }

    @Test
    public void testGetNom() {
        assertEquals("Mouadd", bibliothecaire.getNom());
    }

    @Test
    public void testSetNom() {
        bibliothecaire.setNom("Yammouri");
        assertEquals("Yammouri", bibliothecaire.getNom());
    }

    @Test
    public void testGetEmail() {
        assertEquals("marouane@mouad.com", bibliothecaire.getEmail());
    }

    @Test
    public void testSetEmail() {
        bibliothecaire.setEmail("ayoub@abdelfattah.com");
        assertEquals("ayoub@abdelfattah.com", bibliothecaire.getEmail());
    }

    @Test
    public void testGetMotDePasse() {
        assertEquals("mdp", bibliothecaire.getMotDePasse());
    }

    @Test
    public void testSetMotDePasse() {
        bibliothecaire.setMotDePasse("nouveauMotDePasse");
        assertEquals("nouveauMotDePasse", bibliothecaire.getMotDePasse());
    }

    @Test
    public void testGetRole() {
        assertEquals("Bibliothécaire", bibliothecaire.getRole());
    }

    @Test
    public void testSetRole() {
        bibliothecaire.setRole("utilisateur");
        assertEquals("utilisateur", bibliothecaire.getRole());
    }

    @Test
    public void testToString() {
        String expected = "BibliothecaireModel{ nom=Marouane, prenom=Mouadd, adresse='adresses', email=marouane@mouad.com, telephone=08761223234, motDePasse='mdp', role='Bibliothécaire'}";
        assertEquals(expected, bibliothecaire.toString());
    }
}
