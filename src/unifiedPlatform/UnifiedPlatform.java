package unifiedPlatform;

import SSdocs.PDFDocument;
import data.DocPath;
import data.Nif;
import data.PINcode;
import data.Password;

import data.exceptions.EmptyException;
import data.exceptions.WrongFormedException;
import services.CertificationAuthority;
import services.SS;
import services.exceptions.*;
import unifiedPlatform.exceptions.AnyKeyWordProcedureException;
import unifiedPlatform.exceptions.BadPathException;
import unifiedPlatform.exceptions.PrintingException;

import java.io.File;
import java.io.IOException;
import java.net.ConnectException;
import java.util.Date;
import java.util.Scanner;
import java.util.StringTokenizer;

public class UnifiedPlatform {

    private final String[] aapps = {"Seguretat Social", "Agencia Estatal de l'Administració Tributaria",
            "Ministeri de Justícia", "Direcció General de tràfic"};

    private final String[][] tramits = {
            {"Sol·licitar informe vida laboral", "Obtenir acreditació número afiliació Seguretat Social"},
            {"Obtenir dades fiscals", "Tramitar borrador declaració renda"},
            {"Sol·licitar certificat naixement"},
            {"Consultar punts asociats carnet conduir"}
    };

    private CertificationAuthority certAut;
    private SS ss;
    private Nif nifAct;
    private Date valDateAct;
    private Password pswd;
    private String currentAAPP = null;
    private PDFDocument doc;


    //Setters for dobles:
    public void setCertificationAuthority(CertificationAuthority authority) {
        this.certAut = authority;
    }

    public void setSS(SS ss) {
        this.ss = ss;
    }


    //Input events:
    public void processSearcher() {
        System.out.println("Introdueix l'AAPP a cercar en el buscador de tràmits: ");
    }

    public void enterKeyWords(String keyWord) throws AnyKeyWordProcedureException {
        String aapp = searchKeyWords(keyWord);
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
            AnyMobileRegisteredException, ConnectException, EmptyException, WrongFormedException {
        if (!certAut.sendPIN(nif, valDate)) {
            throw new ConnectException("Error al enviar pin");
        }
        nifAct = nif;
        valDateAct = valDate;
    }

    public void enterPIN(PINcode pin) throws NotValidPINException,
            NotAffiliatedException, ConnectException {

        if (!certAut.checkPIN(nifAct, pin)) {
            throw new ConnectException("Error de conexion");
        }
        ss.getLaboralLife(nifAct);
    }

    public void enterCred(Nif nif, Password passw) throws
            NifNotRegisteredException, NotValidCredException,
            AnyMobileRegisteredException, ConnectException {
        if (certAut.checkCredent(nif, passw) != 0) {
            throw new ConnectException("Error al enviar credencials");
        }
        nifAct = nif;
        pswd = passw;
    }

    private void printDocument() throws BadPathException,
            PrintingException {
        System.out.println("Printant el document...");
    }

    private void downloadDocument() {
        System.out.println("Descarregant el document...");
    }

    // Other operations
    public String searchKeyWords(String keyWord) throws AnyKeyWordProcedureException {
        double[] max_similarity = new double[4];
        for (int i = 0; i < aapps.length; i++) {
            for (int j = 0; j < tramits[i].length; j++) {
                StringTokenizer keyWord_tk = new StringTokenizer(keyWord);
                while (keyWord_tk.hasMoreTokens()) {
                    String keyword = keyWord_tk.nextToken();
                    StringTokenizer tramits_tk = new StringTokenizer(tramits[i][j]);
                    while (tramits_tk.hasMoreTokens()) {
                        double percentage = similarity(keyword, tramits_tk.nextToken());
                        if (percentage > maximum) {
                            maximum = percentage;
                            indexOfAAPP = i;
                        }
                    }
                }
            }
        }

        int indexOfAAPP = 0;
        double maximum = 0;
        for (int i = 0; i < max_similarity.length; i++) {
            if (max_similarity[i] > maximum) {
                maximum = max_similarity[i];
                indexOfAAPP = i;
            }
        }

        if (maximum < 0.85) {
            return null;
        }

        return aapps[indexOfAAPP];
    }

    public static double similarity(String s1, String s2) {
        if (s1.length() < s2.length()) { // s1 should always be bigger
            String swap = s1;
            s1 = s2;
            s2 = swap;
        }
        int bigLen = s1.length();
        if (bigLen == 0) {
            return 1.0; /* both strings are zero length */
        }
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
                        if (s1.charAt(i - 1) != s2.charAt(j - 1))
                            newValue = Math.min(Math.min(newValue, lastValue), costs[j]) + 1;
                        costs[j - 1] = lastValue;
                        lastValue = newValue;
                    }
                }
            }
            if (i > 0) costs[s2.length()] = lastValue;
        }
        return costs[s2.length()];
    }

    private void OpenDocument(DocPath path) throws BadPathException, EmptyException, WrongFormedException, IOException {
        PDFDocument document = new PDFDocument(new Date(), path, new File(path.getPath()));
        document.openDoc(document.getPath());
    }

    //NO CAL IMPLEMENTAR
    private void selectPath(DocPath path) throws BadPathException {
    }

    private void printDocument(DocPath path) throws BadPathException,
            PrintingException {
    }

    private void downloadDocument(DocPath path) throws BadPathException {
    }
}