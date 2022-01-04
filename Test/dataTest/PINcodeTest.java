package dataTest;

import data.PINcode;
import data.exceptions.EmptyException;
import data.exceptions.WrongFormedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PINcodeTest implements DataTestInterface {

    int MAX;
    PINcode pin;
    PINcode pin2;

    @BeforeEach
    void setUp() throws EmptyException, WrongFormedException {
        MAX = 999;
        pin = new PINcode(762);
        pin2 = new PINcode(991);
    }


    @Test
    public void correctTest() throws WrongFormedException, EmptyException {
        for (int i = 100; i < MAX; i++){
            pin = new PINcode(i);
            assertEquals(Integer.toString(i) , pin.getPINcode());
        }
    }

    @Test
    public void emptyTest() {
        Throwable exception = assertThrows(EmptyException.class,
                () -> {
                    pin = new PINcode(0);
                });
        assertEquals("El PIN no pot estar buit",exception.getMessage());
    }

    @Test
    public void wrongFormatTest() {
        Throwable exception1 = assertThrows(WrongFormedException.class,
                () -> {
                    pin = new PINcode(1);
                });
        Throwable exception2 = assertThrows(WrongFormedException.class,
                () -> {
                    pin = new PINcode(12);
                });
        Throwable exception3 = assertThrows(WrongFormedException.class,
                () -> {
                    pin = new PINcode(1234);
                });

        assertEquals("El PIN ha de tenir 3 nombres, ara en té: 1",exception1.getMessage());
        assertEquals("El PIN ha de tenir 3 nombres, ara en té: 2",exception2.getMessage());
        assertEquals("El PIN ha de tenir 3 nombres, ara en té: 4",exception3.getMessage());
    }


    @Test
    public void compareTest() throws WrongFormedException, EmptyException {
        assertEquals(pin, pin);
        assertNotEquals(762, pin);
        assertNotEquals(pin, pin2);
    }
}
