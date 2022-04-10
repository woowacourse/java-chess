package chess.domain.dao;

import chess.dto.GameDto;
import chess.domain.entity.Game;

public interface GameDao {

    Long save(GameDto gameDto);

    Game findGameById(Long id);

    void updateByGame(GameDto of);

    Game findGameByMaxId();
}
