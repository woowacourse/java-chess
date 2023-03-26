package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.position.PossibleDestinations;

public class Rook extends Piece {
    private static final int ROOK_MAX_MOVE_COUNT = 8;
    private static final Double Value = 5.0;

    public Rook(final PieceType pieceType, final TeamColor teamColor) {
        super(pieceType, teamColor);
    }

    @Override
    public boolean canMove(final Position source, final Position target, final Piece targetPiece) {
        final PossibleDestinations allPositions =
                PossibleDestinations.of(source, Direction.getFourDirections(), ROOK_MAX_MOVE_COUNT);
        return allPositions.contains(target);
    }
}
