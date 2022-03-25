package chess.domain.piece;

import chess.domain.ChessBoardPosition;
import chess.domain.Team;

public class Knight extends ChessPiece {
    private static final String NAME = "KNIGHT";
    private static final String UNEXPECTED_MOVEMENT_EXCEPTION = "[ERROR] 나이트가 이동할 수 없는 위치입니다.";

    public Knight(Team team, ChessBoardPosition position) {
        super(NAME, team, position);
    }

    @Override
    public void move(ChessBoardPosition targetPosition) {
        int rowDistance = calculateRowDistance(position.getRow(), targetPosition.getRow());
        int columnDistance = calculateColumnDistance(position.getColumn(), targetPosition.getColumn());
        if (!((rowDistance == 2 && columnDistance == 1) || (rowDistance == 1 && columnDistance == 2))) {
            throw new IllegalArgumentException(UNEXPECTED_MOVEMENT_EXCEPTION);
        }
        position = targetPosition;
    }

    private int calculateRowDistance(int highRow, int lowRow) {
        return Math.abs(highRow - lowRow);
    }

    private int calculateColumnDistance(char highColumn, char lowColumn) {
        return Math.abs(highColumn - lowColumn);
    }

    public boolean isSamePosition(ChessBoardPosition nextPosition) {
        return position.equals(nextPosition);
    }
}
