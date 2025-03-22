package chess.model.piece.types;

import chess.model.ChessBoard;
import chess.model.element.Color;
import chess.model.piece.Piece;
import chess.model.position.Column;
import chess.model.position.Position;
import chess.model.position.Row;

public class Bishop extends Piece {

    public Bishop(Color color) {
        super(color);
    }

    @Override
    protected boolean isNotWithInDirection(Position src, Position dest) {
        // x들 차의 절대값과 y들 차의 절대값이 동일하면 4대각선 내에 존재
        int[] srcNumber = findNumberByPosition(src);
        int[] destNumber = findNumberByPosition(dest);
        int absX = calculateAbs(srcNumber[0], destNumber[0]);
        int absY = calculateAbs(srcNumber[1], destNumber[1]);
        return absX == absY;
    }

    @Override
    protected boolean isWithInRangeByMovement(double distance) {
        return true;
    }


    @Override
    protected boolean passFilter(Position src, Position dest, ChessBoard board) {
        // 4방향 중 어느 방향인지 파악할 것
        int[] srcNumber = findNumberByPosition(src);
        int[] destNumber = findNumberByPosition(dest);
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
