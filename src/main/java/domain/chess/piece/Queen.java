package domain.chess.piece;

import domain.chess.Score;

import java.util.Arrays;
import java.util.List;

public class Queen extends Piece {
    private static final Score SCORE = new Score(9);

    private Queen(String name, int x, int y, boolean isBlack) {
        super(name, SCORE, Position.Of(x, y), isBlack);
    }

    public static Queen Of(String name, Position position, boolean color) {
        return new Queen(name, position.getRow(), position.getColumn(), color);
    }

    public static List<Queen> initialQueenPieces() {
        return Arrays.asList(Queen.Of("Q", Position.Of(0, 3), true),
                Queen.Of("q", Position.Of(7, 3), false));
    }

    @Override
    public boolean canMove(Piece[][] board, Position endPosition) {
        if (board[endPosition.getRow()][endPosition.getColumn()] != null && isOurTeam(board, endPosition)) return false;

        boolean result = canMoveToDiagonal(board, endPosition);
        if (!result) {
            result = canMoveToStraight(board, endPosition);
        }

        return result;
    }

    @Override
    public Queen movePosition(Position end) {
        return new Queen(getName(), position.getRow(), position.getColumn(), isBlack());
    }

    private boolean canMoveToStraight(Piece[][] board, Position endPosition) {
        if (checkPositionRange(endPosition)) {
            return false;
        }

        int dx[] = {-1, 1, 0, 0};
        int dy[] = {0, 0, -1, 1};

        int index = findStraightDirection(endPosition);
        int nextRow = position.getRow() + dx[index];
        int nextColumn = position.getColumn() + dy[index];

        while (!(nextRow == endPosition.getRow() && nextColumn == endPosition.getColumn())
                && board[nextRow][nextColumn] == null) {
            nextRow += dx[index];
            nextColumn += dy[index];
        }

        return Position.Of(nextRow, nextColumn).equals(endPosition);
    }

    private int findStraightDirection(Position end) {
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

    private boolean canMoveToDiagonal(Piece[][] board, Position endPosition) {
        int dx[] = {-1, 1, -1, 1};
        int dy[] = {1, 1, -1, -1};

        if (!checkDiagonal(endPosition)) {
            return false;
        }

        int index = findDiagonalDirection(endPosition);
        int nextRow = position.getRow() + dx[index];
        int nextColumn = position.getColumn() + dy[index];

        while (!(nextRow == endPosition.getRow() && nextColumn == endPosition.getColumn())
                && board[nextRow][nextColumn] == null) {
            nextRow += dx[index];
            nextColumn += dy[index];
        }

        return Position.Of(nextRow, nextColumn).equals(endPosition);
    }

    private int findDiagonalDirection(Position end) {
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
