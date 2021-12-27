package exceptions.dataExceptions;

public class EmptyNIFException extends Exception{
    public EmptyNIFException(){
        super("El camp NIF no pot estar buit");
    }
}
