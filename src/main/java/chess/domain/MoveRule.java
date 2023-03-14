package chess.domain;

public interface MoveRule {
    Position move(Position currentPosition, Position nextPosition);
}
