package chess.model.board;

import chess.model.board.vector.Vector;
import chess.model.piece.Piece;

import java.util.Arrays;
import java.util.Objects;

public class Tile {
    private static final int COORDINATE_LENGTH = 2;

    private Coordinate coordinateX;
    private Coordinate coordinateY;
    private Piece piece;

    public Tile(String coordinates, Piece piece) {
        validateConstructor(coordinates);
        String firstCoordinate = coordinates.substring(0, 1);
        String secondCoordinate = coordinates.substring(1, 2);

        this.coordinateX = Coordinate.valueOf(Integer.parseInt(firstCoordinate));
        this.coordinateY = Coordinate.valueOf(Integer.parseInt(secondCoordinate));
        this.piece = piece;
    }

    private void validateConstructor(String coordinates) {
        if (Objects.isNull(coordinates)) {
            throw new NullPointerException();
        }
        if (coordinates.length() != COORDINATE_LENGTH) {
            throw new IllegalArgumentException("좌표를 벗어났습니다");
        }
    }

    public boolean isPiecePresent() {
        return !Objects.isNull(piece);
    }

    public Route findRouteFromPiece(Vector vector) {
        return piece.produceRoute(Arrays.asList(coordinateX, coordinateY), vector);
    }

    public String askPieceWhichTeam() {
        return piece.askTeamColor();
    }

    public Piece clonePiece() {
        return piece.cloneSelf();
    }

    public boolean askPieceIfPawn() {
        return piece.isPawn();

    }

    public boolean askIfKing() {
        return piece.isKing();

    }

    public Piece getPiece() {
        return piece;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile tile = (Tile) o;
        return Objects.equals(coordinateX, tile.coordinateX) &&
                Objects.equals(coordinateY, tile.coordinateY) &&
                Objects.equals(piece, tile.piece);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinateX, coordinateY, piece);
    }
}
