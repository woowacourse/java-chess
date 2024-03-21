package chess.model.position;

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
        return destination.getFile() - source.getFile();
    }

    public int getRankGap() {
        return destination.getRank() - source.getRank();
    }

    public int getFileDistance() {
        return Math.abs(getFileGap());
    }

    public int getRankDistance() {
        return Math.abs(getRankGap());
    }

    public boolean isSameFileOrRank() {
        return isSameFile() || isSameRank();
    }

    public boolean isSameFile() {
        return source.getFile() == destination.getFile();
    }

    private boolean isSameRank() {
        return source.getRank() == destination.getRank();
    }

    public boolean isDiagonal() {
        return Math.abs(getFileGap()) == Math.abs(getRankGap());
    }

    public List<Position> getIntermediatePositions() {
        if (!isSameFileOrRank() && !isDiagonal()) {
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
        return source.getRank() == rank;
    }
}
