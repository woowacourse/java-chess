package domain.chess.piece;

import java.util.Arrays;
import java.util.List;

public class Bishop extends Piece {
    private static final double SCORE = 3;

    public Bishop(String name, int x, int y, boolean isBlack) {
        super(name, SCORE, Position.Of(x, y), isBlack);
    }

    public static Bishop Of(String name, Position position, boolean color) {
        return new Bishop(name, position.getRow(), position.getColumn(), color);
    }

    public static List<Bishop> initialBishopPieces() {
        return Arrays.asList(Bishop.Of("B", Position.Of(0, 2), true),
                Bishop.Of("B",Position.Of(0, 5), true),
                Bishop.Of("b", Position.Of(7, 2), false),
                Bishop.Of("b", Position.Of(7, 5), false)
        );
    }
}
