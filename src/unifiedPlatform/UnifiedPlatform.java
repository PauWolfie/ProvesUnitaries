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
    public String searchKeyWords(String keyWord) throws AnyKeyWordProcedureException {
        String[][] tramits = {
                {"Solicitar informe vida laboral", "Obtener acreditación número afiliación Seguridad Social"},
                {"Obtener datos fiscales", "Tramitar borrador declaración renta"},
                {"Solicitar certificado nacimiento"},
                {"Consultar puntos asociados carnet conducir"}
        };
        double[] similarity_idx = new double[4];

        for (int i = 0; i < aapps.length; i++) {
            for (int j = 0; j < tramits[i].length; j++) {
                double foo = similarity(tramits[i][j], keyWord);
                if (similarity_idx[i] < foo) {
                    similarity_idx[i] = foo;
                }
            }
        }

        int aappIDx = 0;
        double similarityIdx = 0;
        for (int i = 0; i < similarity_idx.length; i++) {
            if (similarity_idx[i] > similarityIdx) {
                similarityIdx = similarity_idx[i];
                aappIDx = i;
            }
        }

        if (similarityIdx < 0.24) {
            return null;
        }

        return aapps[aappIDx];
    }

    public static double similarity(String s1, String s2) {
        if (s1.length() < s2.length()) { // s1 should always be bigger
            String swap = s1; s1 = s2; s2 = swap; }
        int bigLen = s1.length(); if (bigLen == 0) {
            return 1.0; /* both strings are zero length */ }
        return (bigLen - computeEditDistance(s1, s2)) / (double) bigLen;
    }

    public static int computeEditDistance(String s1, String s2) {
        s1 = s1.toLowerCase();
        s2 = s2.toLowerCase();
        int[] costs = new int[s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            int lastValue = i;
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0) costs[j] = j;
                else {
                    if (j > 0) {
                        int newValue = costs[j - 1];
                        if (s1.charAt(i - 1) != s2.charAt(j - 1)) newValue = Math.min(Math.min(newValue, lastValue), costs[j]) + 1;
                        costs[j - 1] = lastValue; lastValue = newValue;
                    }
                }
            }
            if (i > 0) costs[s2.length()] = lastValue;
        } return costs[s2.length()];
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
