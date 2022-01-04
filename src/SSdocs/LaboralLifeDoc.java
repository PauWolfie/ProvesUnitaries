package SSdocs;

import data.DocPath;
import data.Nif;
import data.exceptions.EmptyException;
import data.exceptions.WrongFormedException;
import publicadministration.QuotePeriodsColl;

import java.io.File;
import java.util.Date;

public class LaboralLifeDoc extends PDFDocument {
    private Nif nif;
    private QuotePeriodsColl quotePds;

    public LaboralLifeDoc (Nif nif, QuotePeriodsColl qtP, Date givenDate, DocPath givenPath, File givenFile) throws EmptyException, WrongFormedException {
        super(givenDate, givenPath, givenFile );
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

