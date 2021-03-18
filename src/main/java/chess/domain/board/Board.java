package chess.domain.board;

import chess.domain.Team;
import chess.domain.pieces.Piece;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    private final Map<Team, List<Piece>> board;

    public Board(final Map<Team, List<Piece>> board) {
        this.board = new HashMap<>(board);
    }

    public Map<Team, List<Piece>> toMap() {
        return new HashMap<>(board);
    }
}
