package SSdocs;
import data.AccredNumb;
import data.DocPath;
import data.Nif;
import data.exceptions.EmptyException;
import data.exceptions.WrongFormedException;

import java.io.File;
import java.util.Date;

public class MemberAccreditationDoc extends PDFDocument {
    private Nif nif;
    private AccredNumb numAffil;

    public MemberAccreditationDoc(Nif nif, AccredNumb nAff, Date givenDate, DocPath givenPath, File givenFile) throws EmptyException, WrongFormedException {
        super(givenDate, givenPath, givenFile);
        this.nif = nif;
        this.numAffil = nAff;
    }

    public Nif getNif() {
        return nif;
    }

    public AccredNumb getNumAffil() {
        return numAffil;
    }
}
