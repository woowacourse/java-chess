package domain.chess.piece;

import java.util.Arrays;
import java.util.List;

public class Rook extends Piece {
    private static final double SCORE = 5;

    public Rook(String name, int x, int y, boolean isBlack) {
        super(name, SCORE, Position.Of(x, y), isBlack);
    }

    public static Rook Of(String name, Position position, boolean color) {
        return new Rook(name, position.getRow(), position.getColumn(), color);
    }

    public static List<Rook> initialRookPieces() {
        return Arrays.asList(Rook.Of("R", Position.Of(0, 0), true),
                Rook.Of("R", Position.Of(0, 7), true),
                Rook.Of("r", Position.Of(7, 0), false),
                Rook.Of("r", Position.Of(7, 7), false));
    }
}
