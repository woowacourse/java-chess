package service;

import dao.GameDao;
import dao.GameDto;
import domain.game.Game;
import java.util.List;

public class ChessService {
    private final GameDao gameDao;

    public ChessService() {
        this.gameDao = new GameDao();
    }

    public void saveChessBoard(Game game) {
        gameDao.saveChessBoard(game);
    }

    public void create(Game game) {
        gameDao.create(game);
    }

    public List<GameDto> findGamesByUserName(String userName) {
        return this.gameDao.findGamesByUserName(userName);
    }

    public Game findGameById(String gameId) {
        return this.gameDao.findGameById(gameId);
    }
}
