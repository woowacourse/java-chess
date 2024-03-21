package domain.position;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Position {

    private final File file;
    private final Rank rank;

    public Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public boolean isDiagonal(Position target) {
        int fileDistance = file.distance(target.file);
        int rankDistance = rank.distance(target.rank);
        return fileDistance == rankDistance;
    }

    public boolean isStraight(Position target) {
        return file.isSame(target.file) || rank.isSame(target.rank);
    }

    public boolean isStraightDiagonal(Position target) {
        int fileDistance = file.distance(target.file);
        int rankDistance = rank.distance(target.rank);
        return (fileDistance == 1 && rankDistance == 2) || (fileDistance == 2 && rankDistance == 1);
    }

    public boolean isNeighbor(Position target) {
        int fileDistance = file.distance(target.file);
        int rankDistance = rank.distance(target.rank);
        return isDiagonalNeighbor(fileDistance, rankDistance) || isStraightNeighbor(fileDistance, rankDistance);
    }

    private boolean isDiagonalNeighbor(int fileDistance, int rankDistance) {
        return fileDistance == 1 && rankDistance == 1;
    }

    private boolean isStraightNeighbor(int fileDistance, int rankDistance) {
        return (fileDistance == 0 && rankDistance == 1) || (fileDistance == 1 && rankDistance == 0);
    }

    public boolean isForwardStraight(Position target, boolean isBlack) {
        if (rank.isSame(Rank.TWO) || rank.isSame(Rank.SEVEN)) {
            return firstMove(target, isBlack);
        }
        return notFirstMove(target, isBlack);
    }

    public boolean canAttackDiagonal(Position target, boolean isBlack) {
        int rankDistance = rank.forwardDistance(target.rank);
        int fileDistance = file.distance(target.file);
        if (isBlack) {
            return rankDistance == -1 || fileDistance == 1;
        }
        return rankDistance == 1 || fileDistance == 1;
    }

    private boolean notFirstMove(Position target, boolean isBlack) {
        int forwardDistance = rank.forwardDistance(target.rank);
        if (isBlack) {
            return forwardDistance == -1 && file.isSame(target.file);
        }
        return forwardDistance == 1 && file.isSame(target.file);
    }

    private boolean firstMove(Position target, boolean isBlack) {
        int forwardDistance = rank.forwardDistance(target.rank);
        if (isBlack) {
            return (forwardDistance == -1 || forwardDistance == -2) && file.isSame(target.file);
        }
        return (forwardDistance == 1 || forwardDistance == 2) && file.isSame(target.file);
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
