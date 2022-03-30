package chess.domain.piece.movepattern;

import chess.domain.board.Direction;
import java.util.List;

public final class KingMovePattern extends AbstractOnceMovePattern {
    @Override
    List<Direction> getDirections() {
        return Direction.getKingDirections();
    }
}
