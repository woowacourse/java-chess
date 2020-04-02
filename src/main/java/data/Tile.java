package data;

import chess.location.Location;
import chess.piece.type.Piece;

public class Tile {
    private final Location location;
    private final Piece piece;

    public Tile(Location location, Piece piece) {
        this.location = location;
        this.piece = piece;
    }

    public boolean is(Location location) {
        return this.location == location;
    }

    public Location getLocation() {
        return location;
    }

    public Piece getPiece() {
        return piece;
    }
}
