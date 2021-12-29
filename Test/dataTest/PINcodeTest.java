package dataTest;

import data.Nif;
import data.PINcode;
import exceptions.EmptyException;
import exceptions.WrongFormedException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PINcodeTest implements dataTestInterface {

    public final int MAX = 999;

    /**
     * Comprova la correcta implementació per al cas on les dades introduides són correctes
     */
    @Test
    public void correctTest() throws WrongFormedException, EmptyException {
        for (int i = 100; i < MAX; i++){
            PINcode pin = new PINcode(i);
            assertEquals(Integer.toString(i) , pin.getPINcode());
        }
    }

    /**
     * Comprova la correcta execució de les excepcions en el cas que la referència passada no contingui res.
     */
    @Test
    public void emptyTest() {
        Throwable exception = assertThrows(EmptyException.class,
                () -> {
                    PINcode pin = new PINcode(0);
                });
        assertEquals("El PIN no pot estar buit",exception.getMessage());
    }

    /**
     * Comprova la correcta execució de les excepcions en el cas que el PIN no tingui el format correcte.
     *
     * Throwable exception1 guarda la excepció retornada a l'hora de cridar al constructor amb un pin d'1 digit.
     * Throwable exception2 guarda la excepció retornada a l'hora de cridar al constructor amb un pin de 2 digits.
     * Throwable exception3 guarda la excepció retornada a l'hora de cridar al constructor amb un pin de 4 digits.
     */
    @Test
    public void wrongFormatTest() {
        Throwable exception1 = assertThrows(WrongFormedException.class,
                () -> {
                    PINcode pin = new PINcode(1);
                });
        Throwable exception2 = assertThrows(WrongFormedException.class,
                () -> {
                    PINcode pin = new PINcode(12);
                });
        Throwable exception3 = assertThrows(WrongFormedException.class,
                () -> {
                    PINcode pin = new PINcode(1234);
                });

        assertEquals("El PIN ha de tenir 3 nombres, ara en té: 1",exception1.getMessage());
        assertEquals("El PIN ha de tenir 3 nombres, ara en té: 2",exception2.getMessage());
        assertEquals("El PIN ha de tenir 3 nombres, ara en té: 4",exception3.getMessage());
    }

    /**
     * Comprova la correcta execució del mètode equals.
     */
    @Test
    public void compareTest() throws WrongFormedException, EmptyException {
        PINcode pin1 = new PINcode(762);
        PINcode pin2 = new PINcode(991);

        assertEquals(pin1, pin1);
        assertNotEquals(762, pin1);
        assertNotEquals(pin1, pin2);
    }
}
