package chess.domain.chessPiece;

public class Pawn implements ChessPiece {

    private static final String NAME = "P";
    private static final Pawn INSTANCE = new Pawn();

    private Pawn() {
    }

    public static Pawn getInstance() {
        return INSTANCE;
    }

}
