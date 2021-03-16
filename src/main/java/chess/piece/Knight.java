package chess.piece;

import java.util.Arrays;
import java.util.List;

public class Knight extends Piece {
    private static final double SCORE = 2.5;

    public Knight(int x, int y, boolean color) {
        super("n", SCORE, Position.Of(x, y), color);
    }

    public static Knight Of(Position position, boolean color) {
        return new Knight(position.getRow(), position.getColumn(), color);
    }

    public static List<Knight> initialKnightPieces() {
        return Arrays.asList(Knight.Of(Position.Of(0, 1), true),
                Knight.Of(Position.Of(0, 6), true),
                Knight.Of(Position.Of(7, 1), false),
                Knight.Of(Position.Of(7, 6), false));
    }
}
