package chess.model.position;

import chess.model.piece.Side;

import java.util.Objects;

public class ChessPosition {
    private final File file;
    private final Rank rank;

    public ChessPosition(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public Distance calculateDistance(ChessPosition other) {
        int fileDifference = file.minus(other.file);
        int rankDifference = rank.minus(other.rank);
        return new Distance(fileDifference, rankDifference);
    }

    public boolean isPawnInitialPosition(Side side) {
        return rank.isPawnInitialRank(side);
    }

    public File findNextFile(int offset) {
        return file.findNextFile(offset);
    }

    public Rank findNextRank(int offset) {
        return rank.findNextRank(offset);
    }

    public File getFile() {
        return file;
    }

    public Rank getRank() {
        return rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessPosition that = (ChessPosition) o;
        return file == that.file && rank == that.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }
}
