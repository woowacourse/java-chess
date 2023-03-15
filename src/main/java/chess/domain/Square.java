package chess.domain;

import java.util.Objects;

public class Square {
    private final File file;
    private final Rank rank;

    public Square(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Square square = (Square) o;
        return file == square.file && rank == square.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }

    public int getFile() {
        return file.getX();
    }

    public int getRank() {
        return rank.getY();
    }

    public boolean isAble(final Square target, final int fileWeight, final int rankWeight) {
        final int targetFile = target.getFile();
        final int targetRank = target.getRank();

        return isAbleFile(targetFile, fileWeight) && isAbleRank(targetRank, rankWeight);
    }

    private boolean isAbleFile(final int targetFile, final int fileWeight) {
        return getFile() + fileWeight == targetFile;
    }

    private boolean isAbleRank(final int targetRank, final int rankWeight) {
        return getRank() + rankWeight == targetRank;
    }

    public Square nextSquare(final Square target, final int fileWeight, final int rankWeight) {
        final int targetFile = target.getFile();
        final int targetRank = target.getRank();

        if (isAbleFile(targetFile, fileWeight) && isAbleRank(targetRank, rankWeight)) {
            return new Square(file.findFile(targetFile), rank.findRank(targetRank));
        }
        return null;
    }
}
