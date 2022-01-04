package dataTest;

import data.DocPath;
import data.exceptions.EmptyException;
import data.exceptions.WrongFormedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class DocPathTest implements DataTestInterface {
    DocPath path;
    DocPath path2;

    @BeforeEach
    void setPath() throws EmptyException, WrongFormedException {
        path = new DocPath("Test/");
        path2 = new DocPath("src/");

    }
    @Test
    public void correctTest() throws WrongFormedException, EmptyException {
        assertEquals("Test/", path.getPath());
    }


    @Test
    public void emptyTest() {
        Throwable exception1 = assertThrows(EmptyException.class,
                () -> {
                    path = new DocPath("");
                });
        Throwable exception2 = assertThrows(NullPointerException.class,
                () -> {
                    path = new DocPath(null);
                });

        assertEquals("El directori no pot estar buit", exception1.getMessage());
        assertEquals("Null reference", exception2.getMessage());
    }

    @Test
    public void wrongFormatTest() {
        Throwable exception = assertThrows(WrongFormedException.class,
                () -> {
                    path = new DocPath("No existeixo");
                });
        assertEquals("El directori no existeix", exception.getMessage());
    }

    @Test
    public void compareTest() throws WrongFormedException, EmptyException {

        assertEquals(path, path);
        assertNotEquals("src/", path);
        assertNotEquals(path, path2);
    }
}
