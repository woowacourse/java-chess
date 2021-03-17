package domain.chess.piece;

import java.util.Arrays;
import java.util.List;

public class Bishop extends Piece {
    private static final double SCORE = 3;

    private Bishop(String name, int x, int y, boolean isBlack) {
        super(name, SCORE, Position.Of(x, y), isBlack);
    }

    public static Bishop Of(String name, Position position, boolean color) {
        return new Bishop(name, position.getRow(), position.getColumn(), color);
    }

    public static List<Bishop> initialBishopPieces() {
        return Arrays.asList(Bishop.Of("B", Position.Of(0, 2), true),
                Bishop.Of("B", Position.Of(0, 5), true),
                Bishop.Of("b", Position.Of(7, 2), false),
                Bishop.Of("b", Position.Of(7, 5), false)
        );
    }

    @Override
    public boolean canMove(Piece[][] board, Position endPosition) {
        int dx[] = {-1, 1, -1, 1};
        int dy[] = {1, 1, -1, -1};

        if (!checkDiagonal(endPosition)) {
            return false;
        }

        int index = findDirection(endPosition);
        int nextRow = position.getRow() + dx[index];
        int nextColumn = position.getColumn() + dy[index];

        while (!(nextRow == endPosition.getRow() && nextColumn == endPosition.getColumn())
                && board[nextRow][nextColumn] == null) {
            nextRow += dx[index];
            nextColumn += dy[index];
        }

        return Position.Of(nextRow, nextColumn).equals(endPosition);
    }

    private int findDirection(Position end) {
        int row = end.getRow() - position.getRow();
        int col = end.getColumn() - position.getColumn();

        if (row > 0 && col > 0) {
            return 1;
        }

        if (row > 0 && col < 0) {
            return 3;
        }

        if (row < 0 && col < 0) {
            return 2;
        }
        return 0;
    }

    public boolean checkDiagonal(Position endPosition) {
        int rowDiff = Math.abs(position.getRow() - endPosition.getRow());
        int colDiff = Math.abs(position.getColumn() - endPosition.getColumn());

        return (rowDiff != 0 && colDiff != 0) && rowDiff == colDiff;
    }
}
