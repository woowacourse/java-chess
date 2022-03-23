package chess.domain.piece;

public class RookPiece extends FullPiece{

    private static final String WHITE_NAME = "r";
    private static final String BLACK_NAME = "R";

    private final String name;

    public RookPiece(final Color color) {
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
