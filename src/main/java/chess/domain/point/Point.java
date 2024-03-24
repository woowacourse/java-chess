package chess.domain.point;

import chess.domain.piece.Direction;
import java.util.EnumMap;

public class Point {

    private static final int SlOPE_TWO = 2;
    private static final EnumMap<File, EnumMap<Rank, Point>> POOL;

    static {
        POOL = new EnumMap<>(File.class);
        for (File file : File.values()) {
            EnumMap<Rank, Point> rankPoints = new EnumMap<>(Rank.class);
            for (Rank rank : Rank.values()) {
                rankPoints.put(rank, new Point(file, rank));
            }
            POOL.put(file, rankPoints);
        }
    }

    private final File file;
    private final Rank rank;

    private Point(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Point of(File file, Rank rank) {
        return POOL.get(file).get(rank);
    }

    public boolean isDiagonal(Point point) {
        int fileDistance = this.file.distance(point.file);
        int rankDistance = this.rank.distance(point.rank);
        if (this.equals(point) || rankDistance == 0) {
            return false;
        }

        return isSlopeOne(fileDistance, rankDistance);
    }

    private boolean isSlopeOne(int fileDistance, int rankDistance) {
        return Math.abs(fileDistance / rankDistance) == 1;
    }

    public boolean isStraight(Point point) {
        if (this.equals(point)) {
            return false;
        }
        return this.file.equals(point.file) || this.rank.equals(point.rank);
    }

    public boolean isAround(Point point) {
        if (this.equals(point)) {
            return false;
        }
        int fileDistance = this.file.distance(point.file);
        int rankDistance = this.rank.distance(point.rank);
        int distance = getDistance(fileDistance, rankDistance);
        if (fileDistance != 0 && rankDistance != 0) {
            return distance == 2;
        }
        return distance == 1;
    }

    private int getDistance(int fileDistance, int rankDistance) {
        return Math.abs(fileDistance) + Math.abs(rankDistance);
    }

    public int multiplyAxis(Point point) {
        int fileDistance = this.file.distance(point.file);
        int rankDistance = this.rank.distance(point.rank);
        return fileDistance * rankDistance;
    }

    public boolean isSameRank(Rank rank) {
        return this.rank == rank;
    }

    public Point add(int addFile, int addRank) {
        File addedFile = file.add(addFile);
        Rank addedRank = rank.add(addRank);

        return Point.of(addedFile, addedRank);
    }

    public boolean addable(int addFile, int addRank) {
        return file.addable(addFile) && rank.addable(addRank);
    }

    public Direction findUnitDirection(Point point) {
        int fileDistance = point.file.distance(this.file);
        int rankDistance = point.rank.distance(this.rank);
        if (Math.abs(multiplyAxis(point)) == SlOPE_TWO) {
            return Direction.of(fileDistance, rankDistance);
        }
        return Direction.of(unitDistance(fileDistance), unitDistance(rankDistance));
    }

    private int unitDistance(int distance) {
        if (distance == 0) {
            return 0;
        }
        return distance < 0 ? -1 : 1;
    }
}
