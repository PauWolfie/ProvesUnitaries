package dataTest;

import data.DocPath;
import exceptions.EmptyException;
import exceptions.WrongFormedException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class DocPathTest implements dataTestInterface{
    /**
     * Comprova la correcta implementació per al cas on el directori és correcte
     */
    @Test
    public void correctTest() throws WrongFormedException, EmptyException {
        DocPath path = new DocPath("EmulatedDirectory/");
        assertEquals("EmulatedDirectory/", path.getPath());
    }

    /**
     * Comprova la correcta execució de les excepcions en el cas que el directori passt sigui nul, no contingui res.
     **/
    @Test
    public void emptyTest() {
        Throwable exception1 = assertThrows(EmptyException.class,
                () -> {
                    DocPath path = new DocPath("");
                });
        Throwable exception2 = assertThrows(NullPointerException.class,
                () -> {
                    DocPath doc = new DocPath(null);
                });

        assertEquals("El directori no pot estar buit", exception1.getMessage());
        assertEquals("Null reference", exception2.getMessage());
    }
    /**
     * Comprova la correcta execució de les excepcions en el cas que el directori no existeixi.
     **/
    @Test
    public void wrongFormatTest() {
        Throwable exception = assertThrows(WrongFormedException.class,
                () -> {
                    DocPath doc = new DocPath("No existeixo");
                });
        assertEquals("El directori no existeix", exception.getMessage());
    }

    /**
     * Comprova la correcta execució del mètode equals.
     */
    @Test
    public void compareTest() throws WrongFormedException, EmptyException {
        DocPath path1 = new DocPath("src/");
        DocPath path2 = new DocPath("EmulatedDirectory/");

        assertEquals(path1, path1);
        assertNotEquals("src/", path1);
        assertNotEquals(path1, path2);
    }
}
