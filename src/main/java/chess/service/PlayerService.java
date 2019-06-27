package chess.service;

import chess.dao.PlayerDao;

import java.sql.SQLDataException;

public class PlayerService {

    private final PlayerDao playerDao;

    private PlayerService() {
        playerDao = PlayerDao.getInstance();
    }

    private static class PlayerServiceHolder {
        private static final PlayerService INSTANCE = new PlayerService();
    }

    public static PlayerService getInstance() {
        return PlayerServiceHolder.INSTANCE;
    }

    public int insertPlayer(String whiteName, String blackName) {
        return playerDao.insertPlayers(whiteName, blackName);
    }
}
