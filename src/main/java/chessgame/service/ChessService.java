package chessgame.service;

import chessgame.domain.Board;
import chessgame.domain.ChessBoardFactory;
import chessgame.domain.Game;
import dao.BoardDao;

import java.sql.SQLException;

public class ChessService {
    private final BoardDao boardDao = new BoardDao();

    public Game setGame(String gameName) throws SQLException {
        Game readGame = boardDao.read(gameName);
        if (readGame == null) {
            return new Game(new Board(ChessBoardFactory.create()), gameName);
        }
        readGame.setDbState(boardDao.findTurnByGame(gameName));
        return readGame;
    }

    public void save(Game game) throws SQLException{
        removeGame(game);
        saveGame(game);
    }

    private void removeGame(Game game) {
        boardDao.remove(game.getName());
    }

    private void saveGame(Game game) {
        boardDao.save(game.board(),game.getName(),game.getTurn());
    }
}
