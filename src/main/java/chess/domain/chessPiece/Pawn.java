package chess.domain.chessPiece;

public class Pawn implements PieceType {
    private Pawn() {
    }

    private static class Singleton {
        private static final Pawn instance = new Pawn();
    }

    public static PieceType getInstance() {
        return Singleton.instance;
    }

    @Override
    public String getName() {
        return "P";
    }
}
