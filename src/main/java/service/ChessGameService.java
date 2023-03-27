package service;

import dao.ChessGameDao;
import domain.board.ChessGame;

public class ChessGameService {

    private final ChessGameDao chessGameDao;

    public ChessGameService(final ChessGameDao chessGameDao) {
        this.chessGameDao = chessGameDao;
    }

    public ChessGame startGame() {
        chessGameDao.delete();
        chessGameDao.insert(new ChessGame());
        return loadGame();
    }

    public ChessGame loadGame() {
        return chessGameDao.read();
    }

    public void updateGame(final ChessGame chessGame) {
        chessGameDao.delete();
        chessGameDao.insert(chessGame);
    }
}
