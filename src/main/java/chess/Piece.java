package chess;

public class Piece {
    Rank rank;
    File file;

    public Piece(Rank rank, File file) {
        this.rank = rank;
        this.file = file;
    }

    public boolean isSamePosition(Piece other) {
        return this.rank == other.rank && this.file == other.file;
    }
}
