package chess.web;

public class WebException {

    private final String message;

    private WebException(String message) {
        this.message = message;
    }

    public static WebException from(String message) {
        return new WebException(message);
    }

    public String getMessage() {
        return message;
    }
}
