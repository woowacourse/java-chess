package chess.domain.chessPiece.pieceType;

public class Knight implements PieceType {

    private Knight() {
    }

    private static class Singleton {
        private static final Knight instance = new Knight();
    }

    public static PieceType getInstance() {
        return Singleton.instance;
    }

    @Override
    public String getName() {
        return "N";
    }
}
