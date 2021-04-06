package chess.domain.pieces;

import chess.domain.moving.KingMoving;
import chess.domain.position.Position;
import chess.domain.position.Row;
import chess.exception.WrongInitPositionException;

public final class King extends Piece {
    private static final String BLACK_TEAM_ROW = "8";
    private static final String WHITE_TEAM_ROW = "1";
    private static final int INIT_COL = 4;

    public King(final Position position) {
        super(position, Information.KING, new KingMoving());
    }

    public static King white(final int col) {
        validatesInitCol(col);
        return new King(new Position(Row.location(WHITE_TEAM_ROW), col));
    }

    public static King black(final int col) {
        validatesInitCol(col);
        return new King(new Position(Row.location(BLACK_TEAM_ROW), col));
    }

    private static void validatesInitCol(final int col) {
        if (col != INIT_COL) {
            throw new WrongInitPositionException();
        }
    }

    @Override
    public boolean isKing() {
        return true;
    }

    @Override
    public boolean isPawn() {
        return false;
    }
}
