package chess.board;

import chess.board.piece.Piece;

import java.util.Objects;

public class Tile {
    private final Coordinate coordinate;
    private final Piece piece;

    public Tile(final Coordinate coordinate, final Piece piece) {
        this.coordinate = coordinate;
        this.piece = piece;
    }

    public boolean hasPiece() {
        return !Objects.isNull(piece);
    }


    @Override
    public String toString() {
        return "Tile{" +
                "piece=" + piece +
                '}';
    }
}
