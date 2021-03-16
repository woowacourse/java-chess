package chess.piece;

import java.util.Arrays;
import java.util.List;

public class King extends Piece {
    private static final double SCORE = 0;

    public King(int x, int y, boolean color) {
        super("k", SCORE, Position.Of(x, y), color);
    }

    public static King Of(Position position, boolean color) {
        return new King(position.getRow(), position.getColumn(), color);
    }

    public static List<King> initialKingPieces() {
        return Arrays.asList(King.Of(Position.Of(0, 4), true),
                King.Of(Position.Of(7, 4), false));
    }
}
