package chess.domain.dto.response;

public final class Response {

    private final int code;
    private final String message;
    private final ResponseDto responseDto;

    public Response(final int code, final String message) {
        this(code, message, null);
    }

    public Response(final int code, final String message, final ResponseDto responseDto) {
        this.code = code;
        this.message = message;
        this.responseDto = responseDto;
    }
}
