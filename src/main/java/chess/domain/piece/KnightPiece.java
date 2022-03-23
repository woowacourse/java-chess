package chess.domain.piece;

public class KnightPiece extends FullPiece{

    private static final String WHITE_NAME = "n";
    private static final String BLACK_NAME = "N";

    private final String name;

    public KnightPiece(final Color color) {
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
