package chess.domain.chessPiece;

public class Knight implements ChessPiece {

    private static final String NAME = "N";
    private static final Knight INSTANCE = new Knight();

    private Knight() {
    }

    public static Knight getInstance() {
        return INSTANCE;
    }

}
