package data;

import chess.location.Location;
import chess.piece.type.Piece;

public class PieceVO {
    private String location;
    private String pieceName;

    public PieceVO(Location location, Piece piece) {
        this.location = location.toString();
        this.pieceName = String.valueOf(piece.getName());
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "PieceVO{" +
                "location='" + location +
                ", pieceName='" + pieceName +
                '}';
    }
}
