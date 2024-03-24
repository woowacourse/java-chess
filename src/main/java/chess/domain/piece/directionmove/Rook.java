package chess.domain.piece.directionmove;

import chess.domain.board.Direction;
import chess.domain.piece.PieceType;
import chess.domain.piece.Team;
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
