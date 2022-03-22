package chess.domain.chessPiece;

public class King implements ChessPiece {

    private static final String NAME = "K";
    private static final King INSTANCE = new King();

    private King() {
    }

    public static King getInstance() {
        return INSTANCE;
    }

}
