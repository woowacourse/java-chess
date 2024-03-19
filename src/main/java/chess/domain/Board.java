package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Board {

    private final Map<Position, Piece> board;

    public Board(Map<Position, Piece> board) {
        this.board = new HashMap<>(board);
    }

    public Optional<Piece> find(Position position) {
        Piece piece = board.get(position);
        return Optional.ofNullable(piece);
    }
}
