package chess.domain.chessPiece;

public class Rook implements PieceType {
    private Rook() {
    }

    private static class Singleton {
        private static final Rook instance = new Rook();
    }

    public static PieceType getInstance() {
        return Singleton.instance;
    }

    @Override
    public String getName() {
        return "R";
    }
}
