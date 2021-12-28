package data;

/**
 * Essential data classes
 */
final public class PINcode {
    // The PIN sent to autenticate
    private final String PINcode;

    public PINcode(int PIN) {
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
