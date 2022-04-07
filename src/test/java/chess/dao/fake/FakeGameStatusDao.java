package chess.dao.fake;

import chess.dao.GameStatusDao;
import chess.domain.GameStatus;

public class FakeGameStatusDao implements GameStatusDao {

    private GameStatus status = GameStatus.READY;

    @Override
    public void update(String nowStatus, String nextStatus) {
        status = GameStatus.valueOf(nextStatus);
    }

    @Override
    public String getStatus() {
        return status.name();
    }

    @Override
    public void reset() {
        status = GameStatus.READY;
    }
}
