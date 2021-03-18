package domain.chess.piece;

import java.util.Arrays;
import java.util.List;

public class Rook extends Piece {
    private static final double SCORE = 5;

    private Rook(String name, int x, int y, boolean isBlack) {
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

    @Override
    public boolean canMove(Piece[][] board, Position endPosition) {
        if (board[endPosition.getRow()][endPosition.getColumn()] != null && isOurTeam(board, endPosition)) return false;

        if (checkPositionRange(endPosition)) {
            return false;
        }

        int dx[] = {-1, 1, 0, 0}; // 상 하 좌 우
        int dy[] = {0, 0, -1, 1};

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

    @Override
    public Rook movePosition(Position end) {
        return new Rook(getName(), position.getRow(), position.getColumn(), isBlack());
    }

    private int findDirection(Position end) {
        int rowDiff = end.getRow() - position.getRow();
        int colDiff = end.getColumn() - position.getColumn();

        if (rowDiff < 0) {
            return 0;
        }

        if (rowDiff > 0) {
            return 1;
        }

        if (colDiff < 0) {
            return 2;
        }

        return 3;
    }

    private boolean checkPositionRange(Position endPosition) {
        return position.getRow() != endPosition.getRow()
                && position.getColumn() != endPosition.getColumn();
    }
}
