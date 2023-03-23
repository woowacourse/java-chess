package chess.domain.piece;

import chess.domain.Score;
import chess.domain.position.MoveRange;
import chess.domain.position.Position;
import chess.domain.piece.info.Team;
import java.util.Map;

public class Knight extends Piece {

    public Knight(Team team) {
        super(team);
    }

    @Override
    public boolean canMove(final Position source, final Position destination) {
        if (source.equals(destination)) {
            return false;
        }
        return MoveRange.L_SHAPED.validate(source, destination);
    }

    @Override
    public boolean canAttack(final Position source, final Position destination) {
        return canMove(source, destination);
    }

    @Override
    public PieceType findType() {
        return PieceType.KNIGHT;
    }

    @Override
    public Score calculateScore(Map<PieceType, Long> pieceCountBoard) {
        return new Score(2.5);
    }
}
