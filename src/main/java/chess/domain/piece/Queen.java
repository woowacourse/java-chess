package chess.domain.piece;

import chess.domain.ChessBoard;
import chess.domain.ChessBoardPosition;
import chess.domain.Team;

public class Queen extends ChessPiece {
    private static final int NO_DIFFERENCE = 0;
    private static final double SCORE = 9.0;

    public Queen(Team team) {
        super(SCORE, team);
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

    @Override
    public boolean isMovable(ChessBoardPosition sourcePosition, ChessBoardPosition targetPosition,
                             ChessBoard chessBoard) {
        return isReachable(sourcePosition, targetPosition) && isUnobstructed(sourcePosition, targetPosition,
                chessBoard);
    }

    private boolean isReachable(ChessBoardPosition sourcePosition, ChessBoardPosition targetPosition) {
        int rowDistance = calculateRowDistance(sourcePosition.getRow(), targetPosition.getRow());
        int columnDistance = calculateColumnDistance(sourcePosition.getColumn(), targetPosition.getColumn());
        return !isNotEightCardinalMovement(rowDistance, columnDistance);
    }

    private boolean isUnobstructed(ChessBoardPosition sourcePosition, ChessBoardPosition targetPosition,
                                   ChessBoard chessBoard) {
        return sourcePosition.createPathPositions(targetPosition)
                .stream()
                .noneMatch(chessBoard::existChessPieceAt);
    }
}
