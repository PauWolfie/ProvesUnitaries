package dataTest;

import data.AccredNumb;
import data.Nif;
import exceptions.EmptyException;
import exceptions.WrongFormedException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class AccredNumbTest implements dataTestInterface{
    /**
     * Comprova la correcta implementació per al cas on les dades introduides són correctes
     */
    @Test
    public void correctTest() throws WrongFormedException, EmptyException {
        AccredNumb num = new AccredNumb("LLRO0123456789");
        assertEquals("LLRO0123456789",num.getAccNum());
    }

    /**
     * Comprova la correcta execució de les excepcions en el cas que la referència passada sigui nul·la o no contingui res.
     **/
    @Override
    public void emptyTest() {
        Throwable exception1 = assertThrows(EmptyException.class,
                () -> {
                    AccredNumb num = new AccredNumb("");
                });
        Throwable exception2 = assertThrows(NullPointerException.class,
                () -> {
                    AccredNumb num = new AccredNumb(null);
                });

        assertEquals("El nombre d'acreditació no pot estar buit", exception1.getMessage());
        assertEquals("Null reference", exception2.getMessage());
    }

    /**
     * Comprova la correcta execució de les excepcions en els casos que el nombre d'acreditació estigui mal format:
     *  -Cas 1: El nombre no té 14 caràcters.
     *  -Cas 2: Els 4 primers caràcters no son lletres.
     *  -Cas 3: Els 10 següents caràcters no son nombres.
     */
    @Test
    public void wrongFormatTest() {
        Throwable exception1 = assertThrows(WrongFormedException.class,
                () -> {
                    AccredNumb num = new AccredNumb("LLRO01234567894123254");
                });
        Throwable exception2 = assertThrows(WrongFormedException.class,
                () -> {
                    AccredNumb num = new AccredNumb("00000123456789");
                });
        Throwable exception3 = assertThrows(WrongFormedException.class,
                () -> {
                    AccredNumb num = new AccredNumb("LLROXXXXXXXXXX");
                });

        assertEquals("El nombre d'acreditació ha de tenir 14 caràcters", exception1.getMessage());
        assertEquals("Els 4 primers caràcters han de ser lletres", exception2.getMessage());
        assertEquals("Els 10 últims caràcters han de ser nombres", exception3.getMessage());
    }

    /**
     * Comprova la correcta execució del mètode equals.
     */
    @Test
    public void compareTest() throws WrongFormedException, EmptyException {
        AccredNumb num1 = new AccredNumb("LLRO0123456789");
        AccredNumb num2 = new AccredNumb("FEPE9876543210");

        assertEquals(num1, num1);
        assertNotEquals("LLRO0123456789", num1);
        assertNotEquals(num1, num2);
    }
}
