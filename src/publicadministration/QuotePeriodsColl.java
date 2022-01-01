package publicadministration;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class QuotePeriodsColl {
    List<QuotePeriod> quotePeriods;

    public QuotePeriodsColl() {
        quotePeriods = new LinkedList<>();
    }

    public List<QuotePeriod> getQuotePeriods() {
        return quotePeriods;
    }

    public void addQuotePeriod(QuotePeriod qPd) {
        quotePeriods.set(getQuoteIndex(qPd), qPd);
    }

    private int getQuoteIndex(QuotePeriod qPd) {
        if (quotePeriods.isEmpty()) {
            return 0;
        }
        int idx = quotePeriods.size();
        Date initDate = qPd.getInitDay();
        Date dateToCheck = quotePeriods.get(idx - 1).getInitDay();
        while (initDate.compareTo(dateToCheck) < 0) {
            idx--;
            dateToCheck = quotePeriods.get(idx).getInitDay();
        }
        return idx;
    }

    public String toString() {
        // Converts to String
        return "Total Quote Periods{" + "El número de cotizaciones es de='" + quotePeriods.size() + '\'' + '}';
    }
}


