package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.position.PossibleDestinations;

public class King extends Piece {
    private static final int KING_MAX_MOVE_COUNT = 1;
    private static final Double VALUE = 0.0;

    public King(final PieceType pieceType, final TeamColor teamColor) {
        super(pieceType, teamColor);
    }

    @Override
    public boolean canMove(final Position source, final Position target, final Piece targetPiece) {
        final PossibleDestinations allPositions =
                PossibleDestinations.of(source, Direction.getAllDirections(), KING_MAX_MOVE_COUNT);
        return allPositions.contains(target);
    }

    @Override
    public boolean isKing() {
        return true;
    }

    @Override
    public Double getValue() {
        return VALUE;
    }
}
