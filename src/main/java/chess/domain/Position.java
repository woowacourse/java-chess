package chess.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Position {
    private final File file;
    private final Rank rank;

    public Position(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public boolean isInCrossPosition(final Position otherPosition) {
        return isInVerticalPosition(otherPosition) || isInHorizontalPosition(otherPosition);
    }

    private boolean isInHorizontalPosition(final Position otherPosition) {
        return this.rank == otherPosition.rank && !this.equals(otherPosition);
    }

    private boolean isInVerticalPosition(final Position otherPosition) {
        return this.file == otherPosition.file && !this.equals(otherPosition);
    }

    public boolean isInDiagonalPosition(final Position otherPosition) {
        return (calculateFileDistance(otherPosition) == calculateRankDistance(otherPosition)) && !this.equals(otherPosition);
    }

    private int calculateFileDistance(final Position otherPosition) {
        return this.file.calculateDistance(otherPosition.file);
    }

    private int calculateRankDistance(final Position otherPosition) {
        return this.rank.calculateDistance(otherPosition.rank);
    }

    public int calculateManhattanDistance(final Position otherPosition) {
        return calculateFileDistance(otherPosition) + calculateRankDistance(otherPosition);
    }

    public List<Position> findPassingPositions(final Position otherPosition) {
        if (isInHorizontalPosition(otherPosition)) {
            return findHorizontalPassingPositions(otherPosition);
        }
        if (isInVerticalPosition(otherPosition)) {
            return findVerticalPassingPositions(otherPosition);
        }
        if (isInDiagonalPosition(otherPosition)) {
            return findDiagonalPassingPositions(otherPosition);
        }
        return List.of();
    }

    private List<Position> findDiagonalPassingPositions(final Position otherPosition) {
        final List<File> files = this.file.getFilesTo(otherPosition.file);
        final List<Rank> ranks = this.rank.getRanksTo(otherPosition.rank);
        List<Position> passingPositions = new ArrayList<>();
        for (int index = 0; index < files.size(); index++) {
            passingPositions.add(new Position(files.get(index), ranks.get(index)));
        }
        return passingPositions;
    }

    private List<Position> findVerticalPassingPositions(final Position otherPosition) {
        List<Position> passingPositions = new ArrayList<>();
        for (Rank rank : this.rank.getRanksTo(otherPosition.rank)) {
            passingPositions.add(new Position(this.file, rank));
        }
        return passingPositions;
    }

    private List<Position> findHorizontalPassingPositions(final Position otherPosition) {
        List<Position> passingPositions = new ArrayList<>();
        for (File file : this.file.getFilesTo(otherPosition.file)) {
            passingPositions.add(new Position(file, this.rank));
        }
        return passingPositions;
    }

    public boolean isUpperRankThan(final Position otherPosition) {
        return this.rank.isUpperThan(otherPosition.rank);
    }

    public boolean isLowerRankThan(final Position otherPosition) {
        return this.rank.isLowerThan(otherPosition.rank);
    }

    public boolean isSameFile(final File file) {
        return this.file == file;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Position position = (Position) o;
        return file == position.file && rank == position.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }

    public Rank getRank() {
        return rank;
    }

    public File getFile() {
        return file;
    }
}
