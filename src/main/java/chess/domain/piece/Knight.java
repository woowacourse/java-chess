package chess.domain.piece;

import chess.domain.position.Position;

public class Knight extends Piece {
    public Knight(final PieceType pieceType, final char representation, final Turn turn, final Position position) {
        super(pieceType, representation, turn, position);
    }

    public static Piece createWhite(final Position position) {
        return new Knight(PieceType.KNIGHT, 'n', Turn.WHITE, position);
    }

    public static Piece createBlack(final Position position) {
        return new Knight(PieceType.KNIGHT, 'N', Turn.BLACK, position);
    }

    @Override
    public Piece moveTo(final Position toPosition) {
        return new Knight(pieceType, representation, turn, toPosition);
    }
}
