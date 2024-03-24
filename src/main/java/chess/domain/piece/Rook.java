package chess.domain.piece;

import chess.domain.board.Direction;
import java.util.Set;

public class Rook extends DirectionMovePiece {

    public Rook(Team team) {
        super(PieceType.ROOK, team);
    }

    @Override
    Set<Direction> legalDirections() {
        return Set.of(
                Direction.UP,
                Direction.DOWN,
                Direction.LEFT,
                Direction.RIGHT
        );
    }
}
