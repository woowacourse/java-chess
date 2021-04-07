package chess.domain.pieces;

import chess.domain.moving.RookMoving;
import chess.domain.position.Position;
import chess.domain.position.Row;
import chess.exception.WrongInitPositionException;

public final class Rook extends NoKingPieces {
    private static final String BLACK_TEAM_ROW = "8";
    private static final String WHITE_TEAM_ROW = "1";
    private static final int LEFT_SIDE_INIT_COL = 0;
    private static final int RIGHT_SIDE_INIT_COL = 7;

    public Rook(final Position position) {
        super(position, Information.ROOK, new RookMoving());
    }

    public static Rook white(final int col) {
        validatesInitCol(col);
        return new Rook(new Position(Row.location(WHITE_TEAM_ROW), col));
    }

    public static Rook black(final int col) {
        validatesInitCol(col);
        return new Rook(new Position(Row.location(BLACK_TEAM_ROW), col));
    }

    private static void validatesInitCol(final int col) {
        if (col != LEFT_SIDE_INIT_COL && col != RIGHT_SIDE_INIT_COL) {
            throw new WrongInitPositionException();
        }
    }
}
