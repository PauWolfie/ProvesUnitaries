package dataTest;

import exceptions.EmptyException;
import exceptions.WrongFormedException;
import jdk.jshell.spi.ExecutionControl;

public class PasswordTest implements dataTestInterface {
    @Override
    public void correctTest() throws WrongFormedException, EmptyException {
    }

    @Override
    public void emptyTest() {
    }

    @Override
    public void wrongFormatTest() {
    }

    @Override
    public void compareTest() throws WrongFormedException, EmptyException {
    }
}
