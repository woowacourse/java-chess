package chess.dao;

import chess.entity.Game;
import java.util.Optional;

public interface GameDaoInterface {

    long insert(Game game);

    Optional<Game> selectById(Long id);
}
