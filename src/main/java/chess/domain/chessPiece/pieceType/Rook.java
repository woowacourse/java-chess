package chess.domain.chessPiece.pieceType;

public class Rook implements PieceType {
    private Rook() {
    }

    private static class Singleton {
        private static final Rook instance = new Rook();
    }

    public static Rook getInstance() {
        return Singleton.instance;
    }

    @Override
    public String getName() {
        return "R";
    }
}
