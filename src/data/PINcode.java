package data;

import data.exceptions.EmptyException;
import data.exceptions.WrongFormedException;

/**
 * Essential data classes
 */
final public class PINcode {
    // The PIN sent to autenticate
    private final String PINcode;

    public PINcode(int PIN) throws EmptyException, WrongFormedException {
        if (PIN == 0) {
            throw new EmptyException("El PIN no pot estar buit");
        }
        if (Integer.toString(PIN).length() != 3) {
            throw new WrongFormedException("El PIN ha de tenir 3 nombres, ara en té: " + Integer.toString(PIN).length());
        }
        this.PINcode = String.valueOf(PIN);
    }

    public String getPINcode() {
        return PINcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PINcode PINcodeToComp = (PINcode) o;
        return PINcode.equals(PINcodeToComp.PINcode);
    }

    @Override
    public int hashCode() {
        return PINcode.hashCode();
    }

    @Override
    public String toString() {
        return "PIN code{" + "Código PIN='" + PINcode + '\'' + '}';
    }
}
