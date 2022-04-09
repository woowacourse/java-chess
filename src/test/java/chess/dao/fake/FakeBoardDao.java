package chess.dao.fake;

import chess.dao.BoardDao;
import chess.domain.board.Board;
import chess.domain.board.strategy.BoardGenerationStrategy;
import java.util.HashMap;
import java.util.Map;

public class FakeBoardDao implements BoardDao {

    private static Map<String, String> board = new HashMap<>();

    public FakeBoardDao(BoardGenerationStrategy strategy) {
        Board data = new Board();
        data.initBoard(strategy);
        board.putAll(data.toMap());
    }

    @Override
    public void update(String position, String piece) {
        board.put(position, piece);
    }

    @Override
    public Map<String, String> getBoard() {
        return Map.copyOf(board);
    }

    @Override
    public void reset(Map<String, String> board) {
        this.board.clear();
        this.board.putAll(board);
    }
}
