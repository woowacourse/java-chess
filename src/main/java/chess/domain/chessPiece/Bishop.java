package chess.domain.chessPiece;

public class Bishop implements ChessPiece {

    private static final String NAME = "B";
    private static final Bishop INSTANCE = new Bishop();

    private Bishop() {
    }

    public static Bishop getInstance() {
        return INSTANCE;
    }
}
