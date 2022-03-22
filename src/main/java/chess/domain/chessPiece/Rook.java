package chess.domain.chessPiece;

public class Rook implements ChessPiece {

    private static final String NAME = "R";
    private static final Rook INSTANCE = new Rook();

    private Rook() {
    }

    public static Rook getInstance() {
        return INSTANCE;
    }

}
