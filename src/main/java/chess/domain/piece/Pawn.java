package chess.domain.piece;

import chess.domain.camp.TeamColor;
import chess.domain.position.Position;

import java.util.Objects;

public abstract class Pawn extends Piece {
    protected static final int PAWN_MAX_MOVE_COUNT = 1;
    protected static final int PAWN_FIRST_MAX_MOVE_COUNT = 2;

    Pawn(final PieceType pieceType, final TeamColor teamColor) {
        super(pieceType, teamColor);
    }

    public boolean canAttack(Position source, Position target, Piece piece) {
        return target.isDiagonalPosition(source) && !Objects.isNull(piece) && !piece.isSameTeam(teamColor);
    }
}
