package chess.config;

import chess.dao.BoardDao;
import chess.dao.JdbcBoardDao;
import chess.dao.JdbcGameDao;

public class DaoConfig {

    public static JdbcGameDao getGameDao() {
        return new JdbcGameDao();
    }

    public static BoardDao getBoardDao() {
        return new JdbcBoardDao();
    }

}
