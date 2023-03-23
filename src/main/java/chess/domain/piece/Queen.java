package chess.domain.piece;

import chess.domain.position.MoveRange;
import chess.domain.position.Position;
import chess.domain.piece.info.Team;

public class Queen extends Piece {

    public Queen(final Team team) {
        super(team);
    }

    @Override
    public boolean canMove(final Position source, final Position destination) {
        if (source.equals(destination)) {
            return false;
        }
        return MoveRange.CROSS.validate(source, destination)
            || MoveRange.DIAGONAL.validate(source, destination);
    }

    @Override
    public boolean canAttack(final Position source, final Position destination) {
        return canMove(source, destination);
    }

    @Override
    public PieceType findType() {
        return PieceType.QUEEN;
    }
}
