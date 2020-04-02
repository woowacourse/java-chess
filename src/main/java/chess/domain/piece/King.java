package chess.domain.piece;

import chess.domain.position.Position;

public class King extends Piece {
    public King(final PieceType pieceType, final Position position) {
        super(pieceType, position);
    }

    public static Piece createWhite(Position position) {
        return new King(PieceType.WHITE_KING, position);
    }

    public static Piece createBlack(Position position) {
        return new King(PieceType.BLACK_KING, position);
    }

    @Override
    public Piece moveTo(final Position toPosition) {
        return new King(pieceType, toPosition);
    }
}
