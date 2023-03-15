package chess.domain;

public class Square {

    private final FileCoordinate fileCoordinate;
    private final Piece piece;

    public Square(FileCoordinate coordinate, Piece piece) {
        this.fileCoordinate = coordinate;
        this.piece = piece;
    }

    public Piece getPiece() {
        return piece;
    }

    public FileCoordinate getFileCoordinate() {
        return fileCoordinate;
    }

    public boolean isSameWith(FileCoordinate coordinate) {
        return this.fileCoordinate == coordinate;
    }
}
