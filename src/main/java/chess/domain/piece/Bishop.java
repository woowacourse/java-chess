package chess.domain.piece;

import chess.domain.Score;
import chess.domain.position.MoveRange;
import chess.domain.position.Position;
import chess.domain.piece.info.Team;
import java.util.Map;

public class Bishop extends Piece {

    public Bishop(Team team) {
        super(team);
    }

    @Override
    public boolean canMove(final Position source, final Position destination) {
        if (source.equals(destination)) {
            return false;
        }
        return MoveRange.DIAGONAL.validate(source, destination);
    }

    @Override
    public boolean canAttack(final Position source, final Position destination) {
        return canMove(source, destination);
    }

    @Override
    public PieceType findType() {
        return PieceType.BISHOP;
    }

    @Override
    public Score calculateScore(Map<PieceType, Long> pieceCountBoard) {
        return new Score(3.0);
    }
}
