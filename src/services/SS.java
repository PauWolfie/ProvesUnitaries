package services;

import SSdocs.LaboralLifeDoc;
import SSdocs.MemberAccreditationDoc;
import data.Nif;
import services.exceptions.NotAffiliatedException;

import java.net.ConnectException;

/**
 * External services involved in procedures from population
 */
public interface SS { // External service for Social Security Govern administration
    LaboralLifeDoc getLaboralLife(Nif nif) throws NotAffiliatedException,
            ConnectException;

    MemberAccreditationDoc getMembAccred(Nif nif) throws NotAffiliatedException,
            ConnectException;
}
