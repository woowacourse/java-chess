package chess.dto.request;

import chess.domain.board.position.Position;
import java.util.List;

public class MoveCommandDto {

    private final Position source;
    private final Position target;

    public MoveCommandDto(String source, String target) {
        this.source = Position.of(source);
        this.target = Position.of(target);
    }

    public Position source() {
        return source;
    }

    public Position target() {
        return target;
    }

    public List<Position> getPositions() {
        return List.of(source, target);
    }
}
