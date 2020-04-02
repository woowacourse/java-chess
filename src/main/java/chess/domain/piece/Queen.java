package chess.domain.piece;

import chess.domain.position.Position;

public class Queen extends Piece {
    public Queen(final PieceType pieceType, final Position position) {
        super(pieceType, position);
    }

    public static Piece createWhite(Position position) {
        return new Queen(PieceType.WHITE_QUEEN, position);
    }

    public static Piece createBlack(Position position) {
        return new Queen(PieceType.BLACK_QUEEN, position);
    }

    @Override
    public Piece moveTo(final Position toPosition) {
        return new Queen(pieceType, toPosition);
    }
}
