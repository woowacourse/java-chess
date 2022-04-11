package chess.config;

import chess.database.dao.BoardDao;
import chess.database.dao.JdbcBoardDao;
import chess.database.dao.JdbcGameDao;

public class DaoConfig {

    public static JdbcGameDao getGameDao() {
        return new JdbcGameDao();
    }

    public static BoardDao getBoardDao() {
        return new JdbcBoardDao();
    }

}
