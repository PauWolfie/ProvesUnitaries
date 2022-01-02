package quotePeriodsTest;

import org.junit.jupiter.api.Test;
import publicadministration.QuotePeriod;
import publicadministration.exceptions.IncorrectValDaysException;

import static org.junit.jupiter.api.Assertions.*;

public class QuotePeriodTest implements QuotePeriodTestInterface{
    @Test
    public void correctTest() throws IncorrectValDaysException {
        QuotePeriod qp = new QuotePeriod(initDate,nDays);
        assertEquals(initDate,qp.getInitDay());
        assertEquals(nDays,qp.getNumDays());
        assertNotEquals("Quote Period{La cotización empezó=" + initDate +"y ha durado="+ nDays +"}",qp.toString());
    }

    @Test
    public void emptyTest() {
        Throwable exception1 = assertThrows(NullPointerException.class,
                () -> {
                    QuotePeriod qp = new QuotePeriod(null,nDays);
                });
        Throwable exception2 = assertThrows(IncorrectValDaysException.class,
                () -> {
                    QuotePeriod qp = new QuotePeriod(initDate,0);
                });

        assertEquals("La data està buida", exception1.getMessage());
        assertEquals("El nombre de dies cotitzats no és vàlid", exception2.getMessage());
    }
}
