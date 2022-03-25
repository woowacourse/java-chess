package chess.domain.piece;

import chess.domain.ChessBoardPosition;
import chess.domain.ChessMen;
import chess.domain.Team;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Bishop extends ChessPiece {
    private static final String NAME = "BISHOP";
    private static final String UNEXPECTED_MOVEMENT_EXCEPTION = "[ERROR] 비숍이 이동할 수 없는 위치입니다.";

    public Bishop(Team team, ChessBoardPosition position) {
        super(NAME, team, position);
    }

    private int calculateRowDistance(int highRow, int lowRow) {
        return Math.abs(highRow - lowRow);
    }

    private int calculateColumnDistance(char highColumn, char lowColumn) {
        return Math.abs(highColumn - lowColumn);
    }

    public boolean isSamePosition(ChessBoardPosition nextPosition) {
        return position.equals(nextPosition);
    }

    @Override
    public void move(ChessBoardPosition targetPosition) {
        position = targetPosition;
    }

    @Override
    public boolean movable(ChessBoardPosition targetPosition, ChessMen chessMen) {
        return reachable(targetPosition) && unobstructed(targetPosition, chessMen);
    }

    private boolean reachable(ChessBoardPosition targetPosition) {
        int rowDistance = calculateRowDistance(position.getRow(), targetPosition.getRow());
        int columnDistance = calculateColumnDistance(position.getColumn(), targetPosition.getColumn());
        return rowDistance == columnDistance;
    }

    private boolean unobstructed(ChessBoardPosition targetPosition, ChessMen chessMen) {
        List<Integer> rows = ascendingRange(position.getRow(), targetPosition.getRow());
        List<Integer> columns = ascendingRange(position.getColumn(), targetPosition.getColumn());
        List<ChessBoardPosition> pathPositions = new ArrayList<>();

        for (int i = 0; i < rows.size(); ++i) {
            pathPositions.add(new ChessBoardPosition((char) (columns.get(i).intValue()), rows.get(i)));
        }

        return pathPositions.stream()
                .noneMatch(chessMen::existChessPieceAt);
    }

    private List<Integer> ascendingRange(int source, int target) {
        if (source > target) {
            return rangeClosed(target, source);
        }
        return rangeClosed(source, target);
    }

    private List<Integer> rangeClosed(int start, int end) {
        return IntStream.rangeClosed(start, end)
                .boxed()
                .collect(Collectors.toList());
    }
}
