package chess.dto;

public class ResponseDto {
    private final String code;
    private final String message;
    private final String turn;

    public ResponseDto(final String code, final String message, final String turn) {
        this.code = code;
        this.message = message;
        this.turn = turn;
    }
}
