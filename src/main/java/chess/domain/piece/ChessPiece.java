package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Movement;
import chess.domain.Position;
import java.util.List;

public interface ChessPiece {

    String name();
    void validateCanMove(List<Movement> route, boolean isExistHurdleOnRoute, ChessPiece targetPiece);
    List<Movement> findRoute(Position origin, Position destination);
    default boolean isEmpty() {
        return false;
    }
    Color getColor();
    void capture();
    boolean isCaptured();
}
