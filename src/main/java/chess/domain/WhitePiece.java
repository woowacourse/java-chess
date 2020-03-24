package chess.domain;

public class WhitePiece extends Piece {
    WhitePiece(PieceType pieceType) {
        super(pieceType);
    }

    @Override
    public String toString() {
        return pieceType.getName().toLowerCase();
    }
}
