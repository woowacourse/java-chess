package chess.domain.piece;

import chess.domain.ChessBoardPosition;
import chess.domain.ChessMen;
import chess.domain.Team;
import java.util.ArrayList;
import java.util.List;

public class Bishop extends ChessPiece {
    private static final String NAME = "BISHOP";

    public Bishop(Team team, ChessBoardPosition position) {
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
    public boolean isMovable(ChessBoardPosition targetPosition, ChessMen chessMen) {
        return isReachable(targetPosition) && isUnobstructed(targetPosition, chessMen);
    }

    private boolean isReachable(ChessBoardPosition targetPosition) {
        int rowDistance = calculateRowDistance(position.getRow(), targetPosition.getRow());
        int columnDistance = calculateColumnDistance(position.getColumn(), targetPosition.getColumn());
        return rowDistance == columnDistance;
    }

    private boolean isUnobstructed(ChessBoardPosition targetPosition, ChessMen chessMen) {
        return createPathPositions(targetPosition)
                .stream()
                .noneMatch(chessMen::existChessPieceAt);
    }

    public List<ChessBoardPosition> createPathPositions(ChessBoardPosition other) {
        List<Integer> rows = position.ascendingRowRange(other);
        List<Integer> columns = position.ascendingColumnRange(other);
        List<ChessBoardPosition> pathPositions = new ArrayList<>();
        for (int i = 0; i < rows.size(); ++i) {
            pathPositions.add(new ChessBoardPosition((char) (columns.get(i).intValue()), rows.get(i)));
        }
        return pathPositions;
    }

    private int calculateRowDistance(int highRow, int lowRow) {
        return Math.abs(highRow - lowRow);
    }

    private int calculateColumnDistance(char highColumn, char lowColumn) {
        return Math.abs(highColumn - lowColumn);
    }
}
