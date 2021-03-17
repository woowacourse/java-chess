package domain.chess.piece;

import java.util.Arrays;
import java.util.List;

public class Pawn extends Piece {
    private static final double SCORE = 3;
    public static final List<Pawn> PAWNS = Arrays.asList(Pawn.Of("P", Position.Of(1, 0), true),
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

    private Pawn(String name, int x, int y, boolean isBlack) {
        super(name, SCORE, Position.Of(x, y), isBlack);
    }

    public static Pawn Of(String name, Position position, boolean color) {
        return new Pawn(name, position.getRow(), position.getColumn(), color);
    }

    public static List<Pawn> initialPawnPieces() {
        return PAWNS;
    }

    @Override
    public boolean canMove(Piece[][] board, Position endPosition) {
        if (canForward(endPosition, 1)) return true;
        if (canForward(endPosition, 2)) return true;

        if (catchDiagonal(board, endPosition, true)) return true;
        if (catchDiagonal(board, endPosition, false)) return true;

        return false;
    }

    private boolean catchDiagonal(Piece[][] board, Position endPosition, boolean isLeft) {
        int rowStep = 1;
        if (!isBlack()) {
            rowStep = -rowStep;
        }

        int colStep = 1;
        if (isLeft) {
            colStep = -colStep;
        }

        if (isEqualsPosition(endPosition, rowStep, colStep) && board[endPosition.getRow()][endPosition.getColumn()].isBlack() != isBlack()) {
            return true;
        }
        return false;
    }

    private boolean canForward(Position endPosition, int step) {
        if (step == 2 && !PAWNS.contains(this)) {
            return false;
        }

        if (!isBlack()) {
            step = -step;
        }

        if (isEqualsPosition(endPosition, step, 0)) {
            return true;
        }
        return false;
    }

    private boolean isEqualsPosition(Position endPosition, int rowStep, int colStep) {
        return position.getRow() + rowStep == endPosition.getRow() && position.getColumn() + colStep == endPosition.getColumn();
    }
}
