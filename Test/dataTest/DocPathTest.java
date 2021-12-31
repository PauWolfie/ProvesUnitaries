package dataTest;

import data.DocPath;
import exceptions.dataExceptions.EmptyException;
import exceptions.dataExceptions.WrongFormedException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class DocPathTest implements dataTestInterface{

    @Test
    public void correctTest() throws WrongFormedException, EmptyException {
        DocPath path = new DocPath("EmulatedDirectory/");
        assertEquals("EmulatedDirectory/", path.getPath());
    }


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

    @Test
    public void wrongFormatTest() {
        Throwable exception = assertThrows(WrongFormedException.class,
                () -> {
                    DocPath doc = new DocPath("No existeixo");
                });
        assertEquals("El directori no existeix", exception.getMessage());
    }

    @Test
    public void compareTest() throws WrongFormedException, EmptyException {
        DocPath path1 = new DocPath("src/");
        DocPath path2 = new DocPath("EmulatedDirectory/");

        assertEquals(path1, path1);
        assertNotEquals("src/", path1);
        assertNotEquals(path1, path2);
    }
}
