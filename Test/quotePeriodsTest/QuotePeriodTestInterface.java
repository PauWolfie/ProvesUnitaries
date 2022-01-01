package quotePeriodsTest;

import org.junit.jupiter.api.Test;
import publicadministration.exceptions.IncorrectValDaysException;

public interface QuotePeriodTestInterface {
    /**
     * Comprova la correcta implementació per al cas on les dades introduides són correctes
     */
    @Test
    void correctTest() throws IncorrectValDaysException;

    /**
     * Comprova la correcta execució de les excepcions en el cas que la referència passada sigui nul·la o no contingui res.
     **/
    @Test
    void emptyTest();
}
