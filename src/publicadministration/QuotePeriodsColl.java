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
        if(qPd == null){
            throw new NullPointerException("Periode de cotització no vàlid");
        }
        quotePeriods.add(getQuoteIndex(qPd), qPd);
    }

    private int getQuoteIndex(QuotePeriod qPd) {
        if (quotePeriods.isEmpty()) {
            return 0;
        }
        int idx = quotePeriods.size();
        Date dateToAdd = qPd.getInitDay();
        Date dateToCheck = quotePeriods.get(idx - 1).getInitDay();
        while (idx != 0 && dateToAdd.compareTo(dateToCheck) < 0) {
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


