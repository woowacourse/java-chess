package chess.domain.piece.strategy;

public interface MoveStrategy {

    boolean movable();
    void move();
}
