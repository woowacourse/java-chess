package chess.service;

import chess.dao.GameDao;
import chess.dao.GameDaoImpl;
import chess.dto.GameDto;

import java.util.List;

public class GameService {
    private static GameDao gameDao = GameDaoImpl.getInstance();

    private GameService() {}

    public static List<GameDto> getNotEndGames() {
        return gameDao.findNotEndGames();
    }

    public static int addGame() {
        return gameDao.addGame();
    }

    public static GameDto findById(int gameId) {
        return gameDao.findById(gameId);
    }

    public static void updateGame(GameDto gameDto) {
        gameDao.updateGame(gameDto);
    }

}
