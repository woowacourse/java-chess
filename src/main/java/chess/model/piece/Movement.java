package chess.model.piece;

import chess.model.board.Position;
import java.util.ArrayList;
import java.util.List;

public class Movement {
    private final Position source;
    private final Position destination;

    public Movement(Position source, Position destination) {
        if (source == destination) {
            throw new IllegalArgumentException("출발지와 도착지가 동일하면 움직일 수 없습니다.");
        }
        this.source = source;
        this.destination = destination;
    }

    public int getFileGap() {
        return source.getFileGap(destination);
    }

    public int getRankGap() {
        return source.getRankGap(destination);
    }

    public int getFileDistance() {
        return Math.abs(getFileGap());
    }

    public int getRankDistance() {
        return Math.abs(getRankGap());
    }

    public boolean isStraight() {
        return getFileGap() == 0 || getRankGap() == 0;
    }

    public boolean isVertical() {
        return this.getFileGap() == 0;
    }

    public boolean isDiagonal() {
        return Math.abs(getFileGap()) == Math.abs(getRankGap());
    }

    public List<Position> getIntermediatePositions() {
        if (!isStraight() && !isDiagonal()) {
            throw new IllegalStateException("직선이나 대각선 방향이 아닙니다.");
        }
        List<Position> positions = new ArrayList<>();
        Position currentPosition = source.moveToTargetByStep(destination);
        while (!currentPosition.equals(destination)) {
            positions.add(currentPosition);
            currentPosition = currentPosition.moveToTargetByStep(destination);
        }
        return positions;
    }

    public boolean isSourceRankMatch(int rank) {
        return source.isRankEquals(rank);
    }
}
