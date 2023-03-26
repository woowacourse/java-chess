package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.position.PossibleDestinations;

public class Bishop extends Piece {
    private static final int BISHOP_MAX_MOVE_COUNT = 8;
    private static final Double VALUE = 3.0;

    public Bishop(final PieceType pieceType, final TeamColor teamColor) {
        super(pieceType, teamColor);
    }

    @Override
    public boolean canMove(final Position source, final Position target, final Piece targetPiece) {
        final PossibleDestinations allPositions =
                PossibleDestinations.of(source, Direction.getDiagonalDirections(), BISHOP_MAX_MOVE_COUNT);
        return allPositions.contains(target);
    }

    @Override
    public Double getValue() {
        return VALUE;
    }
}
