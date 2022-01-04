package unifiedPlatformTest;


import data.Nif;
import data.exceptions.EmptyException;
import data.exceptions.WrongFormedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.CertificationAuthoriryDoble;
import services.SSDoble;
import services.exceptions.AnyMobileRegisteredException;
import services.exceptions.IncorrectValDateException;
import services.exceptions.NifNotRegisteredException;
import unifiedPlatform.UnifiedPlatform;
import unifiedPlatform.exceptions.AnyKeyWordProcedureException;

import java.net.ConnectException;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class UnifiedPlatformTest {
    UnifiedPlatform exec;
    @BeforeEach
    void setExec() throws EmptyException, WrongFormedException {
        exec = new UnifiedPlatform();
        exec.setSS(new SSDoble());
        exec.setCertificationAuthority(new CertificationAuthoriryDoble());
    }

    @Test
    void enterKeywordTest () throws AnyKeyWordProcedureException {
        assertEquals("Seguretat Social",exec.searchKeyWords("vida laboral"));
        assertEquals("Agencia Estatal de l'Administració Tributaria",exec.searchKeyWords("dades fiscals"));
        assertEquals("Ministeri de Justícia",exec.searchKeyWords("certificat naixement"));
        assertEquals("Direcció General de tràfic",exec.searchKeyWords("punts carnet"));

        Throwable exception = assertThrows(AnyKeyWordProcedureException.class,
                () -> {
                    exec.enterKeyWords("skflj");
                });

        assertEquals("No s'ha trobat cap coincidencia", exception.getMessage());

    }

    @Test
    void enterNIF_PINObtTest() throws EmptyException, WrongFormedException, IncorrectValDateException, NifNotRegisteredException, AnyMobileRegisteredException, ConnectException {
        Nif nif = new Nif("12345678Z");
        Date date = new Date(223, Calendar.JANUARY,1);

        Throwable exception = assertThrows(ConnectException.class,
                () -> {
                    exec.enterNIF_PINobt(nif,date);
                });

        assertEquals("Error al enviar pin", exception.getMessage());
    }

}
