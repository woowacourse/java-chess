package chess.dto;

public class ResponseDTO {

    private final String code;
    private final String message;
    private final String turn;

    public ResponseDTO(String code, String message, String turn) {
        this.code = code;
        this.message = message;
        this.turn = turn;
    }
}
