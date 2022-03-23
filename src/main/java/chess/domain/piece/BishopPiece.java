package chess.domain.piece;

public class BishopPiece extends FullPiece {

    private static final String WHITE_NAME = "b";
    private static final String BLACK_NAME = "B";

    private final String name;

    public BishopPiece(final Color color) {
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
