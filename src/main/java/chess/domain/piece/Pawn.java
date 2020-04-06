package chess.domain.piece;

import chess.domain.position.Position;

public class Pawn extends Piece {
    public Pawn(final PieceType pieceType, final char representation, final Turn turn, final Position position) {
        super(pieceType, representation, turn, position);
    }

    public static Piece createWhite(final Position position) {
        return new Pawn(PieceType.FIRST_WHITE_PAWN, 'p', Turn.WHITE, position);
    }

    public static Piece createBlack(final Position position) {
        return new Pawn(PieceType.FIRST_BLACK_PAWN, 'P', Turn.BLACK, position);
    }

    @Override
    public Piece moveTo(final Position toPosition) {
        return new Pawn(pieceType.notFirstStep(), representation, turn, toPosition);
    }
}
