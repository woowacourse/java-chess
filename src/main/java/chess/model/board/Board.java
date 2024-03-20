package chess.model.board;

import chess.model.piece.Piece;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    private static final List<Position> ALL_POSITIONS = Position.values();

    private final Map<Position, Piece> squares;

    public Board(Map<Position, Piece> squares) {
        this.squares = new HashMap<>(squares);
        ALL_POSITIONS.forEach(position -> this.squares.putIfAbsent(position, Piece.EMPTY));
    }

    public List<String> getSignatures() {
        return squares.values().stream()
                .map(Piece::getSignature)
                .toList();
    }
}
