package data;

import data.exceptions.EmptyException;
import data.exceptions.WrongFormedException;

public class Password {
    // Password to autenticate
    private final String pswd;

    public Password(String pswd) throws EmptyException, WrongFormedException {
        if (pswd == null) {
            throw new NullPointerException("Null reference");
        }
        if (pswd.isEmpty()) {
            throw new EmptyException("La contrassenya no pot estar buida");
        }
        if(pswd.length() < 8){
            throw new WrongFormedException("La contrassenya ha de tenir al menys 8 caràcters");
        }
        this.pswd = pswd;
    }

    public String getPswd() {
        return pswd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Password pswdToComp = (Password) o;
        return pswd.equals(pswdToComp.pswd);
    }

    @Override
    public int hashCode() {
        return pswd.hashCode();
    }

    @Override
    public String toString() {
        return "Permanent Key{" + "Cl@ve permanente='" + pswd + '\'' + '}';
    }
}
