package chess.domain.piece;

import chess.domain.ChessBoardPosition;
import chess.domain.ChessMen;
import chess.domain.Team;
import java.util.ArrayList;
import java.util.List;

public class Queen extends ChessPiece {
    private static final int NO_DIFFERENCE = 0;
    private static final String NAME = "QUEEN";
    private static final String UNEXPECTED_MOVEMENT_EXCEPTION = "[ERROR] 퀸이 이동할 수 없는 위치입니다.";

    private static final double SCORE = 9.0;

    public Queen(Team team, ChessBoardPosition position) {
        super(NAME, SCORE, team, position);
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

    public boolean isSamePosition(ChessBoardPosition nextPosition) {
        return position.equals(nextPosition);
    }

    @Override
    public void move(ChessBoardPosition targetPosition) {
        int rowDistance = calculateRowDistance(position.getRow(), targetPosition.getRow());
        int columnDistance = calculateColumnDistance(position.getColumn(), targetPosition.getColumn());
        if (isNotEightCardinalMovement(rowDistance, columnDistance)) {
            throw new IllegalArgumentException(UNEXPECTED_MOVEMENT_EXCEPTION);
        }
        position = targetPosition;
    }

    @Override
    public boolean isMovable(ChessBoardPosition targetPosition, ChessMen whiteChessMen, ChessMen blackChessMen) {
        return isReachable(targetPosition) && isUnobstructed(targetPosition, whiteChessMen, blackChessMen);
    }

    private boolean isReachable(ChessBoardPosition targetPosition) {
        int rowDistance = calculateRowDistance(position.getRow(), targetPosition.getRow());
        int columnDistance = calculateColumnDistance(position.getColumn(), targetPosition.getColumn());
        return !isNotEightCardinalMovement(rowDistance, columnDistance);
    }

    private boolean isUnobstructed(ChessBoardPosition targetChessBoardPosition, ChessMen whiteChessMen,
                                   ChessMen blackChessMen) {
        return noChessMenInPath(targetChessBoardPosition, whiteChessMen)
                && noChessMenInPath(targetChessBoardPosition, blackChessMen);
    }

    private boolean noChessMenInPath(ChessBoardPosition targetChessBoardPosition, ChessMen chessMen) {
        return createPathPositions(targetChessBoardPosition)
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
}
