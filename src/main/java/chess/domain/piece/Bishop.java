package chess.domain.piece;

import chess.domain.position.Position;

public class Bishop extends Piece {
    public Bishop(final PieceType pieceType, final char representation, final Team team, final Position position) {
        super(pieceType, representation, team, position);
    }

    public static Piece createWhite(final Position position) {
        return new Bishop(PieceType.BISHOP, 'b', Team.WHITE, position);
    }

    public static Piece createBlack(final Position position) {
        return new Bishop(PieceType.BISHOP, 'B', Team.BLACK, position);
    }

    @Override
    public Piece moveTo(final Position toPosition) {
        return new Bishop(pieceType, representation, team, toPosition);
    }
}
