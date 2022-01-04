package services;

import SSdocs.LaboralLifeDoc;
import SSdocs.MemberAccreditationDoc;
import data.Nif;
import data.exceptions.EmptyException;
import data.exceptions.WrongFormedException;
import services.exceptions.NotAffiliatedException;

import java.net.ConnectException;
import java.util.LinkedList;
import java.util.List;

public class SSDoble implements SS {
    List<Nif> nifDB = new LinkedList();


    public SSDoble() throws EmptyException, WrongFormedException {
        nifDB.add(new Nif("48152635A"));
        nifDB.add(new Nif("87654321A"));
    }

    @Override
    public LaboralLifeDoc getLaboralLife(Nif nif) throws NotAffiliatedException, ConnectException {
        if (!nifDB.contains(nif)) {
            throw new NotAffiliatedException("El NIF no està registrat a la seguretat social");
        }
        return null;
    }

    @Override
    public MemberAccreditationDoc getMembAccred(Nif nif) throws NotAffiliatedException, ConnectException {
        if (!nifDB.contains(nif)) {
            throw new NotAffiliatedException("El NIF no està registrat a la seguretat social");
        }
        return null;
    }
}