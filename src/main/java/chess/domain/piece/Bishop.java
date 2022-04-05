package chess.domain.piece;

import chess.domain.ChessBoard;
import chess.domain.ChessBoardPosition;
import chess.domain.Team;

public class Bishop extends ChessPiece {
    private static final double SCORE = 3.0;

    public Bishop(Team team) {
        super(SCORE, team);
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
        return rowDistance == columnDistance;
    }

    private boolean isUnobstructed(ChessBoardPosition sourcePosition, ChessBoardPosition targetPosition,
                                   ChessBoard board) {
        return sourcePosition.createPathPositions(targetPosition)
                .stream()
                .noneMatch(board::existChessPieceAt);
    }

    private int calculateRowDistance(int highRow, int lowRow) {
        return Math.abs(highRow - lowRow);
    }

    private int calculateColumnDistance(char highColumn, char lowColumn) {
        return Math.abs(highColumn - lowColumn);
    }
}
