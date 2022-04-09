package chess.board.boardGenerator;

import chess.domain.board.strategy.BoardGenerationStrategy;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.Map;

public class TestBoardStrategy implements BoardGenerationStrategy {

    private final Map<Position, Piece> board = new HashMap<>();

    @Override
    public Map<Position, Piece> create() {
        return Map.copyOf(board);
    }

    public void put(Position position, Piece piece) {
        board.put(position, piece);
    }
}