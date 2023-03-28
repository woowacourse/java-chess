package chess.dao;

import chess.domain.Color;
import chess.dto.GameDto;

public interface GameDao {
    GameDto create();

    GameDto findByLastGame();

    void update(final boolean status, final int gameId);

    void update(final Color color, final int gameId);
}
