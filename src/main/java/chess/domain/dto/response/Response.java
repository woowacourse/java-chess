package chess.domain.dto.response;

public class Response {

    private final String code;
    private final String message;

    public Response(final String code, final String message) {
        this.code = code;
        this.message = message;
    }
}
