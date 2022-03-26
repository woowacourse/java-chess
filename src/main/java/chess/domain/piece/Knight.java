package chess.domain.piece;

import chess.domain.ChessBoardPosition;
import chess.domain.ChessMen;
import chess.domain.Team;

public class Knight extends ChessPiece {
    private static final String NAME = "KNIGHT";
    private static final double SCORE = 2.5;

    public Knight(Team team, ChessBoardPosition position) {
        super(NAME, SCORE, team, position);
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

    @Override
    public void move(ChessBoardPosition targetPosition) {
        position = targetPosition;
    }

    @Override
    public boolean isMovable(ChessBoardPosition targetPosition, ChessMen whiteChessMen, ChessMen blackChessMen) {
        int rowDistance = calculateRowDistance(position.getRow(), targetPosition.getRow());
        int columnDistance = calculateColumnDistance(position.getColumn(), targetPosition.getColumn());
        return (rowDistance == 2 && columnDistance == 1) || (rowDistance == 1 && columnDistance == 2);
    }
}
