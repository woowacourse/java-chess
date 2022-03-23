package chess.domain.piece;

public class PawnPiece extends FullPiece{

    private static final String WHITE_NAME = "p";
    private static final String BLACK_NAME = "P";

    private final String name;

    public PawnPiece(final Color color) {
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
