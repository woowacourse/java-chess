package chessgame.service;

import chessgame.dao.ChessGameDao;
import chessgame.domain.Board;
import chessgame.domain.ChessBoardFactory;
import chessgame.domain.Game;

public class ChessService {
    private final ChessGameDao chessGameDao = new ChessGameDao();

    public ChessService() {
    }

    public Game setGame(String gameName) {
        Game readGame = chessGameDao.read(gameName);
        if (readGame == null) {
            return new Game(new Board(ChessBoardFactory.create()), gameName);
        }
        readGame.setTeamState(chessGameDao.findTurnByGame(gameName));
        return readGame;
    }

    public void removeGame(Game game) {
        chessGameDao.remove(game.getName());
    }

    public void saveGame(Game game) {
        chessGameDao.save(game.board(), game.getName(), game.getTurn());
    }
}
