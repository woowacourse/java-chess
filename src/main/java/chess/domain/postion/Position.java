package chess.domain.postion;

import chess.domain.Direction;

import java.util.Objects;

public class Position {
    private File file;
    private Rank rank;

    public Position(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public Position from(Direction direction) {
        File nextFile = File.from((char) (file.getName() + direction.file()));
        Rank nextRank = Rank.from(rank.getNumber() + direction.rank());

        return new Position(nextFile, nextRank);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        Position position = (Position) o;
        return file == position.file && rank == position.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }

    public boolean isSameRank(Position other) {
        return rank.equals(other.rank);
    }

    public boolean isSameFile(Position other) {
        return file.equals(other.file);
    }

    public boolean isDiagonal(Position other) {
        return (file.calculateAbsoluteValue(other.file)
                == rank.calculateAbsoluteValue(other.rank));
    }

    public boolean isInOneSquare(Position other) {
        int fileAbsoluteValue = file.calculateAbsoluteValue(other.file);
        int rankAbsoluteValue = rank.calculateAbsoluteValue(other.rank);

        if (fileAbsoluteValue + rankAbsoluteValue == 1) {
            return true;
        }

        if (fileAbsoluteValue == 1 && rankAbsoluteValue == 1) {
            return true;
        }

        return false;
    }
}
