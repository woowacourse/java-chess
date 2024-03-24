package chess.domain.position;

import java.util.ArrayList;
import java.util.List;

// TODO: 단위테스트 작성
// TODO: 첵임을 줄이는 방안 고려하기
public class Path {
    private final Position start;
    private final Position target;

    public Path(Position start, Position target) {
        this.start = start;
        this.target = target;
    }

    public int calculateRankDistance() {
        return start.calculateRankDistance(target);
    }

    public int calculateFileDistance() {
        return start.calculateFileDistance(target);
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
        return target.subtractRank(start);
    }

    public int subtractFile() {
        return target.subtractFile(start);
    }

    public boolean isDown(int maxDiff) {
        int diff = start.getRankValue() - target.getRankValue();
        return isStraight() && diff <= maxDiff && diff > 0;
    }

    public boolean isUp(int maxDiff) {
        int diff = target.getRankValue() - start.getRankValue();
        return isStraight() && diff <= maxDiff && diff > 0;
    }

    public List<Position> findStraight() {
        if (isStraightRank()) {
            return findRankStraight();
        }
        return findFileStraight();
    }

    private List<Position> findRankStraight() {
        int maxRankValue = Math.max(start.getRankValue(), target.getRankValue()) - 1;
        int minRankValue = Math.min(start.getRankValue(), target.getRankValue()) + 1;

        List<Position> positions = new ArrayList<>();
        for (int rankValue = minRankValue; rankValue <= maxRankValue; rankValue++) {
            positions.add(new Position(Rank.from(rankValue), start.getFile()));
        }

        return positions;
    }

    private List<Position> findFileStraight() {
        int maxFileValue = Math.max(start.getFileValue(), target.getFileValue()) - 1;
        int minFileValue = Math.min(start.getFileValue(), target.getFileValue()) + 1;

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
        return (start.getFileValue() - target.getFileValue()) * (start.getRankValue() - target.getRankValue()) > 0;
    }

    private List<Position> findUphill() {
        int minRankValue = Math.min(start.getRankValue(), target.getRankValue()) + 1;
        int minFileValue = Math.min(start.getFileValue(), target.getFileValue()) + 1;
        int distance = calculateFileDistance() - 1;

        List<Position> positions = new ArrayList<>();
        for (int i = 0; i < distance; i++) {
            positions.add(new Position(Rank.from(minRankValue + i), File.from(minFileValue + i)));
        }
        return positions;
    }

    private List<Position> findDownhill() {
        int maxRankValue = Math.max(start.getRankValue(), target.getRankValue()) + 1;
        int minFileValue = Math.min(start.getFileValue(), target.getFileValue()) + 1;
        int distance = calculateFileDistance() - 1;

        List<Position> positions = new ArrayList<>();
        for (int i = 0; i < distance; i++) {
            positions.add(new Position(Rank.from(maxRankValue - i), File.from(minFileValue + i)));
        }
        return positions;
    }

    public boolean isStartRank(int rankValue) {
        return start.getRankValue() == rankValue;
    }

    @Override
    public String toString() {
        return "Path{" +
                "start=" + start +
                ", end=" + target +
                '}';
    }

    public Position getStartPosition() {
        return start;
    }

    public Position getTargetPosition() {
        return target;
    }
}
