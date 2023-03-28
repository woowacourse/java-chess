package database;

import chess.dao.GameDao;
import chess.domain.Color;
import chess.dto.GameDto;

public class FakeGameJdbcDao implements GameDao {
    @Override
    public GameDto create() {
        return GameDto.of(2);
    }

    @Override
    public GameDto findByLastGame() {
        return GameDto.from(1, true, Color.WHITE.name());
    }

    @Override
    public void update(final boolean status, final int gameId) {

    }

    @Override
    public void update(final Color color, final int gameId) {

    }
}
