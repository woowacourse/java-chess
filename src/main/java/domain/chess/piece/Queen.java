package domain.chess.piece;

import java.util.Arrays;
import java.util.List;

public class Queen extends Piece {
    private static final double SCORE = 9;

    public Queen(String name, int x, int y, boolean isBlack) {
        super(name, SCORE, Position.Of(x, y), isBlack);
    }

    public static Queen Of(String name, Position position, boolean color) {
        return new Queen(name, position.getRow(), position.getColumn(), color);
    }

    public static List<Queen> initialQueenPieces() {
        return Arrays.asList(Queen.Of("Q", Position.Of(0, 3), true),
                Queen.Of("q", Position.Of(7, 3), false));
    }
}
