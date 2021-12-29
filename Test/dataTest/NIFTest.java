package dataTest;

import data.Nif;
import exceptions.EmptyException;
import exceptions.WrongFormedException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("ALL")
public class NIFTest implements dataTestInterface {

    @Test
    /**
     * Comprova la correcta implementació per al cas on les dades introduides són correctes
     *
     * @param nif guarda el nif correcte.
     */
    public void correctTest() throws WrongFormedException, EmptyException {
        Nif nif = new Nif("48052867W");
        assertEquals("48052867W", nif.getNif());
    }

    @Test
    /**
     * Comprova la correcta execució de les excepcions en el cas que la referència passada sigui nul·la o no contingui res.
     *
     * @param exception1 guarda la excepció retornada a l'hora de cridar al constructor amb null.
     * @param exception2 guarda la excepció retornada a l'hora de cridar al constructor amb una referència buida.
     **/
    public void emptyTest() {
        Throwable exception1 = assertThrows(EmptyException.class,
                () -> {
                    Nif nif = new Nif("");
                });
        Throwable exception2 = assertThrows(NullPointerException.class,
                () -> {
                    Nif nif = new Nif(null);
                });

        assertEquals("El NIF no pot estar buit", exception1.getMessage());
        assertEquals("Null reference", exception2.getMessage());
    }

    @Test
    /**
     * Comprova la correcta execució de les excepcions en els casos que el DNI estigui mal format:
     *  -Cas 1: El NIF no té 8 caràcters.
     *  -Cas 2: Els 7 primers caràcters del NIF no son nombres.
     *  -Cas 3: L'últim caràcter del NIF ha de ser una lletra.
     *
     * @param exception1 guarda la excepció retornada a l'hora de cridar al constructor en l'escenari del cas 1.
     * @param exception2 guarda la excepció retornada a l'hora de cridar al constructor en l'escenari del cas 2.
     * @param exception3 guarda la excepció retornada a l'hora de cridar al constructor en l'escenari del cas 3.
     */
    public void wrongFormatTest() {
        Throwable exception1 = assertThrows(WrongFormedException.class,
                () -> {
                    Nif nif = new Nif("48052867WWW");
                });
        Throwable exception2 = assertThrows(WrongFormedException.class,
                () -> {
                    Nif nif = new Nif("4805286XW");
                });
        Throwable exception3 = assertThrows(WrongFormedException.class,
                () -> {
                    Nif nif = new Nif("480528675");
                });

        assertEquals("El NIF ha de tenir 8 caràcters", exception1.getMessage());
        assertEquals("Els 7 primers caràcters han de ser nombres", exception2.getMessage());
        assertEquals("L'últim caràcter ha de ser una lletra", exception3.getMessage());
    }

    @Test
    /**
     * Comprova la correcta execució del mètode equals.
     *
     * @param nif1 guarda un NIF a ser comparat.
     * @param nif2 guarda un NIF diferent a nif1 per a ser comparat.
     */
    public void compareTest() throws WrongFormedException, EmptyException {
        Nif nif1 = new Nif("48052867W");
        Nif nif2 = new Nif("46852104T");

        assertEquals(nif1, nif1);
        assertNotEquals("48052867W", nif1);
        assertNotEquals(nif1, nif2);
    }
}
