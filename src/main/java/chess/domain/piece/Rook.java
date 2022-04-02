package chess.domain.piece;

import chess.domain.ChessBoard;
import chess.domain.ChessBoardPosition;
import chess.domain.Team;

public class Rook extends ChessPiece {
    private static final int NO_DIFFERENCE = 0;
    private static final double SCORE = 5.0;

    public Rook(Team team) {
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
        return rowDistance == NO_DIFFERENCE || columnDistance == NO_DIFFERENCE;
    }

    private boolean isUnobstructed(ChessBoardPosition sourcePosition, ChessBoardPosition targetPosition,
                                   ChessBoard chessBoard) {
        return sourcePosition.createPathPositions(targetPosition)
                .stream()
                .noneMatch(chessBoard::existChessPieceAt);
    }

    private int calculateRowDistance(int highRow, int lowRow) {
        return highRow - lowRow;
    }

    private int calculateColumnDistance(char highColumn, char lowColumn) {
        return highColumn - lowColumn;
    }
}
