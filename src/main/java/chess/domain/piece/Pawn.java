package chess.domain.piece;

import chess.domain.position.Position;

public class Pawn extends Piece {
    public Pawn(final PieceType pieceType, final char representation, final Team team, final Position position) {
        super(pieceType, representation, team, position);
    }

    public static Piece createWhite(final Position position) {
        return new Pawn(PieceType.FIRST_WHITE_PAWN, 'p', Team.WHITE, position);
    }

    public static Piece createBlack(final Position position) {
        return new Pawn(PieceType.FIRST_BLACK_PAWN, 'P', Team.BLACK, position);
    }

    @Override
    public Piece moveTo(final Position toPosition) {
        return new Pawn(pieceType.notFirstStep(), representation, team, toPosition);
    }
}
