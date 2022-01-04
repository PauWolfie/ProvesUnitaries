package servicesTest;

import data.Nif;
import data.PINcode;
import data.Password;
import data.exceptions.EmptyException;
import data.exceptions.WrongFormedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.CertificationAuthoriryDoble;
import services.CertificationAuthority;
import services.exceptions.*;

import java.net.ConnectException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;


public class CertifyAuthorityTest {
    CertificationAuthority ca;
    Nif nif1, nif2, nif3;
    Date date;
    PINcode pin;
    Password pswd;

    @BeforeEach
    void setSs() throws EmptyException, WrongFormedException {
        ca = new CertificationAuthoriryDoble();
        date = new Date(223, 1, 1);
        pswd = new Password("passerronea");
        pin = new PINcode(123);
        nif1 = new Nif("48152635A");
        nif2 = new Nif("48152679B");
        nif3 = new Nif("48152603C");
    }

    @Test
    void ssTest() throws EmptyException, IncorrectValDateException, NifNotRegisteredException, AnyMobileRegisteredException, ConnectException, WrongFormedException, NotValidPINException, NotValidCredException {
        Throwable exception1 = assertThrows(NifNotRegisteredException.class,
                () -> {
                    ca.sendPIN(new Nif("48157869C"), date);
                });

        Throwable exception2 = assertThrows(IncorrectValDateException.class,
                () -> {
                    ca.sendPIN(nif1, new Date(1, 1, 1));
                });

        Throwable exception3 = assertThrows(ConnectException.class,
                () -> {
                    ca.sendPIN(nif3, date);
                });
        Throwable exception4 = assertThrows(AnyMobileRegisteredException.class,
                () -> {
                    ca.sendPIN(nif2, date);
                });

        assertEquals("Aquest NIF no està registrat a la SS", exception1.getMessage());
        assertEquals("El NIF ha caducat", exception2.getMessage());
        assertEquals("No s'ha pogut establir connexió", exception3.getMessage());
        assertEquals("No existeix cap mòbil registrat", exception4.getMessage());

        assertTrue(ca.sendPIN(nif1, date));

        //checkPinTest:
        checkPINTest();

        //checkCredent
        checkCredentTest();
    }

    void checkPINTest() throws EmptyException, WrongFormedException, NotValidPINException, ConnectException {
        Throwable exceptionp1 = assertThrows(NotValidPINException.class,
                () -> {
                    ca.checkPIN(nif1, pin);
                });

        Throwable exceptionp2 = assertThrows(ConnectException.class,
                () -> {
                    ca.checkPIN(nif3, new PINcode(999));
                });
        assertEquals("PIN no vàlid", exceptionp1.getMessage());
        assertEquals("No s'ha pogut establir connexió", exceptionp2.getMessage());
        assertTrue(ca.checkPIN(nif1,new PINcode(999)));
    }

    void checkCredentTest() throws EmptyException, WrongFormedException, NotValidCredException, NifNotRegisteredException, AnyMobileRegisteredException, ConnectException {
        Throwable exception1 = assertThrows(NifNotRegisteredException.class,
                () -> {
                    ca.checkCredent(new Nif("48157869C"), pswd);
                });

        Throwable exception2 = assertThrows(AnyMobileRegisteredException.class,
                () -> {
                    ca.checkCredent(nif2, pswd);
                });

        Throwable exception3 = assertThrows(ConnectException.class,
                () -> {
                    ca.checkCredent(nif3, pswd);
                });

        Throwable exception4 = assertThrows(NotValidCredException.class,
                () -> {
                    ca.checkCredent(nif1, pswd);
                });

        assertEquals("Aquest NIF no està registrat a la SS", exception1.getMessage());
        assertEquals("No existeix cap mòbil registrat", exception2.getMessage());
        assertEquals("No s'ha pogut establir connexió", exception3.getMessage());
        assertEquals("Les credenciasls no son vàlides", exception4.getMessage());

        assertEquals(0,ca.checkCredent(nif1,new Password("anypasswd")));
    }
}
