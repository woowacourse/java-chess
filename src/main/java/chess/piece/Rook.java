package chess.piece;

import java.awt.Choice;
import java.util.Map;
import chess.Color;
import chess.Column;
import chess.Position;
import chess.Row;

public class Rook extends Piece {

    public Rook(Color color) {
        super(color);
    }

    @Override
    public boolean isAbleToMove(Position startPosition, Position endPosition, Map<Position, Piece> board) {
        // 흑 백팀 상관 없이, startPosition과 endPosition이 같은 라인에 있는지 확인하면 됨.
        // 그리고. startPosition과 endPosition 경로 사이에, 어떠한 기물이 없는지 확인해야 됨.
        // 만약 도착지에 아군이 있을 경우 이동 불가능, 적군이 있을경우 이동 가능.

        if (!Position.isSameLine(startPosition, endPosition)) {
            return false;
        }

        int rowDifference = Row.getDifference(startPosition.row(), endPosition.row());
        int columnDifference = Column.getDifference(startPosition.column(), endPosition.column());

        // 기물 충돌 검사
        Position movedPosition = startPosition;
        while (rowDifference > 1) {
            if (startPosition.row().toInt() < endPosition.row().toInt()) {
                movedPosition = movedPosition.moveUp();
                if (board.get(movedPosition) != null) {
                    return false;
                }
                rowDifference = Row.getDifference(movedPosition.row(), endPosition.row());
            } else {
                movedPosition = movedPosition.moveDown();
                if (board.get(movedPosition) != null) {
                    return false;
                }
                rowDifference = Row.getDifference(movedPosition.row(), endPosition.row());
            }
        }
        while (columnDifference > 1) {
            if (startPosition.column().toInt() < endPosition.column().toInt()) {
                movedPosition = movedPosition.moveRight();
                if (board.get(movedPosition) != null) {
                    return false;
                }
                columnDifference = Column.getDifference(movedPosition.column(), endPosition.column());
            } else {
                movedPosition = movedPosition.moveLeft();
                if (board.get(movedPosition) != null) {
                    return false;
                }
                columnDifference = Column.getDifference(movedPosition.column(), endPosition.column());
            }
        }

        // 도착지점 적팀 / 아군 검사
        if (board.get(endPosition) == null) {
            return true;
        }
        if (board.get(endPosition).getColor() == super.getColor()) {
            return false;
        }
        return true;
    }
}
