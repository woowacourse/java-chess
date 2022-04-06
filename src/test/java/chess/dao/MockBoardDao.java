package chess.dao;

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
    public Color findById(final int id) {
        final String value = board.get(id);
        return Color.valueOf(value);
    }
}
