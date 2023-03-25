package chessgame.service;

import chessgame.dao.ConnectionGenerator;
import chessgame.dao.GameDao;
import chessgame.domain.Board;
import chessgame.domain.ChessBoardFactory;
import chessgame.domain.Game;

import java.sql.Connection;
import java.sql.SQLException;

public class ChessService {
    private final Connection connection;
    private final GameDao gameDao = new GameDao();

    public ChessService() {
        this.connection = ConnectionGenerator.getConnection();
    }

    public Game setGame(String gameName) throws SQLException {
        Game readGame = gameDao.read(gameName, connection);
        if (readGame == null) {
            return new Game(new Board(ChessBoardFactory.create()), gameName);
        }
        readGame.setDbState(gameDao.findTurnByGame(gameName, connection));
        return readGame;
    }

    public void removeGame(Game game) {
        gameDao.remove(game.getName(), connection);
    }

    public void saveGame(Game game) {
        gameDao.save(game.board(), game.getName(), game.getTurn(), connection);
    }
}
