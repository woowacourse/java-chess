package chess.domain.piece;

import chess.domain.position.Position;


public class Rook extends Piece {
    public Rook(final PieceType pieceType, final char representation, final Turn turn, final Position position) {
        super(pieceType, representation, turn, position);
    }

    public static Piece createWhite(final Position position) {
        return new Rook(PieceType.ROOK, 'r', Turn.WHITE, position);
    }

    public static Piece createBlack(final Position position) {
        return new Rook(PieceType.ROOK, 'R', Turn.BLACK, position);
    }

    @Override
    public Piece moveTo(final Position toPosition) {
        return new Rook(pieceType, representation, turn, toPosition);
    }
}
