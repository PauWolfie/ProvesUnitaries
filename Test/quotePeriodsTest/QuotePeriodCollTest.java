package quotePeriodsTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import publicadministration.QuotePeriod;
import publicadministration.QuotePeriodsColl;
import publicadministration.exceptions.IncorrectValDaysException;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class QuotePeriodCollTest implements QuotePeriodTestInterface {
    QuotePeriodsColl qpColl;

    @BeforeEach
    void setQpColl(){
        qpColl = new QuotePeriodsColl();
    }
    @Test
    public void correctTest() throws IncorrectValDaysException {
        for (int i = 0; i < MAX; i++) {
            qpColl.addQuotePeriod(new QuotePeriod(new Date(MAX - i, 1, 1), nDays));
        }

        List<QuotePeriod> qpList = qpColl.getQuotePeriods();
        for (int i = 0; i < MAX; i++) {
            qpList.get(i);
            assertEquals(new Date(i + 1, 1, 1), qpList.get(i).getInitDay());
            assertEquals("Quote Period{La cotización empezó='" + qpList.get(i).getInitDay() + " y ha durado='365'}",
                    qpList.get(i).toString());
        }
    }

    @Test
    public void emptyTest() {
        Throwable exception1 = assertThrows(NullPointerException.class,
                () -> {
                    qpColl.addQuotePeriod(null);
                });

        assertEquals("Periode de cotització no vàlid", exception1.getMessage());
    }
}
