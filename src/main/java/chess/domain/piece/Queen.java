package chess.domain.piece;

import chess.domain.position.Position;

public class Queen extends Piece {
    public Queen(final PieceType pieceType, final char representation, final Team team, final Position position) {
        super(pieceType, representation, team, position);
    }

    public static Piece createWhite(final Position position) {
        return new Queen(PieceType.QUEEN, 'q', Team.WHITE, position);
    }

    public static Piece createBlack(final Position position) {
        return new Queen(PieceType.QUEEN, 'Q', Team.BLACK, position);
    }

    @Override
    public Piece moveTo(final Position toPosition) {
        return new Queen(pieceType, representation, team, toPosition);
    }
}
