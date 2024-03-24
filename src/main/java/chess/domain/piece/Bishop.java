package chess.domain.piece;

import chess.domain.board.Direction;
import java.util.Set;

public class Bishop extends DirectionMovePiece {

    public Bishop(Team team) {
        super(PieceType.BISHOP, team);
    }

    @Override
    Set<Direction> legalDirections() {
        return Set.of(Direction.LEFT_UP, Direction.LEFT_DOWN, Direction.RIGHT_DOWN, Direction.RIGHT_UP);
    }
}
