package chess.controller.dto.request;

import chess.domain.Position;

public class MoveRequest {

    private final String source;
    private final String target;

    public MoveRequest(String source, String target) {
        this.source = source;
        this.target = target;
    }

    public String getSource() {
        return source;
    }

    public String getTarget() {
        return target;
    }

    public Position toSourcePosition() {
        return Position.from(source);
    }

    public Position toTargetPosition() {
        return Position.from(target);
    }
}
