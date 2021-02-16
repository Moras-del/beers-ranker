package pl.moras.auth.exceptions;

public class ObjectAlreadyExists extends RuntimeException {
    public ObjectAlreadyExists(String object) {
        super(object+" already exists");
    }
}
