package SSdocs;

import data.Nif;
import data.exceptions.EmptyException;
import data.exceptions.WrongFormedException;
import publicadministration.QuotePeriodsColl;

public class LaboralLifeDoc extends PDFDocument {
    private Nif nif;
    private QuotePeriodsColl quotePds;

    public LaboralLifeDoc (Nif nif, QuotePeriodsColl qtP) throws EmptyException, WrongFormedException {
        this.nif = nif;
        this.quotePds = qtP;
    }

    public Nif getNif() {
        return nif;
    }

    public QuotePeriodsColl getQuotePds() {
        return quotePds;
    }
}

