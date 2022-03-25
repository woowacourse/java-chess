package chess.domain.piece;

import chess.domain.ChessBoardPosition;
import chess.domain.ChessMen;
import chess.domain.Team;

public class Bishop extends ChessPiece {
    private static final String NAME = "BISHOP";
    private static final String UNEXPECTED_MOVEMENT_EXCEPTION = "[ERROR] 비숍이 이동할 수 없는 위치입니다.";

    public Bishop(Team team, ChessBoardPosition position) {
        super(NAME, team, position);
    }

    public boolean isSamePosition(ChessBoardPosition nextPosition) {
        return position.equals(nextPosition);
    }

    @Override
    public void move(ChessBoardPosition targetPosition) {
        position = targetPosition;
    }

    @Override
    public boolean isMovable(ChessBoardPosition targetPosition, ChessMen chessMen) {
        return isReachable(targetPosition) && isUnobstructed(targetPosition, chessMen);
    }

    private boolean isReachable(ChessBoardPosition targetPosition) {
        int rowDistance = calculateRowDistance(position.getRow(), targetPosition.getRow());
        int columnDistance = calculateColumnDistance(position.getColumn(), targetPosition.getColumn());
        return rowDistance == columnDistance;
    }

    private boolean isUnobstructed(ChessBoardPosition targetPosition, ChessMen chessMen) {
        return position.getPaths(targetPosition)
                .stream()
                .noneMatch(chessMen::existChessPieceAt);
    }

    private int calculateRowDistance(int highRow, int lowRow) {
        return Math.abs(highRow - lowRow);
    }

    private int calculateColumnDistance(char highColumn, char lowColumn) {
        return Math.abs(highColumn - lowColumn);
    }
}
