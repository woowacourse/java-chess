package chess.domain.board;

import chess.domain.piece.Piece;

public class Cell {
    private final Coordinate coordinate;
    private Piece piece;

    public Cell(Coordinate coordinate) {
        this.coordinate = coordinate;
        this.piece = null;
    }

    public boolean isEmpty() {
        return piece == null;
    }

    public void put(Piece piece) {
        this.piece = piece;
    }

    public Piece getPiece() {
        return piece;
    }
}
