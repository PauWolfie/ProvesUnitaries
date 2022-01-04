package SSdocs;

import data.DocPath;
import data.exceptions.EmptyException;
import data.exceptions.WrongFormedException;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.Date;

public class PDFDocument {
    private final Date creatDate;
    private final DocPath path;
    private final File file;

    public PDFDocument(Date date, DocPath path, File file) throws EmptyException, WrongFormedException {
        creatDate = date;
        this.path = path;
        this.file = file;
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

    public String toString() {
        // Converts to String members Date and DocPath
        return "PDF Document{" + "Fecha de creación='" + creatDate + "Directorio='" + path + '\'' + '}';
    }

    public void moveDoc(DocPath destPath) throws IOException {
        boolean canRename = false;
        canRename = file.renameTo(new File(destPath.getPath()));

        if (!canRename) {
            throw new IOException("No s'ha pogut moure l'arxiu");
        }
    }

    public void openDoc(DocPath path) throws IOException {
        try {
            File pathToUse = new File(path.getPath());
            Desktop.getDesktop().open(pathToUse);
        } catch (IOException ex) {
            throw new IOException("No s'ha pogut obrir l'arxiu");
        }
    }
}
