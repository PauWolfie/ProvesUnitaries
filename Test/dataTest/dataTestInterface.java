package dataTest;

import exceptions.dataExceptions.EmptyException;
import exceptions.dataExceptions.WrongFormedException;
import org.junit.jupiter.api.Test;


public interface dataTestInterface {
    /**
     * Comprova la correcta implementació per al cas on les dades introduides són correctes
     */
    @Test
    void correctTest() throws WrongFormedException, EmptyException;

    /**
     * Comprova la correcta execució de les excepcions en el cas que la referència passada sigui nul·la o no contingui res.
     **/
    @Test
    void emptyTest();

    /**
     * Comprova la correcta execució de les excepcions en els casos que l'atribut estigui mal format.
     */
    @Test
    void wrongFormatTest();

    /**
     * Comprova la correcta execució del mètode equals.
     */
    @Test
    void compareTest() throws WrongFormedException, EmptyException;
}
