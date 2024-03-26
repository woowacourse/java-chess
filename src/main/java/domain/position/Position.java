package domain.position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Position { // TODO: refactoring (너무 무거움)

    private final File file;
    private final Rank rank;

    protected Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public boolean isSameFile(Position target) {
        return this.file == target.file;
    }

    public boolean isSameRank(Position target) {
        return this.rank == target.rank;
    }

    public boolean hasRank(Rank rank) {
        return this.rank == rank;
    }

    public boolean hasRank(List<Rank> ranks) {
        return ranks.contains(rank);
    }

    private boolean hasFile(File file) {
        return this.file == file;
    }

    public boolean isStraight(Position target) {
        return isVertical(target) || isHorizontal(target);
    }

    private boolean isVertical(Position target) {
        return file == target.file;
    }

    private boolean isHorizontal(Position target) {
        return rank == target.rank;
    }

    public boolean isUp(Position target) {
        return file == target.file && rank.isUp(target.rank);
    }

    public boolean isDown(Position target) {
        return file == target.file && rank.isDown(target.rank);
    }

    public boolean isRight(Position target) {
        return rank == target.rank && file.isRight(target.file);
    }

    public boolean isLeft(Position target) {
        return rank == target.rank && file.isLeft(target.file);
    }

    public boolean isDiagonal(Position target) {
        int fileDistance = file.distance(target.file);
        int rankDistance = rank.distance(target.rank);
        return fileDistance == rankDistance;
    }

    public boolean isRightUp(Position target) {
        return rank.isUp(target.rank) && file.isRight(target.file);
    }

    public boolean isLeftUp(Position target) {
        return rank.isUp(target.rank) && file.isLeft(target.file);
    }

    public boolean isRightDown(Position target) {
        return rank.isDown(target.rank) && file.isRight(target.file);
    }

    public boolean isLeftDown(Position target) {
        return rank.isDown(target.rank) && file.isLeft(target.file);
    }

    public boolean isSameDistance(Position target) {
        return file.distance(target.file) == rank.distance(target.rank);
    }

    public boolean isLegalRankStep(Position target, Integer... step) {
        List<Integer> steps = Arrays.stream(step).toList();
        int distance = rank.distance(target.rank);
        return steps.contains(distance);
    }

    public boolean isLegalFileStep(Position target, Integer... step) {
        List<Integer> steps = Arrays.stream(step).toList();
        int distance = file.distance(target.file);
        return steps.contains(distance);
    }

    public List<Position> findBetweenStraightPositions(Position target) {
        if (hasFile(target.file)) {
            return findBetweenVerticalPositions(target.rank);
        }
        return findBetweenHorizontalPositions(target.file);
    }

    private List<Position> findBetweenVerticalPositions(Rank targetRank) {
        return rank.betweenRanks(targetRank).stream()
                .map(rank -> PositionGenerator.generate(file, rank))
                .toList();
    }

    private List<Position> findBetweenHorizontalPositions(File targetFile) {
        return file.betweenFiles(targetFile).stream()
                .map(file -> PositionGenerator.generate(file, rank))
                .toList();
    }

    public List<Position> findBetweenDiagonalPositions(Position target) {
        List<File> files = file.betweenFiles(target.file);
        List<Rank> ranks = rank.betweenRanks(target.rank);

        List<Position> positions = new ArrayList<>();
        for (int index = 0; index < ranks.size(); index++) {
            Position position = PositionGenerator.generate(files.get(index), ranks.get(index));
            positions.add(position);
        }
        return positions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Position position = (Position) o;
        return file == position.file && rank == position.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }
}
