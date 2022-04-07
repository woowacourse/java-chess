package chess.dao.fixture;

import chess.dao.BoardDao;
import chess.piece.detail.Color;
import java.util.HashMap;
import java.util.Map;

public class MockBoardDao implements BoardDao {

    private final Map<Integer, String> board = new HashMap<>();

    @Override
    public void save(final Color color) {
        board.put(1, color.name());
    }

    @Override
    public Color findTurnById(final int id) {
        return Color.valueOf(board.get(id));
    }

    @Override
    public Color load() {
        System.out.println(board);
        return Color.valueOf(board.get(1));
    }

    @Override
    public int findLastlyUsedBoard() {
        return board.keySet()
                .stream()
                .findFirst()
                .get();
    }

    @Override
    public void updateById(final int boardId, final Color turn) {
        board.replace(boardId, turn.name());
    }

    @Override
    public void deleteById(final int boardId) {
        board.remove(boardId);
    }

    public Map<Integer, String> getBoard() {
        return board;
    }
}
