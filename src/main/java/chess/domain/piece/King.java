package chess.domain.piece;

import chess.domain.position.Position;

public class King extends Piece {
    public King(final PieceType pieceType, final char representation, final Turn turn, final Position position) {
        super(pieceType, representation, turn, position);
    }

    public static Piece createWhite(final Position position) {
        return new King(PieceType.KING, 'k', Turn.WHITE, position);
    }

    public static Piece createBlack(final Position position) {
        return new King(PieceType.KING, 'K', Turn.BLACK, position);
    }

    @Override
    public Piece moveTo(final Position toPosition) {
        return new King(pieceType, representation, turn, toPosition);
    }
}
