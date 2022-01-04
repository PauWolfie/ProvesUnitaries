package servicesTest;

import data.Nif;
import data.exceptions.EmptyException;
import data.exceptions.WrongFormedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.SS;
import services.SSDoble;
import services.exceptions.NotAffiliatedException;

import java.net.ConnectException;

import static org.junit.jupiter.api.Assertions.*;

public class SSTest {
    SS ss;
    Nif nif;
    @BeforeEach
    void setSs() throws EmptyException, WrongFormedException {
        ss = new SSDoble();
        nif = new Nif("48152635A");
    }

    @Test
    void getLaboralLifeTest() throws NotAffiliatedException, ConnectException {
        Throwable exception = assertThrows(NotAffiliatedException.class,
                () -> {
                    ss.getLaboralLife(new Nif("48484848A"));
                });

        assertEquals("El NIF no està registrat a la seguretat social", exception.getMessage());
        assertNull(ss.getLaboralLife(nif));
    }
    @Test
    void getMembAccredTest() throws NotAffiliatedException, ConnectException {
        Throwable exception = assertThrows(NotAffiliatedException.class,
                () -> {
                    ss.getLaboralLife(new Nif("48484848A"));
                });

        assertEquals("El NIF no està registrat a la seguretat social", exception.getMessage());
        assertNull(ss.getMembAccred(nif));
    }
}
