package dataTest;

import exceptions.EmptyException;
import exceptions.WrongFormedException;
import org.junit.jupiter.api.Test;


public interface dataTestInterface {
    @Test
    void correctTest() throws WrongFormedException, EmptyException;

    @Test
    void emptyTest();

    @Test
    void wrongFormatTest();

    @Test
    void compareTest() throws WrongFormedException, EmptyException;
}
