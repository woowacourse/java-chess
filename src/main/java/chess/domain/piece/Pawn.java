package chess.domain.piece;

import chess.domain.Score;
import chess.domain.position.MoveRange;
import chess.domain.position.Position;
import chess.domain.piece.info.Team;
import java.util.Map;

public class Pawn extends Piece {

    public Pawn(final Team team) {
        super(team);
    }

    @Override
    public boolean canMove(final Position source, final Position destination) {
        if (source.equals(destination)) {
            return false;
        }
        if (team == Team.WHITE) {
            return MoveRange.ONE_UP.validate(source, destination)
                || (MoveRange.TWO_UP.validate(source, destination) && !trace.hasLog());
        }
        return MoveRange.ONE_DOWN.validate(source, destination)
            || (MoveRange.TWO_DOWN.validate(source, destination) && !trace.hasLog());
    }

    @Override
    public boolean canAttack(final Position source, final Position destination) {
        if (team == Team.WHITE) {
            return MoveRange.ONE_DIAGONAL_UP.validate(source, destination);
        }
        return MoveRange.ONE_DIAGONAL_DOWN.validate(source, destination);
    }

    @Override
    public PieceType findType() {
        return PieceType.PAWN;
    }

    @Override
    public Score calculateScore(Map<PieceType, Long> pieceCountBoard) {
        if (pieceCountBoard.getOrDefault(PieceType.PAWN, 0L) > 1L) {
            return new Score(0.5);
        }
        return new Score(1.0);
    }
}

