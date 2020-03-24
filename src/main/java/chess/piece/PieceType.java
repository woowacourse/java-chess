package chess.piece;

import chess.board.Location;
import chess.piece.location.strategy.*;

import java.util.List;

public enum PieceType {
    PAWN("p", 8, new PawnLocationStrategy()),
    KING("k", 1, new KingLocationStrategy()),
    QUEEN("q", 1, new QueenLocationsStrategy()),
    ROOK("r", 2, new RookLocationsStrategy()),
    BISHOP("b", 2, new BishopLocationStrategy()),
    KNIGHT("k", 2, new KnightLocationStrategy());

    private final String value;
    private final int size;
    private final LocationStrategy locationStrategy;

    PieceType(String value, int size, LocationStrategy locationStrategy) {
        this.value = value;
        this.size = size;
        this.locationStrategy = locationStrategy;
    }

    public List<Location> getInitialLocation() {
        return locationStrategy.getInitialLocation();
    }

    public List<Location> reverseInitialLocation() {
        return locationStrategy.reverseInitialLocation();
    }

    public String getValue() {
        return value;
    }

    public int getSize() {
        return size;
    }
}
