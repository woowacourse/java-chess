package chess.dao.fake;

import chess.dao.BoardDao;
import chess.domain.board.BasicBoardStrategy;
import java.util.HashMap;
import java.util.Map;

public class FakeBoardDao implements BoardDao {

    private static Map<String, String> board = new HashMap<>();

    @Override
    public void update(String position, String piece) {
        board.put(position, piece);
    }

    @Override
    public Map<String, String> getBoard() {
        return Map.copyOf(board);
    }

    @Override
    public void removeAll(String name) {
        board.clear();
        board.putAll(BasicBoardStrategy.toMap(new BasicBoardStrategy().create()));
    }
}
