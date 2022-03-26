package chess.domain.piece;

import chess.domain.ChessBoardPosition;
import chess.domain.ChessMen;
import chess.domain.Team;
import java.util.ArrayList;
import java.util.List;

public class Rook extends ChessPiece {
    private static final int NO_DIFFERENCE = 0;
    private static final String NAME = "ROOK";
    private static final String UNEXPECTED_MOVEMENT_EXCEPTION = "[ERROR] 룩이 이동할 수 없는 위치입니다.";

    public Rook(Team team, ChessBoardPosition position) {
        super(NAME, team, position);
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
        return isReachable(targetPosition) && isUnobstructed(targetPosition, whiteChessMen, blackChessMen);
    }

    private boolean isReachable(ChessBoardPosition targetPosition) {
        int rowDistance = calculateRowDistance(position.getRow(), targetPosition.getRow());
        int columnDistance = calculateColumnDistance(position.getColumn(), targetPosition.getColumn());
        return rowDistance == NO_DIFFERENCE || columnDistance == NO_DIFFERENCE;
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

    private int calculateRowDistance(int highRow, int lowRow) {
        return highRow - lowRow;
    }

    private int calculateColumnDistance(char highColumn, char lowColumn) {
        return highColumn - lowColumn;
    }
}
