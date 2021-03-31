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
}
