package domain.chess.piece;

import java.util.Arrays;
import java.util.List;

public class Rook extends Piece {
    private static final double SCORE = 5;

    public Rook(int x, int y, boolean color) {
        super("r", SCORE, Position.Of(x, y), color);
    }

    public static Rook Of(Position position, boolean color) {
        return new Rook(position.getRow(), position.getColumn(), color);
    }

    public static List<Rook> initialRookPieces() {
        return Arrays.asList(Rook.Of(Position.Of(0, 0), true),
                Rook.Of(Position.Of(0, 7), true),
                Rook.Of(Position.Of(7, 0), false),
                Rook.Of(Position.Of(7, 7), false));
    }
}
