package dataTest;

import data.AccredNumb;
import data.exceptions.EmptyException;
import data.exceptions.WrongFormedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class AccredNumbTest implements DataTestInterface {

    AccredNumb num;
    AccredNumb num2;

    @BeforeEach
    void setNum() throws EmptyException, WrongFormedException {
        num = new AccredNumb("LLRO0123456789");
        num2 = new AccredNumb("PAQU0123456789");
    }
    @Test
    public void correctTest() throws WrongFormedException, EmptyException {
        assertEquals("LLRO0123456789",num.getAccNum());
    }

    @Override
    public void emptyTest() {
        Throwable exception1 = assertThrows(EmptyException.class,
                () -> {
                    num = new AccredNumb("");
                });
        Throwable exception2 = assertThrows(NullPointerException.class,
                () -> {
                    num = new AccredNumb(null);
                });

        assertEquals("El nombre d'acreditació no pot estar buit", exception1.getMessage());
        assertEquals("Null reference", exception2.getMessage());
    }

    @Test
    public void wrongFormatTest() {
        Throwable exception1 = assertThrows(WrongFormedException.class,
                () -> {
                    num = new AccredNumb("LLRO01234567894123254");
                });
        Throwable exception2 = assertThrows(WrongFormedException.class,
                () -> {
                    num = new AccredNumb("00000123456789");
                });
        Throwable exception3 = assertThrows(WrongFormedException.class,
                () -> {
                    num = new AccredNumb("LLROXXXXXXXXXX");
                });

        assertEquals("El nombre d'acreditació ha de tenir 14 caràcters", exception1.getMessage());
        assertEquals("Els 4 primers caràcters han de ser lletres", exception2.getMessage());
        assertEquals("Els 10 últims caràcters han de ser nombres", exception3.getMessage());
    }

    @Test
    public void compareTest() throws WrongFormedException, EmptyException {
        assertEquals(num, num);
        assertNotEquals("LLRO0123456789", num);
        assertNotEquals(num, num2);
    }
}
