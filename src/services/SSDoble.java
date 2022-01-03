package services;

import SSdocs.LaboralLifeDoc;
import SSdocs.MemberAccreditationDoc;
import data.Nif;
import data.exceptions.EmptyException;
import data.exceptions.WrongFormedException;
import services.exceptions.NotAffiliatedException;

import java.net.ConnectException;

public class SSDoble implements SS {
    Nif nif = new Nif("48052867W");

    public SSDoble() throws EmptyException, WrongFormedException {
    }

    @Override
    public LaboralLifeDoc getLaboralLife(Nif nif) throws NotAffiliatedException, ConnectException {
        if (!this.nif.getNif().equals(nif.getNif())) {
            throw new NotAffiliatedException("El NIF no està registrat a la seguretat social");
        }
        return null;
    }

    @Override
    public MemberAccreditationDoc getMembAccred(Nif nif) throws NotAffiliatedException, ConnectException {
        if (!this.nif.getNif().equals(nif.getNif())) {
            throw new NotAffiliatedException("El NIF no està registrat a la seguretat social");
        }
        return null;
    }
}