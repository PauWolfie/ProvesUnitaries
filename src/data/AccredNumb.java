package data;

import exceptions.dataExceptions.EmptyException;
import exceptions.dataExceptions.WrongFormedException;

/**
 * Essential data classes
 */
public class AccredNumb {
    // The accreditation number of the SS
    private final String accNum;

    public AccredNumb(String accNum) throws EmptyException, WrongFormedException {
        if (accNum == null){
            throw new NullPointerException("Null reference");
        }
        if (accNum.isEmpty()) {
            throw new EmptyException("El nombre d'acreditació no pot estar buit");
        }
        checkValid(accNum);
        this.accNum = accNum;
    }

    private void checkValid(String num) throws WrongFormedException {
        if (num.length() != 14) {
            throw new WrongFormedException("El nombre d'acreditació ha de tenir 14 caràcters");
        }
        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(num.charAt(i))) {
                throw new WrongFormedException("Els 4 primers caràcters han de ser lletres");
            }
        }
        for (int i = 4; i < 14; i++) {
            if (!Character.isDigit(num.charAt(i))) {
                throw new WrongFormedException("Els 10 últims caràcters han de ser nombres");
            }
        }
    }

    public String getAccNum() {
        return accNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccredNumb acNumToComp = (AccredNumb) o;
        return accNum.equals(acNumToComp.accNum);
    }

    @Override
    public int hashCode() {
        return accNum.hashCode();
    }

    @Override
    public String toString() {
        return "Accreditation Number{" + "Número de acreditación='" + accNum + '\'' + '}';
    }
}
