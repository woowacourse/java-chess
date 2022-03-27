package chess.domain.board.boardGenerator;

import chess.domain.board.BoardGenerator;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.Map;

public class TestBoardGenerator implements BoardGenerator {

    private final Map<Position, Piece> board = new HashMap<>();

    @Override
    public Map<Position, Piece> create() {
        return Map.copyOf(board);
    }

    public void put(Position position, Piece piece) {
        board.put(position, piece);
    }
}