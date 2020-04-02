package chess.domain.piece;

import chess.domain.position.Position;

public class Bishop extends Piece {
    public Bishop(final PieceType pieceType, final Position position) {
        super(pieceType, position);
    }

    public static Piece createWhite(Position position) {
        return new Bishop(PieceType.WHITE_BISHOP, position);
    }

    public static Piece createBlack(Position position) {
        return new Bishop(PieceType.BLACK_BISHOP, position);
    }

    @Override
    public Piece moveTo(final Position toPosition) {
        return new Bishop(pieceType, toPosition);
    }
}
