package chess.domain.piece;

import chess.domain.position.Position;

public class King extends Piece {
    public King(final PieceType pieceType, final char representation, final Team team, final Position position) {
        super(pieceType, representation, team, position);
    }

    public static Piece createWhite(Position position) {
        return new King(PieceType.KING, 'k', Team.WHITE, position);
    }

    public static Piece createBlack(Position position) {
        return new King(PieceType.KING, 'K', Team.BLACK, position);
    }

    @Override
    public Piece moveTo(final Position toPosition) {
        return new King(pieceType, representation, team, toPosition);
    }
}
