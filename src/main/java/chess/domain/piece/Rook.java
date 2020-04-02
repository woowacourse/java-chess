package chess.domain.piece;

import chess.domain.position.Position;


public class Rook extends Piece {
    public Rook(final PieceType pieceType, final Position position) {
        super(pieceType, position);
    }

    public static Piece createWhite(Position position) {
        return new Rook(PieceType.WHITE_ROOK, position);
    }

    public static Piece createBlack(Position position) {
        return new Rook(PieceType.BLACK_ROOK, position);
    }

    @Override
    public Piece moveTo(final Position toPosition) {
        return new Rook(pieceType, toPosition);
    }
}
