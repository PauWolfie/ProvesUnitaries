package unifiedPlatformTest;

import data.Nif;
import data.exceptions.EmptyException;
import org.junit.jupiter.api.Test;
import unifiedPlatform.UnifiedPlatform;
import unifiedPlatform.exceptions.AnyKeyWordProcedureException;

import static org.junit.jupiter.api.Assertions.*;

public class UnifiedPlatformTest {
    @Test
    void enterKeywordTest () throws AnyKeyWordProcedureException {
        UnifiedPlatform exec = new UnifiedPlatform();
        assertEquals("Seguretat Social",exec.searchKeyWords("vida laboral"));
        assertEquals("Agencia Estatal de l'Administració Tributaria",exec.searchKeyWords("dades fiscals"));
        assertEquals("Ministeri de Justícia",exec.searchKeyWords("certificat naixement"));
        assertEquals("Direcció General de tràfic",exec.searchKeyWords("punts carnet"));

        Throwable exception = assertThrows(AnyKeyWordProcedureException.class,
                () -> {
                    exec.enterKeyWords("hola");
                });

        assertEquals("No s'ha trobat cap coincidencia", exception.getMessage());
    }
}
