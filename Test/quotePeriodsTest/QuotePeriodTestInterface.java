package quotePeriodsTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import publicadministration.exceptions.IncorrectValDaysException;

import java.util.Calendar;
import java.util.Date;

public interface QuotePeriodTestInterface {
    Date initDate = new Date(2022, Calendar.JANUARY,1);
    int nDays = 365;
    int MAX = 100;
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
