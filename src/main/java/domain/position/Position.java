package domain.position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

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

    private boolean hasFile(File file) {
        return this.file == file;
    }

    public Direction direction(Position target) {
        return Direction.asDirection(this, target);
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

    public enum Direction { // TODO: 한 칸 검증을 여기서 하는 게 맞을까? -> Movement or Vector?

        UP((source, target) -> source.file.equals(target.file) && source.rank.isUp(target.rank)),
        DOWN((source, target) -> source.file.equals(target.file) && source.rank.isDown(target.rank)),
        RIGHT((source, target) -> source.file.isRight(target.file) && source.rank.equals(target.rank)),
        LEFT((source, target) -> source.file.isLeft(target.file) && source.rank.equals(target.rank)),
        RIGHT_UP((source, target) -> source.file.isRight(target.file) && source.rank.isUp(target.rank)
                && source.isSameDistance(target)),
        RIGHT_DOWN((source, target) -> source.file.isRight(target.file) && source.rank.isDown(target.rank)
                && source.isSameDistance(target)),
        LEFT_UP((source, target) -> source.file.isLeft(target.file) && source.rank.isUp(target.rank)
                && source.isSameDistance(target)),
        LEFT_DOWN((source, target) -> source.file.isLeft(target.file) && source.rank.isDown(target.rank)
                && source.isSameDistance(target)),
        NONE((source, target) -> false),
        ;

        private final BiPredicate<Position, Position> condition;

        Direction(BiPredicate<Position, Position> condition) {
            this.condition = condition;
        }

        private static Direction asDirection(Position source, Position target) {
            return Arrays.stream(values())
                    .filter(direction -> direction.meetCondition(source, target))
                    .findFirst()
                    .orElse(NONE);
        }

        public static Set<Direction> allDirections() {
            Set<Direction> values = Set.of(values());
            return values.stream()
                    .filter(direction -> direction != NONE)
                    .collect(Collectors.toSet());
        }

        private boolean meetCondition(Position source, Position target) {
            return condition.test(source, target);
        }
    }
}
