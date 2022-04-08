package chess.dao;

import chess.util.JdbcTestFixture;
import java.util.Map;

public class FakeBoardDao implements BoardDao {

    public Map<String, String> fakeBoard;

    public FakeBoardDao() {
        fakeBoard = JdbcTestFixture.getMovedTestBoard();
    }

    @Override
    public Map<String, String> getBoard() {
        return fakeBoard;
    }

    @Override
    public void updatePosition(final String position, final String piece) {
        fakeBoard.put(position, piece);
    }

    @Override
    public void updateBatchPositions(final Map<String, String> board) {
        fakeBoard = board;
    }
}
