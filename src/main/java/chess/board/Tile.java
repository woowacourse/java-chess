package chess.board;

import chess.coordinate.Coordinate;
import chess.coordinate.Vector;
import chess.piece.AbstractPawn;
import chess.piece.Piece;
import chess.piece.Pieces;
import chess.piece.Team;

public class Tile {
    private final Coordinate coordinate;
    private Piece piece;

    public Tile(final Coordinate coordinate, final Piece piece) {
        this.coordinate = coordinate;
        this.piece = piece;
    }

    public Piece replacePiece(Tile sourceTile) {
        Piece removedPiece = this.piece;
        this.piece = sourceTile.piece.move();
        sourceTile.piece = Pieces.BLANK.getPiece();
        return removedPiece;
    }

    public boolean canNotReach(final Tile targetTile) {
        Vector vector = targetTile.coordinate.calculateVector(this.coordinate);
        return !this.piece.canMove(vector, targetTile.piece);
    }

    public Directions findPath(final Tile targetTile) {
        Vector vector = targetTile.coordinate.calculateVector(this.coordinate);
        return new Directions(this.piece.findPath(vector));
    }

    public boolean isBlank() {
        return piece.isBlank();
    }

    public double getScore() {
        return piece.getScore();
    }

    public boolean isPawn() {
        return this.piece instanceof AbstractPawn;
    }

    public boolean isNotSameTeam(final Team currentTeam) {
        return !this.piece.isSameTeam(currentTeam);
    }

    public Piece getPiece() {
        return piece;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    @Override
    public String toString() {
        return "Tile{" +
                "coordinate=" + coordinate +
                ", piece=" + piece +
                '}';
    }
}
