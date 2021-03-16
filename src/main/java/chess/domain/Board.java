package chess.domain;

import chess.domain.piece.Piece;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Board {

    private final Map<Position, Piece> coordinates;

    public Board(Map<Position, Piece> coordinates) {
        this.coordinates = coordinates;
    }

    public Board() {
        this.coordinates = new HashMap<>();
    }


    public Map<Position, Piece> getCoordinates() {
        return coordinates;
    }

    public Optional<Piece> findPieceBy(Position position) {
        return Optional.ofNullable(coordinates.get(position));
    }
}
