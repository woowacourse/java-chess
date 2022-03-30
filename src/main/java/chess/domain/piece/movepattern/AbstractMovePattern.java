package chess.domain.piece.movepattern;

import chess.domain.board.Direction;
import java.util.List;

public abstract class AbstractMovePattern implements MovePattern {
    abstract List<Direction> getDirections();
}
