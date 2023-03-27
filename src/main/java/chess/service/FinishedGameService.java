package chess.service;

import chess.dao.FinishedGameDao;
import chess.dto.FinishedGameDto;

public class FinishedGameService {

    private final FinishedGameDao finishedGameDao = new FinishedGameDao();

    public void create(final String turn) {
        final FinishedGameDto finishedGameDto = new FinishedGameDto(turn);
        finishedGameDao.create(finishedGameDto);
    }
}
