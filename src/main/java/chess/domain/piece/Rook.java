package chess.domain.piece;

import chess.domain.ChessBoardPosition;
import chess.domain.Team;

public class Rook extends ChessPiece {
    private static final int NO_DIFFERENCE = 0;
    private static final String NAME = "ROOK";
    private static final String UNEXPECTED_MOVEMENT_EXCEPTION = "[ERROR] 룩이 이동할 수 없는 위치입니다.";

    public Rook(Team team, ChessBoardPosition position) {
        super(NAME, team, position);

    }

    private int calculateRowDistance(int highRow, int lowRow) {
        return highRow - lowRow;
    }

    private int calculateColumnDistance(char highColumn, char lowColumn) {
        return highColumn - lowColumn;
    }

    public boolean isSamePosition(ChessBoardPosition nextPosition) {
        return position.equals(nextPosition);
    }

    @Override
    public void move(ChessBoardPosition targetPosition) {
        int rowDistance = calculateRowDistance(position.getRow(), targetPosition.getRow());
        int columnDistance = calculateColumnDistance(position.getColumn(), targetPosition.getColumn());
        if (rowDistance != NO_DIFFERENCE && columnDistance != NO_DIFFERENCE) {
            throw new IllegalArgumentException(UNEXPECTED_MOVEMENT_EXCEPTION);
        }
        position = targetPosition;

    }
}
