package chess.domain.piece.movepattern;

import chess.domain.board.Direction;
import java.util.List;

public final class KnightMovePattern extends AbstractOnceMovePattern {
    @Override
    public List<Direction> getDirections() {
        return Direction.getKnightDirections();
    }
}
