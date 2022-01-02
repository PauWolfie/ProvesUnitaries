package SSdocs;
import data.AccredNumb;
import data.Nif;
import data.exceptions.EmptyException;
import data.exceptions.WrongFormedException;

public class MemberAccreditationDoc extends PDFDocument {
    private Nif nif;
    private AccredNumb numAffil;

    public MemberAccreditationDoc(Nif nif, AccredNumb nAff) throws EmptyException, WrongFormedException {
        this.nif = nif;
        this.numAffil = nAff;
    }

    public Nif getNif() {
        return nif;
    }

    public AccredNumb getNumAffil() {
        return numAffil;
    }

    //Això és una prova
}
