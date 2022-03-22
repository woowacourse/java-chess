package chess.domain.chessPiece;

public class Queen implements ChessPiece {

    private static final String NAME = "Q";
    private static final Queen INSTANCE = new Queen();

    private Queen() {
    }

    public static Queen getInstance() {
        return INSTANCE;
    }
}
