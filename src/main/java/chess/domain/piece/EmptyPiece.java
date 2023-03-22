package chess.domain.piece;

public class EmptyPiece extends Piece {


    public EmptyPiece() {
        super(PieceType.EMPTY);
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public boolean isBlack() {
        return false;
    }

    @Override
    public boolean isWhite() {
        return false;
    }

}
