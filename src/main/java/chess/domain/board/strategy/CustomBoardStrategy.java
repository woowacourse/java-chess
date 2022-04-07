package chess.domain.board.strategy;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.Map;

public class CustomBoardStrategy implements BoardGenerationStrategy {

    private final Map<Position, Piece> board = new HashMap<>();

    @Override
    public Map<Position, Piece> create() {
        return Map.copyOf(board);
    }

    public void put(Map<Position, Piece> data) {
        board.putAll(data);
    }
}