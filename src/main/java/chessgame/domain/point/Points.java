package chessgame.domain.point;

import java.util.Collections;
import java.util.List;

public class Points {
    public static final int SOURCE_INDEX = 0;
    public static final int TARGET_INDEX = 1;

    private final List<Point> points;

    public Points(List<Point> points) {
        this.points = points;
    }

    public int fileDistance() {
        return target().fileDistance(source());
    }

    public int rankDistance() {
        return target().rankDistance(source());
    }

    public boolean isHorizontal() {
        return !isSameFileDistance(0) && isSameRankDistance(0);
    }

    public boolean isVertical() {
        return isSameFileDistance(0) && !isSameRankDistance(0);
    }

    public boolean isDiagonal() {
        if (isSameFileDistance(0) || isSameRankDistance(0)) {
            return false;
        }
        return Math.abs(fileDistance()) == Math.abs(rankDistance());
    }

    public boolean isInitialPoint(Rank initialRank) {
        return source().isInitialPoint(initialRank);
    }

    public boolean isSameFileDistance(int distance) {
        return Math.abs(fileDistance()) == distance;
    }

    public boolean isSameRankDistance(int distance) {
        return Math.abs(rankDistance()) == distance;
    }

    public int maxDistance() {
        int fileDifference = Math.abs(fileDistance());
        int rankDifference = Math.abs(rankDistance());

        return Math.max(fileDifference, rankDifference);
    }

    public int fileMove(int distance) {
        return fileDistance() / distance;
    }

    public int rankMove(int distance) {
        return rankDistance() / distance;
    }

    public Point source() {
        return points.get(SOURCE_INDEX);
    }

    public Point target() {
        return points.get(TARGET_INDEX);
    }

    public List<Point> getPoints() {
        return Collections.unmodifiableList(points);
    }
}
