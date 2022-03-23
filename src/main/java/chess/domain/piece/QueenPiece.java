package chess.domain.piece;

public class QueenPiece extends FullPiece {

    private static final String WHITE_NAME = "q";
    private static final String BLACK_NAME = "Q";

    private final String name;

    public QueenPiece(final Color color) {
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
