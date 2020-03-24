package chess.domain;

public class BlackPiece extends Piece {
    BlackPiece(PieceType pieceType) {
        super(pieceType);
    }

    @Override
    public String toString() {
        return pieceType.getName().toUpperCase();
    }
}
