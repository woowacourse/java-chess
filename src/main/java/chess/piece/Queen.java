package chess.piece;

import java.util.Arrays;
import java.util.List;

public class Queen extends Piece {
    private static final double SCORE = 9;

    public Queen(int x, int y, boolean color) {
        super("q", SCORE, Position.Of(x, y), color);
    }

    public static Queen Of(Position position, boolean color) {
        return new Queen(position.getRow(), position.getColumn(), color);
    }

    public static List<Queen> initialQueenPieces() {
        return Arrays.asList(Queen.Of(Position.Of(0, 3), true),
                Queen.Of(Position.Of(7, 3), false));
    }
}
