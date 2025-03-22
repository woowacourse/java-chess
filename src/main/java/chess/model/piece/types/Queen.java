package chess.model.piece.types;

import chess.model.ChessBoard;
import chess.model.element.Color;
import chess.model.piece.Piece;
import chess.model.position.Column;
import chess.model.position.Position;
import chess.model.position.Row;

public class Queen extends Piece {

    public Queen(Color color) {
        super(color);
    }

    @Override
    protected boolean isNotWithInDirection(Position src, Position dest) {
        return true;
    }

    @Override
    protected boolean isWithInRangeByMovement(double distance) {
        return true;
    }

    @Override
    protected boolean passFilter(Position src, Position dest, ChessBoard board) {
        /*
        8방향 중 대각선, 동서남북 분리하여 작성
        */

        int[] srcNumber = findNumberByPosition(src);
        int[] destNumber = findNumberByPosition(dest);

        if (isHorizontal(src, dest)) { // 수평 column 다름
            int rowN = srcNumber[0];
            for (int cNum = Math.min(srcNumber[1], destNumber[1]); cNum < Math.max(srcNumber[1], destNumber[1]); cNum++) {
                Row row = Row.findByNumber(rowN);
                Column column = Column.findByNumber(cNum);
                if (board.isExistPosition(new Position(row, column))) {
                    return false;
                }
            }
            return true;
        }

        if (isVertical(src, dest)) { // 수직 row 다름
            int colN = srcNumber[1];
            for (int sNum = Math.min(srcNumber[0], destNumber[0]); sNum < Math.max(srcNumber[0], destNumber[0]); sNum++) {
                Row row = Row.findByNumber(sNum);
                Column column = Column.findByNumber(colN);
                if (board.isExistPosition(new Position(row, column))) {
                    return false;
                }
            }
            return true;
        }

        // 4방향 중 어느 방향인지 파악할 것

        int DX = srcNumber[0] - destNumber[0] < 0 ? +1 : -1;
        int DY = srcNumber[1] - destNumber[1] < 0 ? +1 : -1;

        // dest까지 어떠한 장애물도 없어야 함
        for (int loop = srcNumber[0], x = srcNumber[0], y = srcNumber[1]; loop < destNumber[0]; loop++, x = x + DX, y = y + DY) {
            Row row = Row.findByNumber(x);
            Column column = Column.findByNumber(y);
            if (board.isExistPosition(new Position(row, column))) {
                return false;
            }
        }
        return true;
    }
}
