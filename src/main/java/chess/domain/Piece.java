package chess.domain;

public abstract class Piece {
    protected PieceType pieceType;

    Piece(PieceType pieceType) {
        this.pieceType = pieceType;
    }
}
