package chess.domain.position;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class PathFinder {
    private final Position start;
    private final Position target;

    public PathFinder(Position start, Position target) {
        this.start = start;
        this.target = target;
    }

    public Set<Position> find() {
        if (isStraight()) {
            return findStraight();
        }
        if (isDiagonal()) {
            return findDiagonal();
        }
        return Collections.emptySet();
    }

    public boolean isStraight() {
        return isStraightRank() || isStraightFile();
    }

    public boolean isStraight(int maxDistance) {
        return isStraight() && Math.max(fileDistance(), rankDistance()) <= maxDistance;
    }

    private boolean isStraightRank() {
        return rankDistance() > 0 && fileDistance() == 0;
    }

    public int rankDistance() {
        return start.rankDistance(target);
    }

    public int fileDistance() {
        return start.fileDistance(target);
    }

    private boolean isStraightFile() {
        return rankDistance() == 0 && fileDistance() > 0;
    }

    private Set<Position> findStraight() {
        if (isStraightRank()) {
            return findStraightRank();
        }
        return findStraightFile();
    }

    private Set<Position> findStraightRank() {
        int maxRankValue = Math.max(start.rankValue(), target.rankValue()) - 1;
        int minRankValue = Math.min(start.rankValue(), target.rankValue()) + 1;

        Set<Position> path = new HashSet<>();
        for (int rankValue = minRankValue; rankValue <= maxRankValue; rankValue++) {
            path.add(new Position(Rank.from(rankValue), start.file()));
        }

        return path;
    }

    private Set<Position> findStraightFile() {
        int maxFileValue = Math.max(start.fileValue(), target.fileValue()) - 1;
        int minFileValue = Math.min(start.fileValue(), target.fileValue()) + 1;

        Set<Position> path = new HashSet<>();
        for (int fileValue = minFileValue; fileValue <= maxFileValue; fileValue++) {
            path.add(new Position(start.rank(), File.from(fileValue)));
        }

        return path;
    }

    public boolean isDiagonal() {
        return rankDistance() == fileDistance();
    }

    public boolean isDiagonal(int maxDistance) {
        return isDiagonal() && rankDistance() <= maxDistance;
    }

    private Set<Position> findDiagonal() {
        if (isUphill()) {
            return findUphill();
        }
        return findDownhill();
    }

    private boolean isUphill() {
        return (target.subtractFile(start)) * (target.subtractRank(start)) > 0;
    }

    private Set<Position> findUphill() {
        int minRankValue = Math.min(start.rankValue(), target.rankValue()) + 1;
        int minFileValue = Math.min(start.fileValue(), target.fileValue()) + 1;
        int pathLength = fileDistance() - 1;

        Set<Position> uphill = new HashSet<>();
        for (int addend = 0; addend < pathLength; addend++) {
            uphill.add(new Position(Rank.from(minRankValue + addend), File.from(minFileValue + addend)));
        }
        return uphill;
    }

    private Set<Position> findDownhill() {
        int maxRankValue = Math.max(start.rankValue(), target.rankValue()) - 1;
        int minFileValue = Math.min(start.fileValue(), target.fileValue()) + 1;
        int pathLength = fileDistance() - 1;

        Set<Position> downhill = new HashSet<>();
        for (int addend = 0; addend < pathLength; addend++) {
            downhill.add(new Position(Rank.from(maxRankValue - addend), File.from(minFileValue + addend)));
        }
        return downhill;
    }

    public int subtractRank() {
        return target.subtractRank(start);
    }

    public boolean isDown(int maxDistance) {
        int rankDiff = target.subtractRank(start);
        int rankDistance = target.rankDistance(start);

        return isStraight() && (rankDiff < 0) && (rankDistance <= maxDistance);
    }

    public boolean isUp(int maxDistance) {
        int rankDiff = target.subtractRank(start);
        int rankDistance = target.rankDistance(start);

        return isStraight() && (rankDiff > 0) && (rankDistance <= maxDistance);
    }

    public boolean isStartRank(int rankValue) {
        return start.rankValue() == rankValue;
    }

    @Override
    public String toString() {
        return "PathFinder{" +
                "start=" + start +
                ", end=" + target +
                '}';
    }

    public Position startPosition() {
        return start;
    }

    public Position targetPosition() {
        return target;
    }
}
