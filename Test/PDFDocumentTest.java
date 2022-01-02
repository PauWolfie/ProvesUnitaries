import SSdocs.PDFDocument;
import data.exceptions.EmptyException;
import data.exceptions.WrongFormedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class PDFDocumentTest {
    PDFDocument doc;

    @BeforeEach
    void setDoc() throws EmptyException, WrongFormedException {
        doc = new PDFDocument();
    }

    @Test
    void toStringTest() {
        assertEquals(Date.from(Instant.now()),doc.toString());
    }

    @Test
    void moveDocTest() {

    }

    @Test
    void openDocTest() {

    }
}
