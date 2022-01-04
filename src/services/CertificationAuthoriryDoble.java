package services;

import data.Nif;
import data.PINcode;
import data.Password;
import data.exceptions.EmptyException;
import data.exceptions.WrongFormedException;
import services.exceptions.*;

import java.net.ConnectException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class CertificationAuthoriryDoble implements CertificationAuthority {
    List<Nif> nifDB = new LinkedList();
    List<String> mobilePhoneDB = new LinkedList();

    Password pass;
    PINcode pin;
    Date date;

    public CertificationAuthoriryDoble() throws EmptyException, WrongFormedException {
        date = new Date();
        pass = new Password("anypasswd");

        nifDB.add(new Nif("48152635A"));
        mobilePhoneDB.add("666666660");

        nifDB.add(new Nif("48152679B"));
        mobilePhoneDB.add(null);

        nifDB.add(new Nif("48152603C"));
        mobilePhoneDB.add("666984722");

        nifDB.add(new Nif("12345678Z"));
        mobilePhoneDB.add("66666666");

        nifDB.add(new Nif("87654321A"));
        mobilePhoneDB.add("666666660");
    }

    @Override
    public boolean sendPIN(Nif nif, Date date) throws NifNotRegisteredException, IncorrectValDateException, AnyMobileRegisteredException,
            ConnectException, EmptyException, WrongFormedException {
        if (!nifDB.contains(nif)) {
            throw new NifNotRegisteredException("Aquest NIF no està registrat a la SS");
        }

        if (this.date.after(date)) {
            throw new IncorrectValDateException("El NIF ha caducat");
        }

        if (nif.getNif().equals(nifDB.get(2).getNif())) {
            throw new ConnectException("No s'ha pogut establir connexió");
        }

        if (mobilePhoneDB.get(nifDB.indexOf(nif)) == null) {
            throw new AnyMobileRegisteredException("No existeix cap mòbil registrat");
        }

        if(nif.equals(new Nif("12345678Z"))){
            return false;
        }

        this.pin = new PINcode(999);
        return true;
    }

    @Override
    public boolean checkPIN(Nif nif, PINcode pin) throws NotValidPINException, ConnectException {
        if (!pin.getPINcode().equals(this.pin.getPINcode())){
            throw new NotValidPINException("PIN no vàlid");
        }

        if (nif.getNif().equals(nifDB.get(2).getNif())) {
            throw new ConnectException("No s'ha pogut establir connexió");
        }
        if (nif.equals(nifDB.get(nifDB.size() - 1))){
            return false;
        }
        return true;
    }

    @Override
    public byte checkCredent(Nif nif, Password passw) throws NifNotRegisteredException, NotValidCredException, AnyMobileRegisteredException, ConnectException {
        if (!nifDB.contains(nif)) {
            throw new NifNotRegisteredException("Aquest NIF no està registrat a la SS");
        }

        if (mobilePhoneDB.get(nifDB.indexOf(nif)) == null) {
            throw new AnyMobileRegisteredException("No existeix cap mòbil registrat");
        }

        if (nif.getNif().equals(nifDB.get(2).getNif())) {
            throw new ConnectException("No s'ha pogut establir connexió");
        }

        if (!passw.getPswd().equals(this.pass.getPswd())) {
            throw new NotValidCredException("Les credenciasls no son vàlides");
        }

        if (nif.equals(nifDB.get(nifDB.size() - 1))){
            return -1;
        }

        return 0;
    }
}
