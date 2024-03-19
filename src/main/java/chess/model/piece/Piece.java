package chess.model.piece;

public abstract class Piece {

    private final PieceType type;

    public Piece(PieceType pieceType) {
        this.type = pieceType;
    }

    @Override
    public String toString() {
        return type.getDisplayName();
    }
}
