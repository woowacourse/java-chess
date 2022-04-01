package chess.domain.piece;

import chess.domain.ChessBoard;
import chess.domain.ChessBoardPosition;
import chess.domain.Team;
import java.util.ArrayList;
import java.util.List;

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
        return createPathPositions(sourcePosition, targetPosition)
                .stream()
                .noneMatch(board::existChessPieceAt);
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

    private int calculateRowDistance(int highRow, int lowRow) {
        return Math.abs(highRow - lowRow);
    }

    private int calculateColumnDistance(char highColumn, char lowColumn) {
        return Math.abs(highColumn - lowColumn);
    }
}
