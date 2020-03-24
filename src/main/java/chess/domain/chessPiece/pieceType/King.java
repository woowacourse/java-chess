package chess.domain.chessPiece.pieceType;

public class King implements PieceType {

    private King() {
    }

    private static class Singleton {
        private static final King instance = new King();
    }

    public static PieceType getInstance() {
        return Singleton.instance;
    }

    @Override
    public String getName() {
        return "K";
    }

}
