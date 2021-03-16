package chess.domain;

import chess.domain.piece.Piece;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class Board {

    private final Map<Position, Piece> coordinates;

    public Board(Map<Position, Piece> coordinates) {
        this.coordinates = coordinates;
    }

    public Board() {
        this.coordinates = new LinkedHashMap<>();
    }

    public void putPiece(Position position, Piece piece) {
        coordinates.put(position, piece);
    }

    public Map<Position, Piece> getCoordinates() {
        return coordinates;
    }

    public Optional<Piece> findPieceBy(Position position) {
        return Optional.ofNullable(coordinates.get(position));
    }
}
