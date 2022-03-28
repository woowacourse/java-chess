package chess.domain.piece;

import chess.domain.ChessBoardPosition;
import chess.domain.ChessMen;
import chess.domain.Team;
import java.util.ArrayList;
import java.util.List;

public class Bishop extends ChessPiece {
    private static final String NAME = "BISHOP";
    private static final double SCORE = 3.0;

    public Bishop(Team team, ChessBoardPosition position) {
        super(NAME, SCORE, team, position);
    }

    @Override
    public boolean isMovable(ChessBoardPosition targetPosition, ChessMen whiteChessMen, ChessMen blackChessMen) {
        return isReachable(targetPosition) && isUnobstructed(targetPosition, whiteChessMen, blackChessMen);
    }

    private boolean isReachable(ChessBoardPosition targetPosition) {
        int rowDistance = calculateRowDistance(position.getRow(), targetPosition.getRow());
        int columnDistance = calculateColumnDistance(position.getColumn(), targetPosition.getColumn());
        return rowDistance == columnDistance;
    }

    private boolean isUnobstructed(ChessBoardPosition targetPosition, ChessMen whiteChessMen, ChessMen blackChessMen) {
        return noChessPieceInPath(targetPosition, whiteChessMen) && noChessPieceInPath(targetPosition, blackChessMen);
    }

    private boolean noChessPieceInPath(ChessBoardPosition targetPosition, ChessMen chessMen) {
        return createPathPositions(targetPosition)
                .stream()
                .noneMatch(chessMen::existChessPieceAt);
    }

    private List<ChessBoardPosition> createPathPositions(ChessBoardPosition targetChessBoardPosition) {
        int rowUnitChange = calculateUnitChange(targetChessBoardPosition.getRow(), position.getRow());
        int columnUnitChange = calculateUnitChange(targetChessBoardPosition.getColumn(), position.getColumn());
        List<ChessBoardPosition> pathPositions = new ArrayList<>();
        ChessBoardPosition currentBoardPosition = position.move(columnUnitChange, rowUnitChange);
        while (!currentBoardPosition.equals(targetChessBoardPosition)) {
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
