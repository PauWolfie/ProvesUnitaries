package data;

import data.exceptions.EmptyException;
import data.exceptions.WrongFormedException;

/**
 * Essential data classes
 */
final public class Nif {
    // The tax identification number in the Spanish state.
    private final String nif;

    public Nif(String code) throws WrongFormedException, EmptyException {
        if (code == null) {
            throw new NullPointerException("Null reference");
        }
        if (code.isEmpty()) {
            throw new EmptyException("El NIF no pot estar buit");
        }
        checkValid(code);
        this.nif = code;
    }

    private void checkValid(String code) throws WrongFormedException {
        if (code.length() != 9) {
            throw new WrongFormedException("El NIF ha de tenir 8 caràcters");
        }
        for (int i = 0; i < 8; i++) {
            if (!Character.isDigit(code.charAt(i))) {
                throw new WrongFormedException("Els 7 primers caràcters han de ser nombres");
            }
        }
        if (!Character.isLetter(code.charAt(8))) {
            throw new WrongFormedException("L'últim caràcter ha de ser una lletra");
        }
    }

    public String getNif() {
        return nif;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nif niff = (Nif) o;
        return nif.equals(niff.nif);
    }

    @Override
    public int hashCode() {
        return nif.hashCode();
    }

    @Override
    public String toString() {
        return "Nif{" + "nif ciudadano='" + nif + '\'' + '}';
    }
}