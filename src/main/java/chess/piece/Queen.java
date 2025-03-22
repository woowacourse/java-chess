package chess.piece;

import java.util.Map;
import chess.Color;
import chess.Column;
import chess.Position;
import chess.Row;

public class Queen extends Piece {

    public Queen(Color color) {
        super(color);
    }

    @Override
    public boolean isAbleToMove(Position startPosition, Position endPosition, Map<Position, Piece> board) {
        // 퀸은 대각선, 직선으로 이동 가능하다
        // 이동 중 충돌하면 안됨
        // 목적지에 아군 있으면 안됨

        // 대각선도 아니고, 직선도 아니라면
        if (!Position.isSameLine(startPosition, endPosition) && !(Row.getDifference(startPosition.row(), endPosition.row()) == Column.getDifference(startPosition.column(),
                endPosition.column()))) {
            return false; // 너 못가
        }

        // 직선일 경우의 처리
        int rowDifference = Row.getDifference(startPosition.row(), endPosition.row());
        int columnDifference = Column.getDifference(startPosition.column(), endPosition.column());

        if (Position.isSameLine(startPosition, endPosition)) {
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
        }


        // 대각선일 경우의 처리
        if ((Row.getDifference(startPosition.row(), endPosition.row()) == Column.getDifference(startPosition.column(),
                endPosition.column()))) {
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
        }

        // 충돌 검사 끝
        // 목적지에 아군이 있는지 확인
        if (board.get(endPosition) == null) {
            return true; // 아무도 없으므로 이동 가능
        }
        if (board.get(endPosition).getColor() == super.getColor()) {
            return false;
        }
        return true;
    }
}
