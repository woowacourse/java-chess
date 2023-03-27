package chess.service;

import chess.dao.RunningGameDao;
import chess.dto.RunningGameDto;

public class RunningGameService {

    private final RunningGameDao runningGameDao = new RunningGameDao();

    public void save(final String turn) {
        final RunningGameDto runningGameDto = new RunningGameDto(turn);
        runningGameDao.save(runningGameDto);
    }
}
