package chess.dao;

import chess.domain.game.Game;
import java.util.Optional;

public interface GameDaoInterface {

    long insert(Game game);

    Optional<Game> selectById(long id);

    void update(Game game);

    void deleteById(long id);
}
