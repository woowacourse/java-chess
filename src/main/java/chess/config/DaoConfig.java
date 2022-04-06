package chess.config;

import chess.dao.BoardDao;
import chess.dao.GameDao;
import chess.dao.JdbcBoardDao;

public class DaoConfig {

    public static GameDao getGameDao() {
        return new GameDao();
    }

    public static BoardDao getBoardDao() {
        return new JdbcBoardDao();
    }

}
