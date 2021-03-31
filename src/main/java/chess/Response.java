package chess;

public class Response {

    private final String code;
    private final String message;
    private final String turn;

    public Response(String code, String message, String turn) {
        this.code = code;
        this.message = message;
        this.turn = turn;
    }

    public String getTurn() {
        return turn;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
