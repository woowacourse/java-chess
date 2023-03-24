package chess.domain.piece.position;

import java.util.Objects;

public class PiecePosition {

    private static final int RANK_INDEX = 1;
    private static final int FILE_INDEX = 0;
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

    public static PiecePosition of(final String rankAndFile) {
        validateLength(rankAndFile);
        final String[] split = rankAndFile.split("");
        final int rank = Integer.parseInt(split[RANK_INDEX]);
        final char file = rankAndFile.charAt(FILE_INDEX);
        return PiecePosition.of(rank, file);
    }

    private static void validateLength(final String rankAndFile) {
        if (rankAndFile.length() != RANK_INDEX + 1) {
            throw new IllegalArgumentException("위치가 올바르지 않습니다");
        }
    }

    public int fileInterval(final PiecePosition piecePosition) {
        return file.interval(piecePosition.file);
    }

    public int rankInterval(final PiecePosition piecePosition) {
        return rank.interval(piecePosition.rank);
    }

    public PiecePosition move(final Direction direction) {
        return PiecePosition.of(
                rank.plus(direction.rankUnit()),
                file.plus(direction.fileUnit())
        );
    }

    public Direction direction(final PiecePosition destination) {
        return Direction.byInterval(
                rankInterval(destination),
                fileInterval(destination)
        );
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

    public int rankValue() {
        return rank.value();
    }

    public File file() {
        return file;
    }

    public Rank rank() {
        return rank;
    }
}
