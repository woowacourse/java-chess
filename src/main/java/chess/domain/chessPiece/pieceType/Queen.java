package chess.domain.chessPiece.pieceType;

public class Queen implements PieceType {
    private Queen() {
    }

    private static class Singleton {
        private static final Queen instance = new Queen();
    }

    public static Queen getInstance() {
        return Singleton.instance;
    }

    @Override
    public String getName() {
        return "Q";
    }
}
