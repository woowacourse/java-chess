package chess.domain.pieces;

import chess.domain.moving.QueenMoving;
import chess.domain.position.Position;
import chess.domain.position.Row;
import chess.exception.WrongInitPositionException;

public final class Queen extends NoKingPieces {
    private static final String BLACK_TEAM_ROW = "8";
    private static final String WHITE_TEAM_ROW = "1";
    private static final int INIT_COL = 3;

    public Queen(final Position position) {
        super(position, Information.QUEEN, new QueenMoving());
    }

    public static Queen white(final int col) {
        validatesInitCol(col);
        return new Queen(new Position(Row.location(WHITE_TEAM_ROW), col));
    }

    public static Queen black(final int col) {
        validatesInitCol(col);
        return new Queen(new Position(Row.location(BLACK_TEAM_ROW), col));
    }

    private static void validatesInitCol(final int col) {
        if (col != INIT_COL) {
            throw new WrongInitPositionException();
        }
    }
}
