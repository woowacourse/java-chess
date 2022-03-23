package chess.domain.piece;

public class EmptyPiece extends Piece{

    private static final EmptyPiece emptyPiece = new EmptyPiece();

    private EmptyPiece() {
        super(Color.EMPTY, PieceName.EMPTY);
    }

    public static EmptyPiece getInstance() {
        return emptyPiece;
    }
}
