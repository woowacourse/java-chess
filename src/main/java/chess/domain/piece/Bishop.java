package chess.domain.piece;

import chess.domain.ChessBoardPosition;
import chess.domain.Team;

public class Bishop implements ChessPiece {
    private static final String UNEXPECTED_MOVEMENT_EXCEPTION = "[ERROR] 비숍이 이동할 수 없는 위치입니다.";

    private final Team team;
    private ChessBoardPosition position;

    public Bishop(Team team, ChessBoardPosition position) {
        this.team = team;
        this.position = position;
    }

    @Override
    public void move(ChessBoardPosition targetPosition) {
        int rowDistance = calculateRowDistance(position.getRow(), targetPosition.getRow());
        int columnDistance = calculateColumnDistance(position.getColumn(), targetPosition.getColumn());
        if (rowDistance != columnDistance) {
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
