package chess.domain.piece;

public enum PieceType {

    King("king"),
    Queen("Queen"),
    Bishop("Bishop"),
    Knight("Knight"),
    Rook("Rook"),
    Pawn("Pawn"),
    Empty("Empty");

    private final String type;

    PieceType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
