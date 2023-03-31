package chess.service;

import chess.domain.position.Position;
import java.time.LocalDateTime;

public class Move {
    private final Position source;
    private final Position target;
    private final LocalDateTime moveTime;

    public Move(Position source, Position target, LocalDateTime moveTime) {
        this.source = source;
        this.target = target;
        this.moveTime = moveTime;
    }

    public static Move of(String source, String target, LocalDateTime moveTime) {
        Position sourcePosition = Position.of(source);
        Position targetPosition = Position.of(target);
        return new Move(sourcePosition, targetPosition, moveTime);
    }

    public static Move of(String source, String target) {
        return Move.of(source, target, LocalDateTime.now());
    }

    public LocalDateTime getMoveTime() {
        return moveTime;
    }

    public Position getSource() {
        return source;
    }

    public Position getTarget() {
        return target;
    }
}
