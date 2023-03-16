package chessgame.domain.point;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import chessgame.domain.Team;

public class Point {
    private static final Map<String, Point> cache = new HashMap<>(64);

    private final File file;
    private final Rank rank;

    private Point(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Point of(File file, Rank rank) {
        return cache.computeIfAbsent(toKey(file, rank), ignore -> new Point(file, rank));
    }

    private static String toKey(File file, Rank rank) {
        return file.name() + rank.name();
    }

    public boolean isHorizontal(Point target) {
        return file.distance(target.file) != 0 && rank.distance(target.rank) == 0;
    }

    public boolean isVertical(Point target) {
        return file.distance(target.file) == 0 && rank.distance(target.rank) != 0;
    }

    public boolean isDiagonal(Point target) {
        if (file.distance(target.file) == 0 || rank.distance(target.rank) == 0) {
            return false;
        }
        return Math.abs(file.distance(target.file)) == Math.abs(rank.distance(target.rank));
    }

    public boolean isAllDirectionOneDistance(Point target) {
        if (isHorizontal(target) && Math.abs(file.distance(target.file)) == 1) {
            return true;
        }
        if (isVertical(target) && Math.abs(rank.distance(target.rank)) == 1) {
            return true;
        }
        return isDiagonal(target) && Math.abs(file.distance(target.file)) == 1
            && Math.abs(rank.distance(target.rank)) == 1;
    }

    public boolean isKnightMove(Point target) {
        int fileDistance = Math.abs(file.distance(target.file));
        int rankDistance = Math.abs(rank.distance(target.rank));

        if (fileDistance == 2 && rankDistance == 1) {
            return true;
        }
        return fileDistance == 1 && rankDistance == 2;
    }

    public boolean isPawnMove(Point target, Team team) {
        if(team == Team.BLACK && file.distance(target.file) == 0){
            if(rank.distance(Rank.SEVEN)==0 && (rank.distance(target.rank) == 1 || rank.distance(target.rank) == 2)){
                return true;
            }
            if(rank.distance(target.rank) == 1){
                return true;
            }
        }
        if(team == Team.WHITE && file.distance(target.file) == 0){
            if(rank.distance(Rank.TWO)==0 && (rank.distance(target.rank) == -1 || rank.distance(target.rank) == -2)){
                return true;
            }
            if(rank.distance(target.rank) == -1){
                return true;
            }
        }
        return false;
    }

    public boolean isPawnAttack(Point target, Team team) {
        if (team == Team.BLACK) {
            if (rank.distance(target.rank) == 1 && file.distance(target.file) == 1) {
                return true;
            }
            if (rank.distance(target.rank) == 1 && file.distance(target.file) == -1) {
                return true;
            }
        }
        if (team == Team.WHITE) {
            if (rank.distance(target.rank) == -1 && file.distance(target.file) == -1) {
                return true;
            }
            if (rank.distance(target.rank) == -1 && file.distance(target.file) == 1) {
                return true;
            }
        }
        return false;
    }

    public Point move(int fileMove, int rankMove) {
        return Point.of(file.move(fileMove).get(), rank.move(rankMove).get());
    }

    public int makeFileDifference(Point target) {
        return this.file.distance(target.file);
    }

    public int makeRankDifference(Point target) {
        return this.rank.distance(target.rank);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Point point = (Point)o;
        return file == point.file && rank == point.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }

    @Override
    public String toString() {
        return "Point{" +
            "rank=" + file +
            ", file=" + rank +
            '}';
    }
}
