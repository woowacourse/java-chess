package chess.service;

import chess.dao.BoardDao;
import chess.dao.ChessGameDao;
import chess.dao.DBConnector;
import chess.domain.ChessGame;

public class DaoService {
    private static final int BOARD_ID = 1;

    private final ChessGameDao chessGameDao;
    private final BoardDao boardDao;

    public DaoService() {
        this(new ChessGameDao(), new BoardDao());
    }

    public DaoService(ChessGameDao chessGameDao, BoardDao boardDao) {
        this.chessGameDao = chessGameDao;
        this.boardDao = boardDao;
    }

    public void save(ChessGame game) {
        chessGameDao.remove(BOARD_ID, DBConnector.getConnection());
        chessGameDao.save(game, BOARD_ID, DBConnector.getConnection());
        boardDao.save(game.getBoard(), BOARD_ID, DBConnector.getConnection());
    }

    public void update(ChessGame game) {
        chessGameDao.update(game, BOARD_ID, DBConnector.getConnection());
        boardDao.remove(BOARD_ID, DBConnector.getConnection());
        boardDao.save(game.getBoard(), BOARD_ID, DBConnector.getConnection());
    }

    public void remove() {
        chessGameDao.remove(BOARD_ID, DBConnector.getConnection());
    }

    public ChessGame find() {
        return chessGameDao.find(BOARD_ID, DBConnector.getConnection());
    }
}
