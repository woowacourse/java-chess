package chess.domain.piece;

import chess.domain.position.Position;

public class Knight extends Piece {
    public Knight(final PieceType pieceType, final char representation, final Team team, final Position position) {
        super(pieceType, representation, team, position);
    }

    public static Piece createWhite(Position position) {
        return new Knight(PieceType.KNIGHT, 'n', Team.WHITE, position);
    }

    public static Piece createBlack(Position position) {
        return new Knight(PieceType.KNIGHT, 'N', Team.BLACK, position);
    }

    @Override
    public Piece moveTo(final Position toPosition) {
        return new Knight(pieceType, representation, team, toPosition);
    }
}
