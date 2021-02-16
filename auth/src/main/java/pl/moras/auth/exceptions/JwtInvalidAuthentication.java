package pl.moras.auth.exceptions;

public class JwtInvalidAuthentication extends RuntimeException {
    public JwtInvalidAuthentication(String s) {
        super(s);
    }
}
