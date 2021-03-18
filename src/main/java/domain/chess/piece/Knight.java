package domain.chess.piece;

import java.util.Arrays;
import java.util.List;

public class Knight extends Piece {
    private static final double SCORE = 2.5;

    private Knight(String name, int x, int y, boolean isBlack) {
        super(name, SCORE, Position.Of(x, y), isBlack);
    }

    public static Knight Of(String name, Position position, boolean color) {
        return new Knight(name, position.getRow(), position.getColumn(), color);
    }

    public static List<Knight> initialKnightPieces() {
        return Arrays.asList(Knight.Of("N", Position.Of(0, 1), true),
                Knight.Of("N", Position.Of(0, 6), true),
                Knight.Of("n", Position.Of(7, 1), false),
                Knight.Of("n", Position.Of(7, 6), false));
    }

    @Override
    public Knight movePosition(Position position) {
        return new Knight(getName(), position.getRow(), position.getColumn(), isBlack());
    }

    @Override
    public boolean canMove(Piece[][] board, Position end) {
        List<Position> movePositions = Arrays.asList(
                Position.Of(-2, -1),
                Position.Of(-1, -2),
                Position.Of(1, -2),
                Position.Of(2, -1),
                Position.Of(2, 1),
                Position.Of(1, 2),
                Position.Of(-2, 1),
                Position.Of(-1, 2));

        int x = position.getRow();
        int y = position.getColumn();

        return movePositions.stream()
                .filter(movePosition ->
                        x + movePosition.getRow() == end.getRow() && y + movePosition.getColumn() == end.getColumn())
                .findAny()
                .isPresent();
    }
}
