package chess.domain;

import chess.domain.piece.Piece;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class Board {

    private final Map<Piece, Position> coordinates;

    public Board(Map<Piece, Position> coordinates) {
        this.coordinates = coordinates;
    }

    public Board() {
        this.coordinates = new LinkedHashMap<>();
    }

    public void putPiece(Piece piece, Position position) {
        coordinates.put(piece, position);
    }

    public Map<Piece, Position> getCoordinates() {
        return coordinates;
    }

    public Optional<Piece> findPieceBy(Position position) {
        return coordinates.entrySet()
                .stream()
                .filter(entry -> Objects.equals(entry.getValue(), position))
                .map(Map.Entry::getKey)
                .findFirst()
                ;
    }
}
