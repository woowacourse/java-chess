package chess.domain.piece;

import chess.domain.ChessBoardPosition;
import chess.domain.Team;

public class King implements ChessPiece {
    private static final String UNEXPECTED_MOVEMENT_EXCEPTION = "[ERROR] 킹이 이동할 수 없는 위치입니다.";
    private final Team team;
    private ChessBoardPosition position;

    public King(Team team, ChessBoardPosition position) {
        this.team = team;
        this.position = position;
    }

    private int calculateRowDistance(int highRow, int lowRow) {
        return Math.abs(highRow - lowRow);
    }

    private int calculateColumnDistance(char highColumn, char lowColumn) {
        return Math.abs(highColumn - lowColumn);
    }

    private boolean isNotKingMovement(int rowDistance, int columnDistance) {
        return isNotFourCardinalMovement(rowDistance, columnDistance)
                && isNotFourDiagonalCardinalMovement(rowDistance, columnDistance);
    }

    private boolean isNotFourCardinalMovement(int rowDistance, int columnDistance) {
        return !((rowDistance == 1 && columnDistance == 0) || (columnDistance == 1 && rowDistance == 0));
    }

    private boolean isNotFourDiagonalCardinalMovement(int rowDistance, int columnDistance) {
        return !(rowDistance == 1 && columnDistance == 1);
    }

    public boolean isSamePosition(ChessBoardPosition nextPosition) {
        return position.equals(nextPosition);
    }

    @Override
    public void move(ChessBoardPosition targetPosition) {
        int rowDistance = calculateRowDistance(position.getRow(), targetPosition.getRow());
        int columnDistance = calculateColumnDistance(position.getColumn(), targetPosition.getColumn());
        if (isNotKingMovement(rowDistance, columnDistance)) {
            throw new IllegalArgumentException(UNEXPECTED_MOVEMENT_EXCEPTION);
        }
        position = targetPosition;
    }
}
