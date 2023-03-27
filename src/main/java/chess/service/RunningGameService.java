package chess.service;

import chess.dao.RunningGameDao;
import chess.domain.board.Turn;
import chess.dto.RunningGameDto;
import java.util.List;

public class RunningGameService {

    private final RunningGameDao runningGameDao = new RunningGameDao();

    public void save(final String turn) {
        final RunningGameDto runningGameDto = new RunningGameDto(turn);
        runningGameDao.save(runningGameDto);
    }

    public List<Integer> findAllIds() {
        return runningGameDao.findAllIds();
    }

    public Turn findTurnById(int runningGameId) {
        return runningGameDao.findTurnById(runningGameId);
    }
}
