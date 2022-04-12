package web.dto;

public class MoveResponse {

    private final String code;
    private final String message;
    private final String turn;

    public MoveResponse(String code, String message, String turn) {
        this.code = code;
        this.message = message;
        this.turn = turn;
    }
}
