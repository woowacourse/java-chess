package chess.service;

import java.sql.Connection;

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
        Connection connection = DBConnector.getConnection();
        chessGameDao.remove(boardDao, BOARD_ID, connection);
        chessGameDao.save(game, BOARD_ID, connection);
        boardDao.save(game.getBoard(), BOARD_ID, connection);
        DBConnector.closeConnection(connection);
    }

    public void update(ChessGame game) {
        Connection connection = DBConnector.getConnection();
        chessGameDao.update(game, BOARD_ID, connection);
        boardDao.remove(BOARD_ID, connection);
        boardDao.save(game.getBoard(), BOARD_ID, connection);
        DBConnector.closeConnection(connection);
    }

    public void remove() {
        Connection connection = DBConnector.getConnection();
        chessGameDao.remove(boardDao, BOARD_ID, connection);
        DBConnector.closeConnection(connection);
    }

    public ChessGame find() {
        Connection connection = DBConnector.getConnection();
        return chessGameDao.find(boardDao, BOARD_ID, connection);
    }
}
