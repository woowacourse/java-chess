package chess.service;

import chess.dao.GameDao;
import chess.domain.board.Turn;
import chess.dto.GameDto;
import java.util.List;

public class GameService {

    private final GameDao gameDao = new GameDao();

    public int create() {
        final GameDto gameDto = GameDto.create();
        return gameDao.create(gameDto);
    }

    public List<Integer> findAllIdsByIsRunning(final boolean isRunning) {
        final GameDto gameDto = GameDto.from(isRunning);
        return gameDao.findAllIdsByIsRunning(gameDto);
    }

    public Turn findTurnById(final int id) {
        final GameDto gameDto = GameDto.from(id);
        return gameDao.findTurnById(gameDto);
    }

    public void updateTurn(final int id, final String turn) {
        final GameDto gameDto = GameDto.of(id, turn);
        gameDao.updateTurn(gameDto);
    }

    public void updateIsRunning(final int id, final boolean isRunning) {
        final GameDto gameDto = GameDto.of(id, isRunning);
        gameDao.updateIsRunning(gameDto);
    }
}
