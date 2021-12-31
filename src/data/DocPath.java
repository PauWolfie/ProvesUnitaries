package data;

import exceptions.dataExceptions.EmptyException;
import exceptions.dataExceptions.WrongFormedException;

import java.io.File;

/**
 * Essential data classes
 */
public class DocPath {
    // Path with the actual doc
    private final String path;

    public DocPath(String docpath) throws EmptyException, WrongFormedException {
        if (docpath == null){
            throw new NullPointerException("Null reference");
        }
        if (docpath.isEmpty()){
            throw new EmptyException("El directori no pot estar buit");
        }

        File file = new File(docpath);
        if (!file.exists()) {
            throw new WrongFormedException("El directori no existeix");
        }
        this.path = docpath;
    }

    public String getPath() {
        return path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocPath pathToComp = (DocPath) o;
        return path.equals(pathToComp.path);
    }

    @Override
    public int hashCode() {
        return path.hashCode();
    }

    @Override
    public String toString() {
        return "Path of the document{" + "Dirección='" + path + '\'' + '}';
    }

}
