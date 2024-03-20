package chess.domain;

public interface MoveStrategy {
    MoveStrategy changeStrategy(Position from);

    void move(Color turnColor, Position from, Position to);
}
