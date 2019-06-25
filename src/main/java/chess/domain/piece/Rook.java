package chess.domain.piece;

import chess.domain.Position;

public class Rook extends Piece {
    private Rook(final Color color, final Position position) {
        super(Type.ROOK, color, position);
    }

    public static Rook createWhite(final Position position) {
        return new Rook(Color.WHITE, position);
    }

    public static Rook createBlack(final Position position) {
        return new Rook(Color.BLACK, position);
    }
    @Override
    public boolean isValidMove(final Position origin, final Position target) {
        return origin.isLevel(target) || origin.isPerpendicular(target);
    }
}