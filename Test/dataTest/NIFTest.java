package dataTest;

import data.Nif;
import data.exceptions.EmptyException;
import data.exceptions.WrongFormedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NIFTest implements DataTestInterface {
    Nif nif;
    Nif nif2;

    @BeforeEach
    void setNif() throws EmptyException, WrongFormedException {
        nif = new Nif("48052867W");
        nif2 = new Nif("46852104T");
    }

    @Test
    public void correctTest() throws WrongFormedException, EmptyException {
        assertEquals("48052867W", nif.getNif());
    }

    @Test
    public void emptyTest() {
        Throwable exception1 = assertThrows(EmptyException.class,
                () -> {
                    nif = new Nif("");
                });
        Throwable exception2 = assertThrows(NullPointerException.class,
                () -> {
                    nif = new Nif(null);
                });

        assertEquals("El NIF no pot estar buit", exception1.getMessage());
        assertEquals("Null reference", exception2.getMessage());
    }

    @Test
    public void wrongFormatTest() {
        Throwable exception1 = assertThrows(WrongFormedException.class,
                () -> {
                    nif = new Nif("48052867WWW");
                });
        Throwable exception2 = assertThrows(WrongFormedException.class,
                () -> {
                    nif = new Nif("4805286XW");
                });
        Throwable exception3 = assertThrows(WrongFormedException.class,
                () -> {
                    nif = new Nif("480528675");
                });

        assertEquals("El NIF ha de tenir 8 caràcters", exception1.getMessage());
        assertEquals("Els 7 primers caràcters han de ser nombres", exception2.getMessage());
        assertEquals("L'últim caràcter ha de ser una lletra", exception3.getMessage());
    }

    @Test
    public void compareTest() throws WrongFormedException, EmptyException {
        assertEquals(nif, nif);
        assertNotEquals("48052867W", nif);
        assertNotEquals(nif, nif2);
    }
}
