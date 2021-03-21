package domain.piece;

import domain.Direction;
import domain.Score;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Rook extends Piece {
    private static final Score SCORE = new Score(5);

    private Rook(String name, boolean isBlack) {
        super(name, SCORE, isBlack);
    }

    public static Rook of(String name, boolean color) {
        return new Rook(name, color);
    }

    public static Map<Position, Rook> initialRookPieces() {
        return new HashMap<Position, Rook>() {{
            put(Position.of("a8"), Rook.of("R", true));
            put(Position.of("h8"), Rook.of("R", true));
            put(Position.of("a1"), Rook.of("r", false));
            put(Position.of("h1"), Rook.of("r", false));
        }};
    }

    @Override
    public boolean canMove2(Map<Position, Piece> board, Position start, Position end) {
        if (!isLinear(start, end)) {
            return false;
        }
        Direction direction = Direction.findLinearDirection(start, end);
        do {
            start = start.move(direction);
        } while (!start.equals(end) && isEmptyPosition(board, start));

        return start.equals(end) && (isEmptyPosition(board, end) || !this.isSameColor(board.get(end)));
    }

//    @Override
//    public boolean canMove(Piece[][] board, Position endPosition) {
//        if (board[endPosition.getRow()][endPosition.getColumn()] != null && isOurTeam(board, endPosition)) return false;
//
//        if (checkPositionRange(endPosition)) {
//            return false;
//        }
//
//        int dx[] = {-1, 1, 0, 0}; // 상 하 좌 우
//        int dy[] = {0, 0, -1, 1};
//
//        int index = findDirection(endPosition);
//        int nextRow = position.getRow() + dx[index];
//        int nextColumn = position.getColumn() + dy[index];
//
//        while (!(nextRow == endPosition.getRow() && nextColumn == endPosition.getColumn())
//                && board[nextRow][nextColumn] == null) {
//            nextRow += dx[index];
//            nextColumn += dy[index];
//        }
//
//        return Position.valueOf(nextRow, nextColumn).equals(endPosition);

//    }
//

//
//    private boolean checkPositionRange(Position endPosition) {
//        return position.getRow() != endPosition.getRow()
//                && position.getColumn() != endPosition.getColumn();
//    }
}
