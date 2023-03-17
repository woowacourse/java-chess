package chess.domain.board;

import java.util.Objects;

public class Square {
    private final File file;
    private final Rank rank;

    public Square(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public boolean isMovableToTarget(final Square target, final int fileWeight, final int rankWeight) {
        final int targetFile = target.getFile();
        final int targetRank = target.getRank();

        return isMovableToTargetFile(targetFile, fileWeight) && isMovableToTargetRank(targetRank, rankWeight);
    }

    private boolean isMovableToTargetFile(final int targetFile, final int fileWeight) {
        return getFile() + fileWeight == targetFile;
    }

    private boolean isMovableToTargetRank(final int targetRank, final int rankWeight) {
        return getRank() + rankWeight == targetRank;
    }

    public Square nextSquare(final Square source, final int fileWeight, final int rankWeight) {
        final int sourceFile = source.getFile() + fileWeight;
        final int sourceRank = source.getRank() + rankWeight;

        return new Square(File.findFile(sourceFile), Rank.findRank(sourceRank));
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
}
