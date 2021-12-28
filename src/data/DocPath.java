package data;

import javax.print.Doc;

/**
 * Essential data classes
 */
public class DocPath {
    // The accreditation number of the SS
    private final String path;

    public DocPath(String Docpath) {
        this.path = Docpath;
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
