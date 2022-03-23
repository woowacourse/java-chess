package chess.domain.piece;

public class KingPiece extends FullPiece {

    private static final String WHITE_NAME = "k";
    private static final String BLACK_NAME = "K";

    private final String name;

    public KingPiece(final Color color) {
        super(color);
        this.name = decideName(color);
    }

    private String decideName(final Color color) {
        if (color == Color.WHITE) {
            return WHITE_NAME;
        }
        return BLACK_NAME;
    }

    @Override
    public String getName() {
        return name;
    }
}
