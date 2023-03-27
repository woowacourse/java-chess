package chess.service;

import chess.dao.RunningGameDao;
import chess.domain.board.Turn;
import chess.dto.RunningGameDto;
import java.util.List;

public class RunningGameService {

    private final RunningGameDao runningGameDao = new RunningGameDao();

    public void create(final String turn) {
        final RunningGameDto runningGameDto = new RunningGameDto(turn);
        runningGameDao.create(runningGameDto);
    }

    public List<Integer> findAllIds() {
        return runningGameDao.findAllIds();
    }

    public Turn findTurnById(int runningGameId) {
        return runningGameDao.findTurnById(runningGameId);
    }

    public void update(final String turn) {
        final RunningGameDto runningGameDto = new RunningGameDto(turn);
        runningGameDao.update(runningGameDto);
    }
}
