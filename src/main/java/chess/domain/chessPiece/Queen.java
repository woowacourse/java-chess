package chess.domain.chessPiece;

public class Queen implements PieceType {
    private Queen() {
    }

    private static class Singleton {
        private static final Queen instance = new Queen();
    }

    public static PieceType getInstance() {
        return Singleton.instance;
    }

    @Override
    public String getName() {
        return "Q";
    }
}
