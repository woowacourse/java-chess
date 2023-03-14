package chess.domain.piece.position;

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
}
