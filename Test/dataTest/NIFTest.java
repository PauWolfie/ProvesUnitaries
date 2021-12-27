package dataTest;

import data.Nif;
import exceptions.dataExceptions.EmptyNIFException;
import exceptions.dataExceptions.WrongFormedNIFException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NIFTest {
    @Test
    void correctTest() throws WrongFormedNIFException, EmptyNIFException {
        Nif nif = new Nif("48052867W");
        assertEquals("48052867W", nif.getNif());
    }

    @Test
    void nullTest() {
        Throwable exception = assertThrows(NullPointerException.class,
                () -> {
                    Nif nif = new Nif(null);
                });
        assertEquals("Null reference",exception.getMessage());
    }

    @Test
    void emptyTest() {
        Throwable exception = assertThrows(EmptyNIFException.class,
                () -> {
                    Nif nif = new Nif("");
                });
        assertEquals("El camp NIF no pot estar buit",exception.getMessage());
    }

    @Test
    void wrongFormatTest() {
        /*
         -Cas 1: El NIF no té 8 caràcters
         -Cas 2: Els 7 primers caràcters del NIF no son nombres
         -Cas 3: L'últim caràcter del NIF ha de ser una lletra

         exception1 -> cas 1
         exception2 -> cas 2
         exception3 -> cas 3
         */
        
        Throwable exception1 = assertThrows(WrongFormedNIFException.class,
                () -> {
                    Nif nif = new Nif("48052867WWW");
                });
        Throwable exception2 = assertThrows(WrongFormedNIFException.class,
                () -> {
                    Nif nif = new Nif("4805286XW");
                });
        Throwable exception3 = assertThrows(WrongFormedNIFException.class,
                () -> {
                    Nif nif = new Nif("480528675");
                });

        assertEquals("El NIF ha de tenir 8 caràcters",exception1.getMessage());
        assertEquals("Els 7 primers caràcters han de ser nombres",exception2.getMessage());
        assertEquals("L'últim caràcter ha de ser una lletra",exception3.getMessage());
    }

    @Test
    void compareTest() throws WrongFormedNIFException, EmptyNIFException {
        Nif nif1 = new Nif("48052867W");
        Nif nif2 = new Nif("46852104T");

        assertEquals(nif1, nif1);
        assertNotEquals("48052867W", nif1);
        assertNotEquals(nif1, nif2);
    }
}
