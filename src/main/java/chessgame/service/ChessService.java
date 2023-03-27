package chessgame.service;

import chessgame.dao.GameDao;
import chessgame.domain.Board;
import chessgame.domain.ChessBoardFactory;
import chessgame.domain.Game;

public class ChessService {
    private final GameDao gameDao = new GameDao();

    public ChessService() {
    }

    public Game setGame(String gameName) {
        Game readGame = gameDao.read(gameName);
        if (readGame == null) {
            return new Game(new Board(ChessBoardFactory.create()), gameName);
        }
        readGame.setTeamState(gameDao.findTurnByGame(gameName));
        return readGame;
    }

    public void removeGame(Game game) {
        gameDao.remove(game.getName());
    }

    public void saveGame(Game game) {
        gameDao.save(game.board(), game.getName(), game.getTurn());
    }
}
