package domain.chess.piece;

import java.util.Arrays;
import java.util.List;

public class Pawn extends Piece {
    private static final double SCORE = 3;

    private Pawn(String name, int x, int y, boolean isBlack) {
        super(name, SCORE, Position.Of(x, y), isBlack);
    }

    public static Pawn Of(String name, Position position, boolean color) {
        return new Pawn(name, position.getRow(), position.getColumn(), color);
    }

    public static List<Pawn> initialPawnPieces() {
        return Arrays.asList(Pawn.Of("P", Position.Of(1, 0), true),
                Pawn.Of("P", Position.Of(1, 1), true),
                Pawn.Of("P", Position.Of(1, 2), true),
                Pawn.Of("P", Position.Of(1, 3), true),
                Pawn.Of("P", Position.Of(1, 4), true),
                Pawn.Of("P", Position.Of(1, 5), true),
                Pawn.Of("P", Position.Of(1, 6), true),
                Pawn.Of("P", Position.Of(1, 7), true),
                Pawn.Of("p", Position.Of(6, 0), false),
                Pawn.Of("p", Position.Of(6, 1), false),
                Pawn.Of("p", Position.Of(6, 2), false),
                Pawn.Of("p", Position.Of(6, 3), false),
                Pawn.Of("p", Position.Of(6, 4), false),
                Pawn.Of("p", Position.Of(6, 5), false),
                Pawn.Of("p", Position.Of(6, 6), false),
                Pawn.Of("p", Position.Of(6, 7), false));
    }

    @Override
    public boolean canMove(Piece[][] board, Position endPosition) {
        int x = endPosition.getRow();
        int y = endPosition.getColumn();

        if (forward(x, y, 1)) return true;
        if (forward(x, y, 2)) return true;

        if (catchDiagonal(board, x, y, true)) return true;
        if (catchDiagonal(board, x, y, false)) return true;

        return false;
    }

    private boolean catchDiagonal(Piece[][] board, int x, int y, boolean isLeft) {
        int rowStep = 1;
        if (!isBlack()) {
            rowStep = -rowStep;
        }

        int colStep = 1;
        if (isLeft) {
            colStep = -colStep;
        }

        if (position.getRow() + rowStep == x && position.getColumn() + colStep == y && board[x][y].isBlack() != isBlack()) {
            return true;
        }
        return false;
    }

    private boolean forward(int x, int y, int step) {
        if (!isBlack()) {
            step = -step;
        }

        if (position.getRow() + step == x && position.getColumn() == y) {
            return true;
        }
        return false;
    }
}
