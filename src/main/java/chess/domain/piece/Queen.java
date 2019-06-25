package chess.domain.piece;

import chess.domain.Position;

public class Queen extends Piece {
    private Queen(final Color color, final Position position) {
        super(Type.QUEEN, color, position);
    }

    public static Queen createWhite(final Position position) {
        return new Queen(Color.WHITE, position);
    }

    public static Queen createBlack(final Position position) {
        return new Queen(Color.BLACK, position);
    }
    @Override
    public boolean isValidMove(final Position origin, final Position target) {
        return origin.isDiagonal(target) || origin.isPerpendicular(target) || origin.isLevel(target);
    }
}
