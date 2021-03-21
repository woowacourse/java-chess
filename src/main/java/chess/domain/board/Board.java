package chess.domain.board;

import chess.domain.board.position.Position;
import chess.domain.piece.Piece;

import java.util.List;
import java.util.Map;

public class Board {

    private final List<Map<Position, Piece>> squares;

    public Board(final List<Map<Position, Piece>> squares) {
        this.squares = squares;
    }

    public Piece pieceOfPosition(Position position) {
        return squares.stream()
                .filter(map -> map.containsKey(position))
                .map(map -> map.get(position))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public List<Map<Position, Piece>> getSquares() {
        return squares;
    }
}
