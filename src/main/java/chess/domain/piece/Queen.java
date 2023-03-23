package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.position.PossibleDestinations;

public class Queen extends Piece {
    private static final int QUEEN_MAX_MOVE_COUNT = 8;

    public Queen(final PieceType pieceType, final TeamColor teamColor) {
        super(pieceType, teamColor);
    }

    @Override
    public boolean canMove(final Position source, final Position target, final Piece targetPiece) {
        final PossibleDestinations allPositions =
                PossibleDestinations.of(source, Direction.getAllDirections(), QUEEN_MAX_MOVE_COUNT);
        return allPositions.contains(target);
    }
}
