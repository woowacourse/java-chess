package chess.domain.dto.request;

public class MoveRequest {

    private final String source;
    private final String target;

    public MoveRequest(final String source, final String target) {
        this.source = source;
        this.target = target;
    }

    public String source() {
        return source;
    }

    public String target() {
        return target;
    }
}
