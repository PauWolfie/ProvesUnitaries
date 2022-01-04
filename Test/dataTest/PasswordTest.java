package dataTest;

import data.Password;
import data.exceptions.EmptyException;
import data.exceptions.WrongFormedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PasswordTest implements DataTestInterface {

    Password pswd;
    Password pswd2;

    @BeforeEach
    void setPswd() throws EmptyException, WrongFormedException {
        pswd = new Password("hola1234");
        pswd2 = new Password("55pa2KuTf96Sbv");
    }

    @Test
    public void correctTest() throws WrongFormedException, EmptyException {
        assertEquals("hola1234", pswd.getPswd());
    }

    @Test
    public void emptyTest() {
        Throwable exception1 = assertThrows(EmptyException.class,
                () -> {
                    pswd = new Password("");
                });
        Throwable exception2 = assertThrows(NullPointerException.class,
                () -> {
                    pswd = new Password(null);
                });

        assertEquals("La contrassenya no pot estar buida", exception1.getMessage());
        assertEquals("Null reference", exception2.getMessage());
    }

    @Test
    public void wrongFormatTest() {Throwable exception1 = assertThrows(WrongFormedException.class,
            () -> {
                pswd = new Password("123");
            });
        assertEquals("La contrassenya ha de tenir al menys 8 caràcters", exception1.getMessage());
    }

    @Test
    public void compareTest() throws WrongFormedException, EmptyException {
        assertEquals(pswd, pswd);
        assertNotEquals("hola1234", pswd);
        assertNotEquals(pswd, pswd2);
    }
}
