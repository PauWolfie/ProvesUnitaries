package dataTest;

import data.Password;
import exceptions.dataExceptions.EmptyException;
import exceptions.dataExceptions.WrongFormedException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PasswordTest implements dataTestInterface {


    @Test
    public void correctTest() throws WrongFormedException, EmptyException {
        Password pswd = new Password("hola1234");
        assertEquals("hola1234", pswd.getPswd());
    }

    @Test
    public void emptyTest() {
        Throwable exception1 = assertThrows(EmptyException.class,
                () -> {
                    Password pswd = new Password("");
                });
        Throwable exception2 = assertThrows(NullPointerException.class,
                () -> {
                    Password pswd = new Password(null);
                });

        assertEquals("La contrassenya no pot estar buida", exception1.getMessage());
        assertEquals("Null reference", exception2.getMessage());
    }

    @Test
    public void wrongFormatTest() {Throwable exception1 = assertThrows(WrongFormedException.class,
            () -> {
                Password pswd = new Password("123");
            });
        assertEquals("La contrassenya ha de tenir al menys 8 caràcters", exception1.getMessage());
    }

    @Test
    public void compareTest() throws WrongFormedException, EmptyException {
        Password pswd1 = new Password("23nCVs3UP74kks1");
        Password pswd2 = new Password("55pa2KuTf96Sbv");

        assertEquals(pswd1, pswd1);
        assertNotEquals("23nCVs3UP74kks1", pswd1);
        assertNotEquals(pswd1, pswd2);
    }
}
