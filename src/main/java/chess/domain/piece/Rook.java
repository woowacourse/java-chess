package chess.domain.piece;

import chess.domain.position.Position;


public class Rook extends Piece {
    public Rook(final PieceType pieceType, final char representation, final Team team, final Position position) {
        super(pieceType, representation, team, position);
    }

    public static Piece createWhite(final Position position) {
        return new Rook(PieceType.ROOK, 'r', Team.WHITE, position);
    }

    public static Piece createBlack(final Position position) {
        return new Rook(PieceType.ROOK, 'R', Team.BLACK, position);
    }

    @Override
    public Piece moveTo(final Position toPosition) {
        return new Rook(pieceType, representation, team, toPosition);
    }
}
