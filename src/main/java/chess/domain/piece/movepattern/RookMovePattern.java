package chess.domain.piece.movepattern;

import chess.domain.board.Direction;
import java.util.List;

public final class RookMovePattern extends AbstractStraightMovePattern {
    @Override
    public List<Direction> getDirections() {
        return Direction.getRookDirections();
    }
}
