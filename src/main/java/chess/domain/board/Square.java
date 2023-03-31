package chess.domain.board;

import java.util.Objects;

public class Square {
    private final File file;
    private final Rank rank;

    public Square(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public boolean isMovableToTarget(Square target, int fileWeight, int rankWeight) {
        int targetFile = target.getFileToInt();
        int targetRank = target.getRankToInt();

        return isMovableToTargetFile(targetFile, fileWeight) && isMovableToTargetRank(targetRank, rankWeight);
    }

    private boolean isMovableToTargetFile(int targetFile, int fileWeight) {
        return getFileToInt() + fileWeight == targetFile;
    }

    private boolean isMovableToTargetRank(int targetRank, int rankWeight) {
        return getRankToInt() + rankWeight == targetRank;
    }

    public Square nextSquare(Square source, int fileWeight, int rankWeight) {
        int sourceFile = source.getFileToInt() + fileWeight;
        int sourceRank = source.getRankToInt() + rankWeight;

        return new Square(File.findFile(sourceFile), Rank.findRank(sourceRank));
    }

    public int calculateFileGap(Square target) {
        return target.file.calculateGap(file);
    }

    public int calculateRankGap(Square target) {
        return target.rank.calculateGap(rank);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Square square = (Square) o;
        return file == square.file && rank == square.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }

    public int getFileToInt() {
        return file.getFile();
    }

    public int getRankToInt() {
        return rank.getRank();
    }

    public File getFile() {
        return file;
    }

    public Rank getRank() {
        return rank;
    }
}
