package chess.dao;

import java.util.List;

public interface ChessGameDao {

    List<Long> findAllId();

    Long generateNewGame();
}
