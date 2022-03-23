package chess;

import java.util.Objects;

public class Position {
    private final Rank rank;
    private final File file;

    public Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public boolean isHorizontal(Position position) {
        return this.rank == position.rank;
    }

    public boolean isVertical(Position position) {
        return this.file == position.file;
    }

    public boolean isDiagonal(Position position) {
        return rank.absMinus(position.rank) == file.absMinus(position.file);
    }

    public boolean isOneStepAway(Position position) {
        return isOneStepHorizontalOrVertical(position) || isOneStepDiagonal(position);
    }

    private boolean isOneStepHorizontalOrVertical(Position position) {
        return rank.absMinus(position.rank) + file.absMinus(position.file) == 1;
    }

    private boolean isOneStepDiagonal(Position position) {
        return rank.absMinus(position.rank) == 1 && file.absMinus(position.file) == 1;
    }

    public File getFile() {
        return file;
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
        return rank == position.rank && file == position.file;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, file);
    }

}
