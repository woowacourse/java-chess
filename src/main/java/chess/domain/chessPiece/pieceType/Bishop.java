package chess.domain.chessPiece.pieceType;

public class Bishop implements PieceType {

    private Bishop() {
    }

    private static class Singleton {
        private static final Bishop instance = new Bishop();
    }

    public static Bishop getInstance() {
        return Singleton.instance;
    }

    @Override
    public String getName() {
        return "B";
    }
}
