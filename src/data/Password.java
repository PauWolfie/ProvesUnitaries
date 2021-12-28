package data;

public class Password {
    // The accreditation number of the SS
    private final String pswd;

    public Password(String pswd) {
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
