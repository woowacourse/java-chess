package chess.model;

import chess.model.piece.Piece;

import java.util.Objects;
import java.util.Optional;

public class Tile {
    private static final int COORDINATE_LENGTH = 2;
    // TODO: 2019-06-18 인스턴스 변수 줄이기
    private Coordinate xCoordinate;
    private Coordinate yCoordinate;
    private Optional<Piece> piece;

    public Tile(String coordinates, Optional<Piece> piece) {
        validateInput(coordinates);
        String firstCoordinate = coordinates.substring(0, 1);
        String secondCoordinate = coordinates.substring(1, 2);

        this.xCoordinate = Coordinate.valueOf(Integer.parseInt(firstCoordinate));
        this.yCoordinate = Coordinate.valueOf(Integer.parseInt(secondCoordinate));
        this.piece = piece;
    }

    private void validateInput(String coordinates) {
        if (Objects.isNull(coordinates)) {
            throw new NullPointerException();
        }
        if (coordinates.length() != COORDINATE_LENGTH) {
            throw new IllegalArgumentException("좌표를 벗어났습니다");
        }
    }

    public boolean isPiecePresent() {
        return piece.isPresent();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile tile = (Tile) o;
        return Objects.equals(xCoordinate, tile.xCoordinate) &&
                Objects.equals(yCoordinate, tile.yCoordinate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(xCoordinate, yCoordinate);
    }
}
