package chess.domain;

class Blank extends Piece {
    Blank(PieceType pieceType) {
        super(pieceType);
    }

    @Override
    public String toString() {
        return pieceType.getName();
    }
}
