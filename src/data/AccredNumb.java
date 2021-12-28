package data;

/**
 * Essential data classes
 */
public class AccredNumb {
    // The accreditation number of the SS
    private final String accNum;

    public AccredNumb(String accNum) {
        this.accNum = accNum;
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
