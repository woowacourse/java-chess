package chess.domain.piece.movepattern;

import chess.domain.board.Direction;
import java.util.List;

public final class BishopMovePattern extends AbstractStraightMovePattern {
    @Override
    public List<Direction> getDirections() {
        return Direction.getBishopDirections();
    }
}
