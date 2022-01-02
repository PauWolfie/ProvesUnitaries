package SSdocs;

import data.DocPath;
import data.exceptions.EmptyException;
import data.exceptions.WrongFormedException;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.Date;

public class PDFDocument {
    private Date creatDate;
    private DocPath path;
    private File file;

    public PDFDocument () throws EmptyException, WrongFormedException {
        creatDate = Date.from(Instant.now());
        path = new DocPath("EmulatedDirectory/");
        file = new File("defaultFilename.pdf");
    }

    public Date getCreatDate() {
        return creatDate;
    }

    public DocPath getPath() {
        return path;
    }

    public File getFile() {
        return file;
    }

    public String toString () {
        // Converts to String members Date and DocPath
        return "PDF Document{" + "Fecha de creación='" + creatDate + "Directorio='" + path + '\'' + '}';
    }

    // To implement only optionally
    public void moveDoc (DocPath destPath) throws IOException {
        // Moves the document to the destination path indicated
    }
    public void openDoc (DocPath path) throws IOException{
        // Opens the document at the path indicated
    }
}
