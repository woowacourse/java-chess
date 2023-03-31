package chess.model.dao;

import chess.model.domain.board.Turn;
import java.util.List;

public interface ChessGameDao {

    List<Long> findAllId();

    long generateNewGame();

    void updateTurn(final Turn turn, final long gameId);

    Turn loadTurn(long gameId);

    void deleteGame(long gameId);
}
