package domain.position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Position {

    private final File file;
    private final Rank rank;

    public Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public boolean hasRank(Rank rank) {
        return this.rank.isSame(rank);
    }

    // TODO: source와 target이 같은 위치라면?

    public boolean isStraight(Position target) {
        return isVertical(target) || isHorizontal(target);
    }

    private boolean isHorizontal(Position target) {
        return rank.isSame(target.rank);
    }

    private boolean isVertical(Position target) {
        return file.isSame(target.file);
    }

    public boolean isUp(Position target) {
        return file.isSame(target.file) && rank.isUp(target.rank);
    }

    public boolean isDown(Position target) {
        return file.isSame(target.file) && rank.isDown(target.rank);
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
        if (file.isSame(target.file)) {
            return rank.betweenRanks(target.rank).stream()
                    .map(rank -> new Position(file, rank))
                    .toList();
        }
        return file.betweenFiles(target.file).stream()
                .map(file -> new Position(file, rank))
                .toList();
    }

    public List<Position> findBetweenDiagonalPositions(Position target) {
        List<File> files = file.betweenFiles(target.file);
        List<Rank> ranks = rank.betweenRanks(target.rank);

        List<Position> positions = new ArrayList<>();
        for (int index = 0; index < ranks.size(); index++) {
            Position position = new Position(files.get(index), ranks.get(index));
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
