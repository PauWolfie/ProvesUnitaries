package unifiedPlatform;

import SSdocs.PDFDocument;
import data.DocPath;
import data.Nif;
import data.PINcode;
import data.Password;

import services.CertificationAuthority;
import services.exceptions.*;
import unifiedPlatform.exceptions.AnyKeyWordProcedureException;
import unifiedPlatform.exceptions.BadPathException;
import unifiedPlatform.exceptions.PrintingException;

import java.net.ConnectException;
import java.util.Date;
import java.util.Scanner;

public class UnifiedPlatform {

    private final String[] aapps = {"Seguretat Social", "Agencia Estatal de l'Administració Tributaria",
            "Ministeri de Justícia", "Direcció General de tràfic"};
    private String currentAAPP = null;
    private PDFDocument doc;


    //Input events:
    public void processSearcher() {
        System.out.println("Introdueix l'AAPP a cercar en el buscador de tràmits: ");
    }

    public void enterKeyWords(String keyWord) throws AnyKeyWordProcedureException {
        Scanner sc = new Scanner(System.in);
        String keyword = sc.nextLine();
        String aapp = searchKeyWords(keyword);
        if (aapp == null) {
            throw new AnyKeyWordProcedureException("No s'ha trobat cap coincidencia");
        } else {
            System.out.print("Heu escollit: " + aapp);
        }
        currentAAPP = aapp;
    }

    public void selectSS() {
        System.out.println("Selecciona un dels apartats de" + currentAAPP + ": ");
        System.out.println("***** apartats a mostrar *****");
    }

    public void selectCitizens() {
        System.out.println("Selecciona un dels tràmits de Ciutadans: ");
        System.out.println("***** tràmits a mostrar *****");
    }

    public void selectReports() {
        System.out.println("Selecciona un dels informes disponibles: ");
        System.out.println("***** informes a mostrar *****");
    }

    public void selectCertificationReport(byte opc) {
        if (opc == 0) {
            System.out.println("Heu seleccionat solicitar l'informe de vida laboral");
        } else {
            System.out.println("Heu seleccionat obtenir acreditació del nombre d'afiliació a la Seguretat Social");
        }
    }

    public void selectAuthMethod(byte opc) {
        if (opc == 0) {
            System.out.println("Heu seleccionat accés mitjançant Certificat digital y DNIe");
        } else {
            System.out.println("Heu seleccionat accés mitjançant Sistema Cl@ve");
        }
    }

    public void enterNIF_PINobt(Nif nif, Date valDate) throws NifNotRegisteredException, IncorrectValDateException,
            AnyMobileRegisteredException, ConnectException {

    }

    public void enterPIN(PINcode pin) throws NotValidPINException,
            NotAffiliatedException, ConnectException {

    }

    public void enterCred(Nif nif, Password passw) throws
            NifNotRegisteredException, NotValidCredException,
            AnyMobileRegisteredException, ConnectException {

    }

    private void printDocument() throws BadPathException,
            PrintingException {

    }

    private void downloadDocument() {
    }

    private void selectPath(DocPath path) throws BadPathException {

    }


    // Other operations
    private String searchKeyWords(String keyWord) throws
            AnyKeyWordProcedureException {
        return null;
    }

    private void OpenDocument(DocPath path) throws BadPathException {

    }

    private void printDocument(DocPath path) throws BadPathException,
            PrintingException {

    }

    private void downloadDocument(DocPath path) throws BadPathException {

    }


    //Doble. Revisar si cal moure de lloc al enunciat (pg14)
    private static class CertificationAuthorityDoble implements CertificationAuthority {
        Nif nif;
        Date date;

        private CertificationAuthorityDoble(Nif nif, Date date) {
            this.nif = nif;
            this.date = date;
        }

        @Override
        public boolean sendPIN(Nif nif, Date date) throws NifNotRegisteredException, IncorrectValDateException,
                AnyMobileRegisteredException, ConnectException {
            if (!this.nif.equals(nif) || !this.date.equals(date)) {
                throw new IncorrectValDateException("El nif i la data de validesa no corresponen");
            }
            //proporciona las credenciales al sistema Cl@ve PIN
            //solicita la emisión del PIN para completar su identificación
            return true;
        }

        @Override
        public boolean checkPIN(Nif nif, PINcode pin) throws NotValidPINException, ConnectException {
            return false;
        }

        @Override
        public byte ckeckCredent(Nif nif, Password passw) throws NifNotRegisteredException, NotValidCredException, AnyMobileRegisteredException, ConnectException {
            return 0;
        }
    }
}
