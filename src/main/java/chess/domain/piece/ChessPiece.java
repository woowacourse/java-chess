package chess.domain.piece;

import chess.domain.Movement;
import chess.domain.Position;
import java.util.List;

public interface ChessPiece {

    void move();
    String name();
    void validateCanMove(Position origin, Position destination);
    List<Movement> findRoute(Position origin, Position destination);
    default boolean isEmpty() {
        return false;
    }
}
