package chess.domain.pieces;

import chess.domain.moving.BishopMoving;
import chess.domain.position.Position;
import chess.domain.position.Row;
import chess.exception.WrongInitPositionException;

public final class Bishop extends NoKingPieces {
    private static final String BLACK_TEAM_ROW = "8";
    private static final String WHITE_TEAM_ROW = "1";
    private static final int LEFT_SIDE_INIT_COL = 2;
    private static final int RIGHT_SIDE_INIT_COL = 5;

    public Bishop(final Position position) {
        super(position, Information.BISHOP, new BishopMoving());
    }

    public static Bishop white(final int col) {
        return new Bishop(new Position(Row.location(WHITE_TEAM_ROW), validatesInitCol(col)));
    }

    public static Bishop black(final int col) {
        return new Bishop(new Position(Row.location(BLACK_TEAM_ROW), validatesInitCol(col)));
    }

    private static int validatesInitCol(final int col) {
        if (col != LEFT_SIDE_INIT_COL && col != RIGHT_SIDE_INIT_COL) {
            throw new WrongInitPositionException();
        }
        return col;
    }
}
