package chess.service;

import chess.dao.GameDaoInterface;
import chess.domain.game.Game;
import chess.domain.team.Team;
import chess.dto.game.GamePutRequestDto;
import chess.service.exception.DataNotFoundException;
import java.time.LocalDateTime;

public class GameService {

    private final GameDaoInterface gameDao;

    public GameService(final GameDaoInterface gameDao) {
        this.gameDao = gameDao;
    }

    public long add(final long whiteId, final long blackId) {
        return gameDao.insert(new Game(
            0L,
            whiteId,
            blackId,
            Team.WHITE,
            false,
            LocalDateTime.now()
        ));
    }

    public void update(final GamePutRequestDto gamePutRequestDto) {
        gameDao.update(
            new Game(
                gamePutRequestDto.getId(),
                0L,
                0L,
                Team.from(gamePutRequestDto.getTurn()),
                gamePutRequestDto.isFinished(),
                LocalDateTime.now()
            )
        );
    }

    public Game find(final long id) {
        return gameDao.selectById(id)
            .orElseThrow(() -> new DataNotFoundException(Game.class));
    }
}
