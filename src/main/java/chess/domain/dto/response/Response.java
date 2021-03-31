package chess.domain.dto.response;

public class Response {

    private final String code;
    private final String message;
    private final ResponseDto responseDto;

    public Response(final String code, final String message) {
        this(code, message, null);
    }

    public Response(final String code, final String message, final ResponseDto responseDto) {
        this.code = code;
        this.message = message;
        this.responseDto = responseDto;
    }
}
