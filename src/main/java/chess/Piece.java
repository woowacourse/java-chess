package chess;

public class Piece {
    private final Rank rank;
    private final File file;
    private final PieceType pieceType;

    public Piece(PieceType pieceType, Rank rank, File file) {
        this.pieceType = pieceType;
        this.rank = rank;
        this.file = file;
    }

    public boolean isSamePosition(Piece other) {
        return this.rank == other.rank && this.file == other.file;
    }

    public boolean isSameType(PieceType pieceType) {
        return this.pieceType == pieceType;
    }
}
