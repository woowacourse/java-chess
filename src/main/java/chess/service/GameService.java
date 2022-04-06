package chess.service;

import chess.dao.GameDao;
import chess.domain.piece.Team;
import chess.dto.GameDto;

public class GameService {

    private final GameDao gameDao;

    public GameService(GameDao gameDao) {
        this.gameDao = gameDao;
    }

    public void save(String whiteUserName, String blackUserName, String state) {
        GameDto gameDto = new GameDto(whiteUserName, blackUserName, state);
        gameDao.save(gameDto);
    }

    public int findGameId(String whiteUserName, String blackUserName) {
        return gameDao.findGameIdByUserName(whiteUserName, blackUserName);
    }

    public GameDto findById(int gameId) {
        return gameDao.findById(gameId);
    }

    public void changeTurn(String state, int gameId) {
        if (state.equals(Team.WHITE.name())) {
            gameDao.update(Team.BLACK.name(), gameId);
            return;
        }
        gameDao.update(Team.WHITE.name(), gameId);
    }

    public void delete(int gameId) {
        gameDao.deleteById(gameId);
    }
}
