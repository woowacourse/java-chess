package domain.position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Position { // TODO: refactoring (너무 무거움)

    private final File file;
    private final Rank rank;

    protected Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public boolean hasRank(Rank rank) {
        return this.rank == rank;
    }

    public boolean hasRank(List<Rank> ranks) {
        return ranks.contains(rank);
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

    public boolean isRightUp(Position target) {
        return rank.isUp(target.rank) && file.isRight(target.file)
                && isSameDistance(target);
    }

    public boolean isLeftUp(Position target) {
        return rank.isUp(target.rank) && file.isLeft(target.file)
                && isSameDistance(target);
    }

    public boolean isRightDown(Position target) {
        return rank.isDown(target.rank) && file.isRight(target.file)
                && isSameDistance(target);
    }

    public boolean isLeftDown(Position target) {
        return rank.isDown(target.rank) && file.isLeft(target.file)
                && isSameDistance(target);
    }

    private boolean isSameDistance(Position target) {
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

    public List<Position> betweenPositions(Position target) {
        Direction direction = Direction.asDirection(this, target);
        Set<Direction> straightDirections = Direction.straightDirections();
        Set<Direction> diagonalDirections = Direction.diagonalDirections();
        if (straightDirections.contains(direction)) {
            return betweenStraightPositions(target);
        }
        if (diagonalDirections.contains(direction)) {
            return betweenDiagonalPositions(target);
        }
        return List.of();
    }

    private List<Position> betweenStraightPositions(Position target) {
        if (file == target.file) {
            return betweenVerticalPositions(target.rank);
        }
        return betweenHorizontalPositions(target.file);
    }

    private List<Position> betweenVerticalPositions(Rank targetRank) {
        return rank.betweenRanks(targetRank).stream()
                .map(rank -> PositionGenerator.generate(file, rank))
                .toList();
    }

    private List<Position> betweenHorizontalPositions(File targetFile) {
        return file.betweenFiles(targetFile).stream()
                .map(file -> PositionGenerator.generate(file, rank))
                .toList();
    }

    private List<Position> betweenDiagonalPositions(Position target) {
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
