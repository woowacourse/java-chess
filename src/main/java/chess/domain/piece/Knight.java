package chess.domain.piece;

import chess.domain.position.Position;

public class Knight extends Piece {
    public Knight(final PieceType pieceType, final Position position) {
        super(pieceType, position);
    }

    public static Piece createWhite(Position position) {
        return new Knight(PieceType.WHITE_KNIGHT, position);
    }

    public static Piece createBlack(Position position) {
        return new Knight(PieceType.BLACK_KNIGHT, position);
    }

    @Override
    public Piece moveTo(final Position toPosition) {
        return new Knight(pieceType, toPosition);
    }
}
