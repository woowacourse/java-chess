package chess.domain.piece;

import chess.domain.position.Position;

public class Bishop extends Piece {
    public Bishop(final PieceType pieceType, final char representation, final Turn turn, final Position position) {
        super(pieceType, representation, turn, position);
    }

    public static Piece createWhite(final Position position) {
        return new Bishop(PieceType.BISHOP, 'b', Turn.WHITE, position);
    }

    public static Piece createBlack(final Position position) {
        return new Bishop(PieceType.BISHOP, 'B', Turn.BLACK, position);
    }

    @Override
    public Piece moveTo(final Position toPosition) {
        return new Bishop(pieceType, representation, turn, toPosition);
    }
}
