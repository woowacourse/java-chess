package chess.domain.piece;

import chess.domain.ChessBoard;
import chess.domain.ChessBoardPosition;
import chess.domain.Team;

public class King extends ChessPiece {
    private static final double SCORE = 0;

    public King(Team team) {
        super(SCORE, team);
    }

    private int calculateRowDistance(int highRow, int lowRow) {
        return Math.abs(highRow - lowRow);
    }

    private int calculateColumnDistance(char highColumn, char lowColumn) {
        return Math.abs(highColumn - lowColumn);
    }

    private boolean isKingMovement(int rowDistance, int columnDistance) {
        return isFourCardinalMovement(rowDistance, columnDistance)
                || isFourDiagonalCardinalMovement(rowDistance, columnDistance);
    }

    private boolean isFourCardinalMovement(int rowDistance, int columnDistance) {
        return (rowDistance == 1 && columnDistance == 0) || (columnDistance == 1 && rowDistance == 0);
    }

    private boolean isFourDiagonalCardinalMovement(int rowDistance, int columnDistance) {
        return rowDistance == 1 && columnDistance == 1;
    }

    @Override
    public boolean isMovable(ChessBoardPosition sourcePosition, ChessBoardPosition targetPosition,
                             ChessBoard chessBoard) {
        int rowDistance = calculateRowDistance(sourcePosition.getRow(), targetPosition.getRow());
        int columnDistance = calculateColumnDistance(sourcePosition.getColumn(), targetPosition.getColumn());
        return isKingMovement(rowDistance, columnDistance);
    }
}
