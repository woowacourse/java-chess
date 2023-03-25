package chessgame.service;

import chessgame.domain.Board;
import chessgame.domain.ChessBoardFactory;
import chessgame.domain.Game;
import chessgame.dao.GameDao;

import java.sql.SQLException;

public class ChessService {
    private final GameDao gameDao = new GameDao();

    public Game setGame(String gameName) throws SQLException {
        Game readGame = gameDao.read(gameName);
        if (readGame == null) {
            return new Game(new Board(ChessBoardFactory.create()), gameName);
        }
        readGame.setDbState(gameDao.findTurnByGame(gameName));
        return readGame;
    }

    public void save(Game game) throws SQLException{
        removeGame(game);
        saveGame(game);
    }

    private void removeGame(Game game) {
        gameDao.remove(game.getName());
    }

    private void saveGame(Game game) {
        gameDao.save(game.board(),game.getName(),game.getTurn());
    }
}
