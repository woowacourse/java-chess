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

    public Movement calculateMovement(ChessPosition other) {
        Difference fileDifference = file.minus(other.file);
        Difference rankDifference = rank.minus(other.rank);
        return new Movement(fileDifference, rankDifference);
    }

    public boolean hasRank(Rank rank) {
        return this.rank == rank;
    }

    public File calculateNextFile(int offset) {
        return file.calculateNextFile(offset);
    }

    public Rank calculateNextRank(int offset) {
        return rank.calculateNextRank(offset);
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
