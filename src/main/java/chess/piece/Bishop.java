package chess.piece;

import java.util.Arrays;
import java.util.List;

public class Bishop extends Piece {
    private static final double SCORE = 3;

    public Bishop(int x, int y, boolean color) {
        super("b", SCORE, Position.Of(x, y), color);
    }

    public static Bishop Of(Position position, boolean color) {
        return new Bishop(position.getRow(), position.getColumn(), color);
    }

    public static List<Bishop> initialBishopPieces() {
        return Arrays.asList(Bishop.Of(Position.Of(0, 2), true),
                Bishop.Of(Position.Of(0, 5), true),
                Bishop.Of(Position.Of(7, 2), false),
                Bishop.Of(Position.Of(7, 5), false)
        );
    }
}
