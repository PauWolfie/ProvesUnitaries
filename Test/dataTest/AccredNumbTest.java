package dataTest;

import data.AccredNumb;
import data.exceptions.EmptyException;
import data.exceptions.WrongFormedException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class AccredNumbTest implements DataTestInterface {

    @Test
    public void correctTest() throws WrongFormedException, EmptyException {
        AccredNumb num = new AccredNumb("LLRO0123456789");
        assertEquals("LLRO0123456789",num.getAccNum());
    }

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

    @Test
    public void compareTest() throws WrongFormedException, EmptyException {
        AccredNumb num1 = new AccredNumb("LLRO0123456789");
        AccredNumb num2 = new AccredNumb("FEPE9876543210");

        assertEquals(num1, num1);
        assertNotEquals("LLRO0123456789", num1);
        assertNotEquals(num1, num2);
    }
}
