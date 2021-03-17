package domain.chess.piece;

import java.util.Arrays;
import java.util.List;

public class King extends Piece {
    private static final double SCORE = 0;

    private King(String name, int x, int y, boolean isBlack) {
        super(name, SCORE, Position.Of(x, y), isBlack);
    }

    public static King Of(String name, Position position, boolean color) {
        return new King(name, position.getRow(), position.getColumn(), color);
    }

    public static List<King> initialKingPieces() {
        return Arrays.asList(King.Of("K", Position.Of(0, 4), true),
                King.Of("k", Position.Of(7, 4), false));
    }

    @Override
    public boolean canMove(Piece[][] board, Position end) {
        List<Position> movePositions = Arrays.asList(
                Position.Of(-1, 0),
                Position.Of(1, 0),
                Position.Of(0, -1),
                Position.Of(0, 1),
                Position.Of(-1, 1),
                Position.Of(1, 1),
                Position.Of(-1, -1),
                Position.Of(1, -1)
        );

        int x = position.getRow();
        int y = position.getColumn();
        return movePositions.stream()
                .filter(movePosition ->
                        x + movePosition.getRow() == end.getRow() && y + movePosition.getColumn() == end.getColumn())
                .findAny()
                .isPresent();
    }
}
