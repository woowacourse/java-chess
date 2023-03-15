package chess.domain.piece.position;

import java.util.Objects;

public class PiecePosition {

    private final Rank rank;
    private final File file;

    private PiecePosition(final Rank rank, final File file) {
        this.rank = rank;
        this.file = file;
    }

    public static PiecePosition of(final int rank, final char file) {
        return new PiecePosition(Rank.from(rank), File.from(file));
    }

    public static PiecePosition of(final Rank rank, final File file) {
        return new PiecePosition(rank, file);
    }

    public int fileDistance(final PiecePosition piecePosition) {
        return file.interval(piecePosition.file);
    }

    public int rankDistance(final PiecePosition piecePosition) {
        return rank.interval(piecePosition.rank);
    }

    public PiecePosition move(final Direction direction) {
        return PiecePosition.of(rank.plus(direction.rankUnit()), file.plus(direction.fileUnit()));
    }

    public Direction direction(final PiecePosition destination) {
        return Direction.byDisplacement(rankDistance(destination), fileDistance(destination));
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof PiecePosition)) return false;
        final PiecePosition that = (PiecePosition) o;
        return Objects.equals(rank, that.rank) && Objects.equals(file, that.file);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, file);
    }
}
