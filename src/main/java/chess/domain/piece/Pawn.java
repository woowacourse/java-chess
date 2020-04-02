package chess.domain.piece;

import chess.domain.position.Position;

public class Pawn extends Piece {
    public Pawn(final PieceType pieceType, final Position position) {
        super(pieceType, position);
    }

    public static Piece createWhite(Position position) {
        return new Pawn(PieceType.FIRST_WHITE_PAWN, position);
    }

    public static Piece createBlack(Position position) {
        return new Pawn(PieceType.FIRST_BLACK_PAWN, position);
    }

    @Override
    public Piece moveTo(final Position toPosition) {
        return new Pawn(pieceType.notFirstStep(), toPosition);
    }
}
