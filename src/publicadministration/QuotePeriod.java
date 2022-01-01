package publicadministration;

import publicadministration.exceptions.IncorrectValDaysException;

import java.util.Date;

public class QuotePeriod {
    private Date initDay;
    private int numDays;

    public QuotePeriod(Date date, int ndays) throws IncorrectValDaysException {
        if (date == null){
            throw new NullPointerException("La data està buida");
        }
        if(ndays <= 0){
            throw new IncorrectValDaysException("El nombre de dies cotitzats no és vàlid");
        }

        initDay =  date;
        numDays = ndays;
    }

    public Date getInitDay() {
        return initDay;
    }

    public int getNumDays() {
        return numDays;
    }

    public String toString() {
        // converts to String
        return "Quote Period{" + "La cotización empezó='" + initDay +" y ha durado='" + numDays + '\'' + '}';
    }
}
