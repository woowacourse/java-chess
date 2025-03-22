package chess.piece;

import java.util.Map;
import chess.Color;
import chess.Column;
import chess.Position;
import chess.Row;

public class Bishop extends Piece {
    public Bishop(Color color) {
        super(color);
    }

    @Override
    public boolean isAbleToMove(Position startPosition, Position endPosition, Map<Position, Piece> board) {
        // 비숍은 대각선으로만 이동할 수 있다.
        // 룩과 똑같이 충돌 시 예외 처리를 해야 한다.
        // 도착 지점에 아군이 있을경우 이동 불가능하다.

        // 대각선상에 있는지 확인
        if (!(Row.getDifference(startPosition.row(), endPosition.row()) == Column.getDifference(startPosition.column(),
                endPosition.column()))) {
            return false;
        }

        // 충돌 검사
        int diagonalDifference = Row.getDifference(startPosition.row(),
                endPosition.row()); // 대각선 상임이 보장되어있으므로 row만 계산해서 대각선 길이를 계산할 수 있음

        Position movedPosition = startPosition;
        if (startPosition.column().toInt() < endPosition.column().toInt()) { // 오른쪽으로 가야하는 경우
            if (startPosition.row().toInt() < endPosition.row().toInt()) { // 오른쪽 위
                while (diagonalDifference > 1) {
                    movedPosition = movedPosition.moveRightUp();
                    if (board.get(movedPosition) != null) {
                        return false; // 충돌
                    }
                    diagonalDifference = Row.getDifference(movedPosition.row(), endPosition.row());
                }
            } else { // 오른쪽 아래
                while (diagonalDifference > 1) {
                    movedPosition = movedPosition.moveRightDown();
                    if (board.get(movedPosition) != null) {
                        return false; // 충돌
                    }
                    diagonalDifference = Row.getDifference(movedPosition.row(), endPosition.row());
                }
            }
        }

        if (startPosition.column().toInt() > endPosition.column().toInt()) { // 왼쪽으로 가야하는 경우
            if (startPosition.row().toInt() < endPosition.row().toInt()) { // 왼쪽 위
                while (diagonalDifference > 1) {
                    movedPosition = movedPosition.moveLeftUp();
                    if (board.get(movedPosition) != null) {
                        return false; // 충돌
                    }
                    diagonalDifference = Row.getDifference(movedPosition.row(), endPosition.row());
                }
            } else { // 왼쪽 아래
                while (diagonalDifference > 1) {
                    movedPosition = movedPosition.moveLeftDown();
                    if (board.get(movedPosition) != null) {
                        return false; // 충돌
                    }
                    diagonalDifference = Row.getDifference(movedPosition.row(), endPosition.row());
                }
            }
        }

        if (board.get(endPosition) == null) {
            return true;
        }
        if (board.get(endPosition).getColor() == super.getColor()) {
            return false;
        }
        return true;
    }
}
