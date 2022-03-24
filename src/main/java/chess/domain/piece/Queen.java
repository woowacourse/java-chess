package chess.domain.piece;

import chess.domain.ChessBoardPosition;
import chess.domain.Team;

public class Queen implements ChessPiece {
    private static final String UNEXPECTED_MOVEMENT_EXCEPTION = "[ERROR] 퀸이 이동할 수 없는 위치입니다.";
    private static final int NO_DIFFERENCE = 0;

    private final Team team;
    private ChessBoardPosition position;

    public Queen(Team team, ChessBoardPosition position) {
        this.team = team;
        this.position = position;
    }

    private int calculateRowDistance(int highRow, int lowRow) {
        return Math.abs(highRow - lowRow);
    }

    private int calculateColumnDistance(char highColumn, char lowColumn) {
        return Math.abs(highColumn - lowColumn);
    }

    private boolean isNotEightCardinalMovement(int rowDistance, int columnDistance) {
        return isNotFourCardinalMovement(rowDistance, columnDistance)
                && isNotFourDiagonalCardinalMovement(rowDistance, columnDistance);
    }

    private boolean isNotFourCardinalMovement(int rowDistance, int columnDistance) {
        return rowDistance != NO_DIFFERENCE && columnDistance != NO_DIFFERENCE;
    }

    private boolean isNotFourDiagonalCardinalMovement(int rowDistance, int columnDistance) {
        return rowDistance != columnDistance;
    }

    public boolean isSamePosition(ChessBoardPosition nextPosition) {
        return position.equals(nextPosition);
    }

    @Override
    public void move(ChessBoardPosition targetPosition) {
        int rowDistance = calculateRowDistance(position.getRow(), targetPosition.getRow());
        int columnDistance = calculateColumnDistance(position.getColumn(), targetPosition.getColumn());
        if (isNotEightCardinalMovement(rowDistance, columnDistance)) {
            throw new IllegalArgumentException(UNEXPECTED_MOVEMENT_EXCEPTION);
        }
        position = targetPosition;
    }
}
