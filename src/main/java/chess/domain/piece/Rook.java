package chess.domain.piece;

import chess.domain.camp.TeamColor;
import chess.domain.position.Position;

import java.util.Set;

public class Rook extends Piece {
    private static final int ROOK_MAX_MOVE_COUNT = 8;

    public Rook(final PieceType pieceType, final TeamColor teamColor) {
        super(pieceType, teamColor);
    }

    @Override
    public boolean canMove(final Position source, final Position target) {
        final Set<Position> allPositions = move.getAllPositions(source, Direction.getFourDirections(), ROOK_MAX_MOVE_COUNT);
        return allPositions.contains(target);
    }
}
