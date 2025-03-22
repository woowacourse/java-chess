package chess.model.piece.types;

import chess.model.ChessBoard;
import chess.model.element.Color;
import chess.model.piece.Piece;
import chess.model.position.Column;
import chess.model.position.Position;
import chess.model.position.Row;

public class Rook extends Piece {

    public Rook(Color color) {
        super(color);
    }

    @Override
    protected boolean isNotWithInDirection(Position src, Position dest) {
        if (!isHorizontal(src, dest)) {
            return false;
        }
        if (!isVertical(src, dest)) {
            return false;
        }
        return true;
    }

    @Override
    protected boolean isWithInRangeByMovement(double distance) {
        return true;
    }

    @Override
    protected boolean passFilter(Position src, Position dest, ChessBoard board) {
        // 중간에 장애물이 없어야 함

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
}
