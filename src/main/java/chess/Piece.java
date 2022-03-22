package chess;

public class Piece {
    private final PieceType pieceType;
    private final Position position;

    public Piece(PieceType pieceType, Rank rank, File file) {
        this.pieceType = pieceType;
        this.position = new Position(rank, file);
    }

    public Piece(PieceType pieceType, Position position) {
        this.pieceType = pieceType;
        this.position = position;
    }

    public boolean isSamePosition(Piece other) {
        return this.position == other.position;
    }

    public boolean isSameType(PieceType pieceType) {
        return this.pieceType == pieceType;
    }

    public PieceType getPieceType() {
        return pieceType;
    }
}
