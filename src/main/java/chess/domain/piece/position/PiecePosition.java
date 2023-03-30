package chess.domain.piece.position;

import java.util.Objects;

public class PiecePosition {

    private static final int FILE_INDEX = 0;
    private static final int RANK_INDEX = 1;
    private final File file;
    private final Rank rank;

    private PiecePosition(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static PiecePosition of(final char file, final int rank) {
        return new PiecePosition(new File(file), new Rank(rank));
    }

    public static PiecePosition of(final File file, final Rank rank) {
        return new PiecePosition(file, rank);
    }

    public static PiecePosition of(final String fileAndRank) {
        final String[] split = fileAndRank.split("");
        final char file = fileAndRank.charAt(FILE_INDEX);
        final int rank = Integer.parseInt(split[RANK_INDEX]);
        return PiecePosition.of(file, rank);
    }

    public int fileDistance(final PiecePosition piecePosition) {
        return file.interval(piecePosition.file);
    }

    public int rankDistance(final PiecePosition piecePosition) {
        return rank.interval(piecePosition.rank);
    }

    public PiecePosition move(final Direction direction) {
        return PiecePosition.of(file.plus(direction.fileUnit()), rank.plus(direction.rankUnit()));
    }

    public Direction direction(final PiecePosition destination) {
        return Direction.byDisplacement(rankDistance(destination), fileDistance(destination));
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof PiecePosition)) return false;
        final PiecePosition that = (PiecePosition) o;
        return Objects.equals(file, that.file) && Objects.equals(rank, that.rank);
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }

    public File file() {
        return file;
    }

    public int rank() {
        return rank.value();
    }
}
