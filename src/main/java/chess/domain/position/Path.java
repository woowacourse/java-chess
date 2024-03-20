package chess.domain.position;

import java.util.ArrayList;
import java.util.List;

//TODO : 단위테스트 작성
public class Path {
    private final Position start;
    private final Position end;

    public Path(Position start, Position end) {
        this.start = start;
        this.end = end;
    }

    public int calculateRankDistance() {
        return start.calculateRankDistance(end);
    }

    public int calculateFileDistance() {
        return start.calculateFileDistance(end);
    }

    public boolean isStraight() {
        return isStraightRank() || isStraightFile();
    }

    public boolean isStraight(int maxDistance) {
        return isStraight() && Math.max(calculateFileDistance(), calculateRankDistance()) <= maxDistance;
    }

    private boolean isStraightRank() {
        return calculateRankDistance() > 0 && calculateFileDistance() == 0;
    }

    private boolean isStraightFile() {
        return calculateRankDistance() == 0 && calculateFileDistance() > 0;
    }

    public boolean isDiagonal() {
        return calculateRankDistance() == calculateFileDistance();
    }

    public boolean isDiagonal(int maxDistance) {
        return isDiagonal() && calculateRankDistance() <= maxDistance;
    }

    public int subtractRank() {
        return end.subtractRank(start);
    }

    public int subtractFile() {
        return end.subtractFile(start);
    }

    public boolean isDown(int maxDiff) {
        int diff = start.getRankValue() - end.getRankValue();
        return isStraight() && diff <= maxDiff && diff > 0;
    }

    public boolean isUp(int maxDiff) {
        int diff = end.getRankValue() - start.getRankValue();
        return isStraight() && diff <= maxDiff && diff > 0;
    }

    public List<Position> findStraight() {
        if (isStraightRank()) {
            return findRankStraight();
        }
        return findFileStraight();
    }

    private List<Position> findRankStraight() {
        int maxRankValue = Math.max(start.getRankValue(), end.getRankValue()) - 1;
        int minRankValue = Math.min(start.getRankValue(), end.getRankValue()) + 1;

        List<Position> positions = new ArrayList<>();
        for (int rankValue = minRankValue; rankValue <= maxRankValue; rankValue++) {
            positions.add(new Position(Rank.from(rankValue), start.getFile()));
        }

        return positions;
    }

    private List<Position> findFileStraight() {
        int maxFileValue = Math.max(start.getFileValue(), end.getFileValue()) - 1;
        int minFileValue = Math.min(start.getFileValue(), end.getFileValue()) + 1;

        List<Position> positions = new ArrayList<>();
        for (int fileValue = minFileValue; fileValue <= maxFileValue; fileValue++) {
            positions.add(new Position(start.getRank(), File.from(fileValue)));
        }

        return positions;
    }

    public List<Position> findDiagonal() {
        if (isUphill()) {
            return findUphill();
        }
        return findDownhill();
    }

    private boolean isUphill() {
        return (start.getFileValue() - end.getFileValue()) * (start.getRankValue() - end.getRankValue()) > 0;
    }

    private List<Position> findUphill() {
        int minRankValue = Math.min(start.getRankValue(), end.getRankValue()) + 1;
        int minFileValue = Math.min(start.getFileValue(), end.getFileValue()) + 1;
        int distance = calculateFileDistance() - 1;

        List<Position> positions = new ArrayList<>();
        for (int i = 0; i < distance; i++) {
            positions.add(new Position(Rank.from(minRankValue + i), File.from(minFileValue + i)));
        }
        return positions;
    }

    private List<Position> findDownhill() {
        int maxRankValue = Math.max(start.getRankValue(), end.getRankValue()) + 1;
        int minFileValue = Math.min(start.getFileValue(), end.getFileValue()) + 1;
        int distance = calculateFileDistance() - 1;

        List<Position> positions = new ArrayList<>();
        for (int i = 0; i < distance; i++) {
            positions.add(new Position(Rank.from(maxRankValue - i), File.from(minFileValue + i)));
        }
        return positions;
    }

    @Override
    public String toString() {
        return "Path{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }

    public Position getStart() {
        return start;
    }

    public Position getEnd() {
        return end;
    }
}
