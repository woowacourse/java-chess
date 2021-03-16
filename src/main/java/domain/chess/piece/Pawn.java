package domain.chess.piece;

import java.util.Arrays;
import java.util.List;

public class Pawn extends Piece {
    private static final double SCORE = 3;

    public Pawn(String name, int x, int y, boolean isBlack) {
        super(name, SCORE, Position.Of(x, y), isBlack);
    }

    public static Pawn Of(String name, Position position, boolean color) {
        return new Pawn(name, position.getRow(), position.getColumn(), color);
    }

    public static List<Pawn> initialPawnPieces() {
        return Arrays.asList(Pawn.Of("P", Position.Of(1, 0), true),
                Pawn.Of("P",Position.Of(1, 1), true),
                Pawn.Of("P",Position.Of(1, 2), true),
                Pawn.Of("P",Position.Of(1, 3), true),
                Pawn.Of("P",Position.Of(1, 4), true),
                Pawn.Of("P",Position.Of(1, 5), true),
                Pawn.Of("P",Position.Of(1, 6), true),
                Pawn.Of("P",Position.Of(1, 7), true),
                Pawn.Of("p",Position.Of(6, 0), false),
                Pawn.Of("p",Position.Of(6, 1), false),
                Pawn.Of("p",Position.Of(6, 2), false),
                Pawn.Of("p",Position.Of(6, 3), false),
                Pawn.Of("p",Position.Of(6, 4), false),
                Pawn.Of("p",Position.Of(6, 5), false),
                Pawn.Of("p",Position.Of(6, 6), false),
                Pawn.Of("p",Position.Of(6, 7), false));
    }
}
