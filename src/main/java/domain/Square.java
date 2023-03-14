package domain;

public class Square {

    private final Piece piece;

    public Square(int row, int col) {
        this.piece = BoardInitialImage.getPieceByCoordinate(row, col);
    }

    public Piece getPiece() {
        return piece;
    }
}
