package chess.domain.piece;

public class EmptyPiece implements Piece {

    private static final EmptyPiece EMPTY_PIECE = new EmptyPiece();

    private EmptyPiece() {
    }

    public static EmptyPiece getInstance() {
        return EMPTY_PIECE;
    }

    @Override
    public boolean isColor(PieceColor color) {
        return false;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }
}
