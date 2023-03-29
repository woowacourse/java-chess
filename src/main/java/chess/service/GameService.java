package chess.service;

import chess.dao.GameDao;
import chess.domain.board.Turn;
import chess.dto.GameDto;
import java.util.List;

public class GameService {

    private final GameDao runningGameDao = new GameDao();

    public void create(final String turn) {
        final GameDto gameDto = new GameDto(turn, true);
        runningGameDao.create(gameDto);
    }

    public List<Integer> findAllIds() {
        return runningGameDao.findAllIds();
    }

    public Turn findTurnById(final int runningGameId) {
        return runningGameDao.findTurnById(runningGameId);
    }

    public void update(final String turn) {
        final GameDto gameDto = new GameDto(turn, true);
        runningGameDao.update(gameDto);
    }

    public void delete(final int runningGameId) {
        runningGameDao.delete(runningGameId);
    }
}
