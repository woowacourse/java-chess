package domain;

import domain.piece.Piece;
import domain.position.Position;

import java.util.HashMap;
import java.util.Map;

public class Board {
    private final Map<Position, Piece> board = new HashMap<>();

    public Board() {
        Position.all()
                .stream()
                .forEach(a -> board.put(a, null));
    }
}
