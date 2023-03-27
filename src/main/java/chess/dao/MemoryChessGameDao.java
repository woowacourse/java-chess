package chess.dao;

import chess.domain.board.Turn;
import java.util.List;

public class MemoryChessGameDao implements ChessGameDao {

    @Override
    public List<Long> findAllId() {
        return null;
    }

    @Override
    public long generateNewGame() {
        return 0;
    }

    @Override
    public void updateTurn(final Turn turn, final long gameId) {

    }

    @Override
    public Turn loadTurn(final long gameId) {
        return null;
    }

    @Override
    public void deleteGame(final long gameId) {

    }
}
