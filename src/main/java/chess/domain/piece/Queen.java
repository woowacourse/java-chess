package chess.domain.piece;

import chess.domain.ChessBoard;
import chess.domain.ChessBoardPosition;
import chess.domain.Team;
import java.util.ArrayList;
import java.util.List;

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
        return createPathPositions(sourcePosition, targetPosition)
                .stream()
                .noneMatch(chessBoard::existChessPieceAt);
    }

    private List<ChessBoardPosition> createPathPositions(ChessBoardPosition sourcePosition,
                                                         ChessBoardPosition targetPosition) {
        int rowUnitChange = calculateUnitChange(targetPosition.getRow(), sourcePosition.getRow());
        int columnUnitChange = calculateUnitChange(targetPosition.getColumn(), sourcePosition.getColumn());
        List<ChessBoardPosition> pathPositions = new ArrayList<>();
        ChessBoardPosition currentBoardPosition = sourcePosition.move(columnUnitChange, rowUnitChange);
        while (!currentBoardPosition.equals(targetPosition)) {
            pathPositions.add(currentBoardPosition);
            currentBoardPosition = currentBoardPosition.move(columnUnitChange, rowUnitChange);
        }
        return pathPositions;
    }

    private int calculateUnitChange(int source, int target) {
        if (source == target) {
            return 0;
        }
        return (source - target) / Math.abs(source - target);
    }
}
