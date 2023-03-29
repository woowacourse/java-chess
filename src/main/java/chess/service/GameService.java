package chess.service;

import chess.dao.GameDao;
import chess.domain.board.Turn;
import chess.dto.GameDto;
import java.util.List;

public class GameService {

    private final GameDao gameDao = new GameDao();

    public void create() {
        final GameDto gameDto = GameDto.create();
        gameDao.create(gameDto);
    }

    public List<Integer> findAllIds() {
        return gameDao.findAllIds();
    }

    public Turn findTurnById(final int runningGameId) {
        return gameDao.findTurnById(runningGameId);
    }

    public void update(final String turn) {
        final GameDto gameDto = GameDto.from(turn);
        gameDao.update(gameDto);
    }

    public void delete(final int runningGameId) {
        gameDao.delete(runningGameId);
    }
}
